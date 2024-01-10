package com.spaghettininjas.yaposs.order.processing.repository.order;


import lombok.*;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private Long staffUserId;

    private String dateTimeGMT;
}
