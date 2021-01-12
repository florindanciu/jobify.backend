package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "employed")
    private List<JobOffer> workedAt;

    @ManyToMany
    private Set<JobOffer> favoriteJobOffers;

    @ManyToMany
    private Set<JobOffer> appliedJobs = new HashSet<>();

}
