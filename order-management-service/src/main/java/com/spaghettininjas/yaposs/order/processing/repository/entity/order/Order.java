package com.spaghettininjas.yaposs.order.processing.repository.entity.order;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

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

    @DecimalMin(value = "0.00", inclusive = false, message = "Value must be positive")
    @Digits(integer = 10, fraction = 2, message = "Value must have up to 2 decimal places")
    private BigDecimal tax;

    @DecimalMin(value = "0.00", inclusive = false, message = "Value must be positive")
    @Digits(integer = 10, fraction = 2, message = "Value must have up to 2 decimal places")
    private BigDecimal totalDiscount;

    @DecimalMin(value = "0.00", inclusive = false, message = "Value must be positive")
    @Digits(integer = 10, fraction = 2, message = "Value must have up to 2 decimal places")
    private BigDecimal price;
}
