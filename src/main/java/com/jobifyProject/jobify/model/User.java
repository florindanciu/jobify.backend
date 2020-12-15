package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private String email;
    private String password;
    private UUID jobId;
    private UUID companyId;
    private UUID appliedJobId;
}
