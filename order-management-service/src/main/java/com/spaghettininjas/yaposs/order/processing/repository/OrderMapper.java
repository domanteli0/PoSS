package com.spaghettininjas.yaposs.order.processing.repository;

import com.spaghettininjas.yaposs.DTO;
import com.spaghettininjas.yaposs.order.processing.repository.entity.order.Order;
import com.spaghettininjas.yaposs.order.processing.repository.entity.order.OrderDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order mergeWithDto(@MappingTarget Order domain, OrderDTO dto);
    Order toDomain(DTO.Customer dto);
    DTO.Customer toDto(Order domain);
}
