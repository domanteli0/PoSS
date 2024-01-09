package com.spaghettininjas.yaposs.order.processing.repository.order;


import lombok.*;

import java.time.ZonedDateTime;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private Long staffUserId;

    private Long appointmentId;

    private ZonedDateTime dateTimeGMT;
}
