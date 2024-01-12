package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.dto.PaymentReceiptResponse;
import com.spaghettininjas.yaposs.dto.TransactionUpdateRequest;
import com.spaghettininjas.yaposs.entity.Transaction;
import com.spaghettininjas.yaposs.exception.ApiException;
import com.spaghettininjas.yaposs.repository.PaymentRepository;
import com.spaghettininjas.yaposs.repository.specification.PaymentSpecification;
import com.spaghettininjas.yaposs.utils.PaymentTypesEnum;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.spaghettininjas.yaposs.repository.specification.PaymentSpecification.paymentTypeLike;

@Service
public class PaymentsService {

    @Autowired
    private RestTemplate restTemplate;

    public final PaymentRepository repository;

    public static final double TAXES = 0.21;
    public static final String ERR_PREFIX= "err.payment.";

    public PaymentsService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Transaction> findAll(Integer page, Integer pageSize, String paymentType, @Nullable Long staffUserId, @Nullable Long orderId) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");
        Specification<Transaction> filters = Specification.where(StringUtils.isBlank(paymentType) ? null : paymentTypeLike(paymentType))
                .and(staffUserId == null ? null : PaymentSpecification.staffUserIdEqual(staffUserId))
                .and(orderId == null ? null : PaymentSpecification.orderIdEqual(orderId));
        return this.repository.findAll(filters, pageable).getContent();
    }

    public Transaction save(Transaction transaction){
        return this.repository.save(transaction);
    }

    public Boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    public Transaction findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> ApiException.notFound(ERR_PREFIX + "doesnt.exist"));
    }

    public void deleteById(Long id){
        if (!existsById(id)) {
            throw ApiException.notFound(ERR_PREFIX + "doesnt.exist");
        }
        this.repository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, TransactionUpdateRequest request) {
        Optional<Transaction> foundTransaction = this.repository.findById(id);
        if(foundTransaction.isEmpty())
            throw ApiException.notFound(ERR_PREFIX + "doesnt.exist");
        Transaction updatedTransaction = foundTransaction.get().setDiscountApplied(request.getDiscountApplied())
                .setOrderId(request.getOrderId())
                .setPaymentType(request.getPaymentType())
                .setStaffUserId(request.getStaffUserId())
                .setTax(request.getTax())
                .setTip(request.getTip())
                .setTotalDiscount(request.getTotalDiscount());
        return this.repository.save(updatedTransaction);
    }

    public PaymentReceiptResponse payForOrder(Transaction transaction, Long moneyAmount) {
//        Order order = restTemplate.exchange("http://api-gateway:8080/api/Staff/" + transaction.getOrderId()
//                , HttpMethod.GET, null,  Order.class).getBody();
        double totalPrice = 100;
        //TODO: Add update status endpoint for Order.
//        Order updatedOrder = restTemplate.exchange("http://api-gateway:8080/api/Orders/" + transaction.getOrderId()
//                , HttpMethod.GET, null,  Order.class).getBody();


        PaymentReceiptResponse receipt = new PaymentReceiptResponse()
                .setTransactionId(transaction.getId())
                .setTotalDiscount(transaction.getTotalDiscount()) //TODO: Add discounts enum
                .setTaxes(totalPrice * TAXES)
                .setTips(transaction.getTip())
                .setTotalPrice(calculateTotalPrice(totalPrice, transaction.getTotalDiscount(), TAXES, transaction.getTip()));
            System.out.println("the money amount is: " + moneyAmount);
        if(moneyAmount < receipt.getTotalPrice()) {
//            updatedOrder.setStatus(OrderStatusEnum.CANCELED);
//            restTemplate.put("http://api-gateway:8080/api/Orders/" + transaction.getOrderId()
//                , updatedOrder);
            throw new ApiException(ERR_PREFIX + "notEnoughMoney", HttpStatus.BAD_REQUEST);
        }
        receipt.setChange((transaction.getPaymentType().equals(PaymentTypesEnum.CASH.name())) ? (moneyAmount - receipt.getTotalPrice()) : 0);
//        updatedOrder.setStatus(OrderStatusEnum.COMPLETE);
//        restTemplate.put("http://api-gateway:8080/api/Orders/" + transaction.getOrderId()
//                , updatedOrder);
        return receipt;
    }

    public double calculateTotalPrice(double price, double discount, double taxes, double tips) {
        double totalPrice = price + price * taxes - discount + tips;
        return totalPrice >= 0 ? totalPrice : 0;
    }

}
