package com.spaghettininjas.yaposs.dto;

import lombok.*;

@Data
@Builder
public class StaffUserPasswordless {
    private Long id;
    private String name;
    private String email;
}
