package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.AppliedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob, UUID> {
    List<AppliedJob> findAllByUserId(UUID userId);
}
