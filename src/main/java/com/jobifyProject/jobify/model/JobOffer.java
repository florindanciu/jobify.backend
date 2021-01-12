package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Insert;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class JobOffer {

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
    private Enum state; // ENUM

    @ManyToOne
    private Company company;

//    @ManyToMany
//    private Set<User> applicants;
}