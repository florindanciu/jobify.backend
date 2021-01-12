package com.jobifyProject.jobify.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.sql.Insert;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "jobOffers")
public class JobOffer {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String applyLink;
    @CreationTimestamp
    private Date publishedDate;
    private String type;
    private String location;
//    @Enumerated(EnumType.STRING)
//    private JobOfferStates state;

    @ManyToOne(fetch = FetchType.LAZY)
    private User employed;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(mappedBy = "appliedJobs")
    private Set<User> applicants = new HashSet<>();

    @ToString.Exclude
    @JsonBackReference // indicate that this is child class compared to User
    @ManyToMany(mappedBy = "favoriteJobOffers",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @Override
    public String toString(){
        return "JobOffer [id=" + id + ", name=" + name + "]";
    }



}