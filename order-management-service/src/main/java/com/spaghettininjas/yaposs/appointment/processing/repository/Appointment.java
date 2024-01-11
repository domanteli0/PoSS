package com.spaghettininjas.yaposs.appointment.processing.repository;


import com.spaghettininjas.yaposs.enums.StatusEnum;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import jakarta.persistence.*;
import lombok.*;
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

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "poss_order_id", referencedColumnName = "id")
    private Order order;

    private Date dateTimeGMT;

    private Integer durationMinutes;

    private StatusEnum status;
}
