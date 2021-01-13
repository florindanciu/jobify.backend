package com.jobifyProject.jobify.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter@Setter
public class SkillDto {
    private UUID id;
    private String name;
}
