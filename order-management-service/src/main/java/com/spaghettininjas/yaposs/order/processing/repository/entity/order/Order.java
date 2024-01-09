package com.spaghettininjas.yaposs.order.processing.repository.entity.order;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


/**
 * Also known as Cheque from contract.
 * Renamed so that endpoint and entity naming matches.
 */

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

    private String paymentType;

    private BigDecimal tax;

    private BigDecimal totalDiscount;

    private BigDecimal price;
}
