package com.spaghettininjas.yaposs.order.processing.repository.item;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem mergeWithDto(@MappingTarget OrderItem domain, OrderItemDTO dto);
    OrderItem toDomain(OrderItemDTO dto);
    OrderItemDTO toDto(OrderItem domain);
}
