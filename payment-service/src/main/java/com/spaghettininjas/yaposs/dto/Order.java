package com.spaghettininjas.yaposs.dto;

import com.spaghettininjas.yaposs.utils.OrderStatusEnum;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
@Getter
public class Order {
    private Long id;

    private Long staffUserId;

    private List<OrderItem> items;

    private Date startDateTimeGMT;

    private OrderStatusEnum status;
}
