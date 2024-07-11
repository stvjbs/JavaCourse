package com.andersenlab.spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDto {
    @NonNull
    private int id;
    @NonNull
    private String name;
    private boolean userStatus;
}
