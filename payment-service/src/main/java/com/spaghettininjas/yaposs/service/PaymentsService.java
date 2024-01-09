package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.PaymentRepository;
import com.spaghettininjas.yaposs.repository.entity.Payment;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsService {
    public final PaymentRepository repository;

    public PaymentsService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Optional<Payment> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Payment> findAll(Integer page, Integer pageSize, @Nullable String name, @Nullable String email) {
//        SpringDataWebProperties.Pageable pageable = PageRequest.of(page, pageSize, TypeCache.Sort.Direction.ASC, "name");
//        Specification<Payment> filters = Specification.where(StringUtils.isBlank(name) ? null : nameLike(name))
//                .and(StringUtils.isBlank(email) ? null : emailLike(email));
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Payment save(Payment payment){
        return repository.save(payment);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
