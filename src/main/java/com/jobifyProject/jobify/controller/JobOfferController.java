package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.CompanyRepository;
import com.jobifyProject.jobify.repository.JobRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class JobOfferController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/jobs")
//    @PreAuthorize("hasRole('Role_ADMIN')")
    public List<JobOffer> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobOffer> getJobById(@PathVariable UUID id) {
        JobOffer jobOffer = jobRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));
        return ResponseEntity.ok(jobOffer);
    }

//    @GetMapping("/jobs/{id}/applicants")
//    public Set<User> getJobOfferApplicants(@PathVariable UUID id) {
//        JobOffer jobOffer = jobRepository.findById(id).
//                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));
//        return jobOffer.getApplicants();
//    }

    @PostMapping("/companies/{company_id}/jobs")
    public JobOffer addJob(@RequestBody JobOffer jobOffer, @PathVariable UUID company_id) {
        Company company = companyRepository.findById(company_id).
                orElseThrow(() -> new ResourceAccessException("Company with id " + company_id + " not found"));
        jobOffer.setCompany(company);
        return jobRepository.save(jobOffer);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<JobOffer> updateJobById(@PathVariable UUID id, @RequestBody JobOffer updatedJobOfferDetails) {
        JobOffer jobOffer = jobRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));

        jobOffer.setName(updatedJobOfferDetails.getName());
        jobOffer.setDescription(updatedJobOfferDetails.getDescription());
        jobOffer.setApplyLink(updatedJobOfferDetails.getApplyLink());
        jobOffer.setType(updatedJobOfferDetails.getType());
        jobOffer.setLocation(updatedJobOfferDetails.getLocation());

        JobOffer updatedJobOffer = jobRepository.save(jobOffer);

        return ResponseEntity.ok(updatedJobOffer);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteJob(@PathVariable UUID id) {
        JobOffer jobOffer = jobRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));

        jobRepository.delete(jobOffer);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
