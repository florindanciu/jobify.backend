package com.jobifyProject.jobify.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String username;
    private String role;
    private String email;
    private String password;
}
