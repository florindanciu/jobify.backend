package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.model.AppliedJob;
import com.jobifyProject.jobify.repository.AppliedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class AppliedJobController {

    @Autowired
    private AppliedJobRepository appliedJobRepository;

    @GetMapping("/users/{userId}/appliedJobs")
    public List<AppliedJob> getAppliedJobsByUserId(@PathVariable UUID userId) {
        return appliedJobRepository.findAllByUserId(userId);
    }

    @GetMapping("/users/{userId}/appliedJobs/{appliedJobId}")
    public Optional<AppliedJob> getAppliedJobById(@PathVariable UUID appliedJobId) {
        return appliedJobRepository.findById(appliedJobId);
    }

    @PostMapping("/users/{userId}/appliedJobs")
    public void addAppliedJob(@RequestBody AppliedJob appliedJob) {
         appliedJobRepository.save(appliedJob);
    }

    @DeleteMapping("/users/{userId}/appliedJobs/{appliedJobId}")
    public void deleteAppliedJob(@PathVariable UUID appliedJobId){
        appliedJobRepository.deleteById(appliedJobId);
    }

}
