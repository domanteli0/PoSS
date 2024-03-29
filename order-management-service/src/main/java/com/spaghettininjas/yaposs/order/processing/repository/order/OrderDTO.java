package com.spaghettininjas.yaposs.order.processing.repository.order;


import com.spaghettininjas.yaposs.enums.OrderStatusEnum;
import lombok.*;

import java.util.Date;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private Long staffUserId;

    private Date startDateTimeGMT;

    private OrderStatusEnum status;
}
