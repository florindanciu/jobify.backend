package com.jobifyProject.jobify.dto;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.User;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class JobOfferDto {

    private UUID id;
    private String name;
    private String description;
    private String applyLink;
    private Date publishedDate;
    private String type;
    private String location;
    private Enum state;
    private User employed;
    private Company company;
    private Set<User> applicants = new HashSet<>();
    private Set<User> users = new HashSet<>();
}
