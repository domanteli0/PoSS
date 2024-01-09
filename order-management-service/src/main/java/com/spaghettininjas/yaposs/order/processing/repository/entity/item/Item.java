package com.spaghettininjas.yaposs.order.processing.repository.entity.item;


import jakarta.persistence.*;
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

    private Integer quantity;

    private BigDecimal price;
}
