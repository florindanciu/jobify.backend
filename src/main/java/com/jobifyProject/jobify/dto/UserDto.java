package com.jobifyProject.jobify.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    private String password;
    private String experience;
    private String age;
    private String image;
    private Set<String> roles;
}
