package com.spaghettininjas.yaposs.repository;

import com.spaghettininjas.yaposs.repository.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StaffUserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StaffUser mergeWithDto(@MappingTarget StaffUser domain, StaffUserDTO dto);
}
