package com.spaghettininjas.yaposs.appointment.processing.repository;


import com.spaghettininjas.yaposs.enums.StatusEnum;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

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

    @OneToOne
    @JoinColumn(name = "orderId")
    private Order orderId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateTimeGMT;

    private Integer durationMinutes;

    private StatusEnum status;
}
