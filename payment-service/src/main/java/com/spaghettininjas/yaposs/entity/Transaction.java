package com.spaghettininjas.yaposs.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Data
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long discountApplied;
    private Long orderId;
    private String paymentType;
    private Long staffUserId;
    private Long tax;
    private Long tip;
    private Long totalDiscount;

    public Transaction setDiscountApplied(Long discountApplied) {
        this.discountApplied = discountApplied;
        return this;
    }
    public Transaction setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
    public Transaction setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }
    public Transaction setStaffUserId(Long staffUserId) {
        this.staffUserId = staffUserId;
        return this;
    }
    public Transaction setTax(Long tax) {
        this.tax = tax;
        return this;
    }
    public Transaction setTip(Long tip) {
        this.tip = tip;
        return this;
    }
    public Transaction setTotalDiscount(Long totalDiscount) {
        this.totalDiscount = totalDiscount;
        return this;
    }
}
