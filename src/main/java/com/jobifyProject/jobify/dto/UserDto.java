package com.jobifyProject.jobify.dto;

import com.jobifyProject.jobify.model.Skill;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String username;
    private String role;
    private String email;
    private String password;
    private String experience;
    private String age;
    private String image;
}
