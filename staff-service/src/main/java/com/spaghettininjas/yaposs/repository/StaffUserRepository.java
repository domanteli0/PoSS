package com.spaghettininjas.yaposs.repository;

import com.spaghettininjas.yaposs.repository.entity.StaffUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffUserRepository extends JpaRepository<StaffUser, Long>, JpaSpecificationExecutor<StaffUser> {
}
