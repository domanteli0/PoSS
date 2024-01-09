package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.dto.TransactionUpdateRequest;
import com.spaghettininjas.yaposs.entity.Transaction;
import com.spaghettininjas.yaposs.exception.ApiException;
import com.spaghettininjas.yaposs.repository.PaymentRepository;
import com.spaghettininjas.yaposs.repository.specification.PaymentSpecification;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.spaghettininjas.yaposs.repository.specification.PaymentSpecification.paymentTypeLike;

@Service
public class PaymentsService {
    public final PaymentRepository repository;

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
                .orElseThrow(() -> ApiException.notFound("err.transaction.doesnt.exist"));
    }

    public void deleteById(Long id){
        if (!existsById(id)) {
            throw ApiException.notFound("err.transaction.doesnt.exist");
        }
        this.repository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, TransactionUpdateRequest request) {
        Optional<Transaction> foundTransaction = this.repository.findById(id);
        if(foundTransaction.isEmpty())
            throw ApiException.notFound("err.transaction.doesnt.exist");
        Transaction updatedTransaction = foundTransaction.get().setDiscountApplied(request.getDiscountApplied())
                .setOrderId(request.getOrderId())
                .setPaymentType(request.getPaymentType())
                .setStaffUserId(request.getStaffUserId())
                .setTax(request.getTax())
                .setTip(request.getTip())
                .setTotalDiscount(request.getTotalDiscount());
        return this.repository.save(updatedTransaction);
    }

}
