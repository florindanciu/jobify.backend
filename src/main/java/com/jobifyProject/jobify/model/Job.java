package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Job {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String applyLink;
    private String companyName;
    private UUID companyId;
    private Date publishedDate;
}
