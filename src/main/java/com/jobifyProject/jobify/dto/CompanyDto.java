package com.jobifyProject.jobify.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CompanyDto {

    private UUID id;
    private String name;
    private String websiteLink;
    private String companyLogo;
}
