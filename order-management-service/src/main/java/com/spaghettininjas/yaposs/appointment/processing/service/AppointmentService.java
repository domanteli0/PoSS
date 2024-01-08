package com.spaghettininjas.yaposs.appointment.processing.service;

import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentRepository;
import com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment.Appointment;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.spaghettininjas.yaposs.order.processing.repository.specification.CustomerSpecification.emailLike;
import static com.spaghettininjas.yaposs.order.processing.repository.specification.CustomerSpecification.nameLike;

@Service
public class AppointmentService {
    public final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Optional<Appointment> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Appointment> findAll(Integer page, Integer pageSize, @Nullable String name, @Nullable String email) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "name");
        Specification<Appointment> filters = Specification.where(StringUtils.isBlank(name) ? null : nameLike(name))
                .and(StringUtils.isBlank(email) ? null : emailLike(email));
        return repository.findAll(filters, pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Appointment save(Appointment customer){
        return repository.save(customer);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
