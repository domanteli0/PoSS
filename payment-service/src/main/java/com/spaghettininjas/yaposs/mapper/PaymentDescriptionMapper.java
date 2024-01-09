package com.spaghettininjas.yaposs.mapper;

import com.spaghettininjas.yaposs.dto.TransactionUpdateRequest;
import com.spaghettininjas.yaposs.entity.Transaction;

public class PaymentDescriptionMapper {
    public static Transaction transactionUpdateRequestToTransaction(TransactionUpdateRequest request) {
        return new Transaction()
                .setDiscountApplied(request.getDiscountApplied())
                .setOrderId(request.getOrderId())
                .setPaymentType(request.getPaymentType())
                .setStaffUserId(request.getStaffUserId())
                .setTax(request.getTax())
                .setTip(request.getTip())
                .setTotalDiscount(request.getTotalDiscount());
    }
}
