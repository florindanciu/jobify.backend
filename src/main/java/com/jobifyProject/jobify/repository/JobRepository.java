package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAllByCompanyId(UUID id);

    @Query(
            value = "SELECT j FROM Job j WHERE j.id = :jobId AND j.company = :company")
    Optional<Job> findByIdAndCompany(@Param("company") Company company, @Param("jobId") UUID jobId);

}
