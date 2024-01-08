package com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Entity
@Data
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    // TODO: status enum
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long staffUserId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    private String status;
}
