package com.spaghettininjas.yaposs.repository.entity;

import lombok.*;

@Data
@Builder
public class StaffUserDTO {
    private Long id;

    private String name;

    private String email;

    private String password;

}
