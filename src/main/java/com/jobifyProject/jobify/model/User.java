package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String role;
    private String email;
    private String password;

    @OneToMany
    private Set<JobOffer> workingAt;

    @OneToMany
    private Set<JobOffer> favoriteJobOffers;

    @OneToMany
    private Set<JobOffer> appliedJobs;

}
