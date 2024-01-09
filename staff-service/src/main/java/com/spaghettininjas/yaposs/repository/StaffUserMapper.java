package com.spaghettininjas.yaposs.repository;

import com.spaghettininjas.yaposs.repository.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StaffUserMapper {
    StaffUserPasswordless toPasswordlessDTO(StaffUser staffUser);
}
