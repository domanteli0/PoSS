package com.spaghettininjas.yaposs.repository;

import com.spaghettininjas.yaposs.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
}
