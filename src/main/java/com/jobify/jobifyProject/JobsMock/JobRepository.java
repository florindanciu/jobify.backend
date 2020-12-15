package com.jobify.jobifyProject.JobsMock;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JobRepository extends CrudRepository<Job, String> {
}
