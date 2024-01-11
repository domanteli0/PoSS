package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.StaffUserRepository;
import com.spaghettininjas.yaposs.repository.entity.StaffUser;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.spaghettininjas.yaposs.repository.specification.StaffUserSpecification.emailLike;
import static com.spaghettininjas.yaposs.repository.specification.StaffUserSpecification.nameLike;

@Service
public class StaffUsersService {
    public final StaffUserRepository repository;

    public StaffUsersService(StaffUserRepository repository) {
        this.repository = repository;
    }

    public Optional<StaffUser> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<StaffUser> findAll(
        Integer page,
        Integer pageSize,
        @Nullable String name,
        @Nullable String email
    ) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "name");
        Specification<StaffUser> filters =
            Specification
                .where(StringUtils.isBlank(name) ? null : nameLike(name))
                .and(StringUtils.isBlank(email) ? null : emailLike(email));

        return repository.findAll(filters, pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public StaffUser createOrReplace(StaffUser staffUser){
        return repository.save(staffUser);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
