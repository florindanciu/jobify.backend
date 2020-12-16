package com.jobifyProject.jobify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "applied_jobs")
public class AppliedJob {

    @Id
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Job job;

}
