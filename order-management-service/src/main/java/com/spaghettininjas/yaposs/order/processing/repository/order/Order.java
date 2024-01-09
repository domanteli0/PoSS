package com.spaghettininjas.yaposs.order.processing.repository.order;


import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.List;


@Getter
@Entity
@Data
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long staffUserId;

    @OneToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointmentId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateTimeGMT;
}
