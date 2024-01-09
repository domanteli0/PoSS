package com.spaghettininjas.yaposs.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionUpdateRequest {
    @Nullable
    private Long discountApplied;
    @Nullable
    private Long orderId;
    @Nullable
    private String paymentType;
    @Nullable
    private Long staffUserId;
    @Nullable
    private Long tax;
    @Nullable
    private Long tip;
    @Nullable
    private Long totalDiscount;
}
