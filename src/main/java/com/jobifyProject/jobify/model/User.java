package com.jobifyProject.jobify.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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

//    @JsonManagedReference
    @OneToMany(mappedBy = "employed")
    private List<JobOffer> workedAt;

    @JsonManagedReference // indicate that this is parent class compared to User
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_favorite_jobs",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "job_offer_id")})
    private Set<JobOffer> favoriteJobOffers;

    @JsonManagedReference
    @ManyToMany
    private Set<JobOffer> appliedJobs = new HashSet<>();

    @Override
    public String toString(){
        return "User [id=" + id + ", name=" + username + "]";
    }

}
