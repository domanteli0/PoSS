package com.spaghettininjas.yaposs.order.processing.repository.entity.item;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

/**
 * Item is a product with quantity.
 * It duplicates price for ease of access and captures it at order time.
 * It also refers to the product through name.
 */

@Getter
@Entity
@Data
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Positive
    private Integer quantity;

    @DecimalMin(value = "0.00", inclusive = false, message = "Value must be positive")
    @Digits(integer = 10, fraction = 2, message = "Value must have up to 2 decimal places")
    private BigDecimal price;
}
