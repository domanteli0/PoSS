package com.spaghettininjas.yaposs.order.processing.repository.order;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDTO mapToDTO(Order order);
}
