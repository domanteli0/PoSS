package com.spaghettininjas.yaposs.appointment.processing.repository;

import com.spaghettininjas.yaposs.DTO;
import com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment.AppointmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Appointment mergeWithDto(@MappingTarget Appointment domain, AppointmentDTO dto);
    Appointment toDomain(AppointmentDTO dto);
    AppointmentDTO toDto(Appointment domain);
}
