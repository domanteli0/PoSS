package com.spaghettininjas.yaposs.order.processing.repository.order;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "appointmentId", target = "appointment.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order mergeWithDto(@MappingTarget Order domain, OrderDTO dto);
//    Order toDomain(OrderDTO dto);
//    OrderDTO toDto(Order domain);
}
