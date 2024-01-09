package com.spaghettininjas.yaposs.order.processing.repository.order;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Entity
@Data
// rename something other to "order" because it's a sql keyword
@Table(name="pos_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long staffUserId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String dateTimeGMT;
}
