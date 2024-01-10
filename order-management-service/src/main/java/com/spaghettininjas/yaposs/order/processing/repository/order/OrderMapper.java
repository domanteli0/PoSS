package com.spaghettininjas.yaposs.order.processing.repository.order;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    @Mapping(source = "items", target = "", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDTO mapToDTO(Order order);
}
