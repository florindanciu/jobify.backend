package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Insert;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String applyLink;
    @Generated()
    private Date publishedDate;
    private String type;
    private String location;

    @ManyToOne
    private Company company;
}