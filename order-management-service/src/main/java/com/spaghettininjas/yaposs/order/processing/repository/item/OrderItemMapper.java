package com.spaghettininjas.yaposs.order.processing.repository.item;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "orderId", target = "order.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem mergeWithDto(@MappingTarget OrderItem domain, OrderItemDTO dto);
}
