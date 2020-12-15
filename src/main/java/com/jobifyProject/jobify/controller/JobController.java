package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.model.Job;
import com.jobifyProject.jobify.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable UUID id) {
        Job job = jobRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));
        return ResponseEntity.ok(job);
    }

    @PostMapping("/jobs")
    public Job addJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable UUID id, @RequestBody Job updatedJobDetails) {
        Job job = jobRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));

        job.setName(updatedJobDetails.getName());
        job.setDescription(updatedJobDetails.getDescription());
        job.setApplyLink(updatedJobDetails.getApplyLink());
        job.setCompanyName(updatedJobDetails.getCompanyName());
        job.setPublishedDate(updatedJobDetails.getPublishedDate());

        Job updatedJob = jobRepository.save(job);

        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteJob(@PathVariable UUID id) {
        Job job = jobRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));

        jobRepository.delete(job);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
