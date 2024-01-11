package com.spaghettininjas.yaposs.appointment.processing.service;

import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentRepository;
import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentSpecification.dateTimeGreaterThan;
import static com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentSpecification.dateTimeLessThan;

@Service
public class AppointmentService {
    public final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Optional<Appointment> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Appointment> findAll(Integer page, Integer pageSize, @Nullable Date fromDateTimeGMT, @Nullable Date tillDateTimeGMT) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "dateTimeGMT");
        Specification<Appointment> filters = Specification.where(fromDateTimeGMT == null ? null : dateTimeGreaterThan(fromDateTimeGMT))
                .and(tillDateTimeGMT == null ? null : dateTimeLessThan(tillDateTimeGMT));
        return repository.findAll(filters, pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Appointment save(Appointment appointment){
        return repository.save(appointment);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
