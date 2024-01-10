package com.spaghettininjas.yaposs.order.processing.repository.item;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * OrderItem is a product with quantity associated with an order.
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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "poss_order_id", referencedColumnName = "id")
    private Order order;

    private String name;

    private Integer quantity;

    private Double priceOfUnit;
}
