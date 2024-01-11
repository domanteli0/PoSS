package com.spaghettininjas.yaposs.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentReceiptResponse {
    private Long transactionId;
    private double totalDiscount;
    private double taxes;
    private double tips;
    private double change;
    private double totalPrice;

    public PaymentReceiptResponse setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public PaymentReceiptResponse setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
        return this;
    }

    public PaymentReceiptResponse setTaxes(double taxes) {
        this.taxes = taxes;
        return this;
    }

    public PaymentReceiptResponse setTips(double tips) {
        this.tips = tips;
        return this;
    }

    public PaymentReceiptResponse setChange(double change) {
        this.change = change;
        return this;
    }

    public PaymentReceiptResponse setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
