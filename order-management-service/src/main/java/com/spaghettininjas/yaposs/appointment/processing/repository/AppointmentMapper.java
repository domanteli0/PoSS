package com.spaghettininjas.yaposs.appointment.processing.repository;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(source = "orderId", target = "order.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Appointment mergeWithDto(@MappingTarget Appointment domain, AppointmentDTO dto);
//    Appointment toDomain(AppointmentDTO dto);
//    AppointmentDTO toDto(Appointment domain);
}
