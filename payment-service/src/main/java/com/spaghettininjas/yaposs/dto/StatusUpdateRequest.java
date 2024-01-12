package com.spaghettininjas.yaposs.dto;

import com.spaghettininjas.yaposs.utils.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {
    private OrderStatusEnum status;

    public StatusUpdateRequest setStatus(OrderStatusEnum status) {
        this.status = status;
        return this;
    }
}
