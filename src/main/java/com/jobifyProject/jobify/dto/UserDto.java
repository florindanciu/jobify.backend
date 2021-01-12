package com.jobifyProject.jobify.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobifyProject.jobify.model.JobOffer;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {

    @JsonIgnore
    private UUID id;
    private String username;
    private String role;
    private String email;
    private String password;
    private List<JobOffer> workedAt;
    private Set<JobOffer> favoriteJobOffers;
    private Set<JobOffer> appliedJobs;
}
