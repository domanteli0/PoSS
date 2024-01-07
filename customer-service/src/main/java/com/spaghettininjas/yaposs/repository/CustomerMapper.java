package com.spaghettininjas.yaposs.repository;

import com.spaghettininjas.yaposs.DTO;
import com.spaghettininjas.yaposs.repository.entity.Customer;
import com.spaghettininjas.yaposs.repository.entity.CustomerDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer mergeWithDto(@MappingTarget Customer domain, CustomerDTO dto);
    Customer toDomain(DTO.Customer dto);
    DTO.Customer toDto(Customer domain);
}
