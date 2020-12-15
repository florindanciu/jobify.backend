package com.jobify.jobifyProject.JobsMock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {

    @Id
    private UUID id;
    private String jobName;

    public Job(java.util.UUID id, String jobName) {
        this.id = id;
        this.jobName = jobName;
    }
}
