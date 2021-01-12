package com.jobifyProject.jobify.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;

@Data
public class CompanyDto {

    @JsonIgnore
    private UUID id;
    private String name;
    private String websiteLink;
    private String companyLogo;
}
