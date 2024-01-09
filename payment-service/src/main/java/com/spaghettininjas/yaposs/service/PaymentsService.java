package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.dto.TransactionUpdateRequest;
import com.spaghettininjas.yaposs.entity.Transaction;
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
        return repository.findAll(filters, pageable).getContent();
    }

    public Transaction save(Transaction transaction){
        return repository.save(transaction);
    }

    public Optional<Transaction> findById(Long id) { return repository.findById(id);}

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, TransactionUpdateRequest request) {
        Optional<Transaction> foundTransaction = repository.findById(id);
        if(foundTransaction.isEmpty())
            return null;
        Transaction updatedTransaction = foundTransaction.get().setDiscountApplied(request.getDiscountApplied())
                .setOrderId(request.getOrderId())
                .setPaymentType(request.getPaymentType())
                .setStaffUserId(request.getStaffUserId())
                .setTax(request.getTax())
                .setTip(request.getTip())
                .setTotalDiscount(request.getTotalDiscount());
        return repository.save(updatedTransaction);
    }

}
