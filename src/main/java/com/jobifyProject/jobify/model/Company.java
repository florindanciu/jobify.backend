package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String websiteLink;
    private String companyLogo;
}
