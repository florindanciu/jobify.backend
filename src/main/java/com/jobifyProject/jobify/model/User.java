package com.jobifyProject.jobify.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Setter @Getter
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

    @OneToMany(mappedBy = "user")
    private List<Skill> skills = new ArrayList<>();

    @Override
    public String toString(){
        return "User [id=" + id + ", name=" + username + "]";
    }
}
