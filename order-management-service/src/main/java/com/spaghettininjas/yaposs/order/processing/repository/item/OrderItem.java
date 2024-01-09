package com.spaghettininjas.yaposs.order.processing.repository.item;


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

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    private String name;

    private Integer quantity;

    private BigDecimal price;
}
