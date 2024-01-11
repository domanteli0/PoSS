package com.spaghettininjas.yaposs.appointment.processing.repository;


import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Entity
@Data
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "poss_order_id")
    private Order order;

    // when order ends, start of order is specified in Order entity
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDateTimeGMT;
}
