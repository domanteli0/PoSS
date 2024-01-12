package com.spaghettininjas.yaposs.order.processing.repository.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spaghettininjas.yaposs.enums.OrderStatusEnum;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Entity
@Data
// rename something other to "order" because it's a sql keyword
@Table(name="poss_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private Long id;

    private Long staffUserId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    // when order started
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDateTimeGMT;

    @Builder.Default
    private OrderStatusEnum status = OrderStatusEnum.ACTIVE;

    // needed for manually adding references to the Order on POST and mapping from DTO
    public void generateId() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        this.id = (mostSignificantBits & Long.MAX_VALUE) << 64 | (leastSignificantBits & Long.MAX_VALUE);
    }

    public void addItems(List<OrderItem> newItems) {
        this.items.addAll(newItems);
    }
}
