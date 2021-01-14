package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.JobOfferConverter;
import com.jobifyProject.jobify.converter.UserConverter;
import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.service.JobOfferService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class JobOfferController {

    @Autowired
    private JobOfferConverter jobOfferConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping("/jobs")
//    @PreAuthorize("hasRole('Role_ADMIN')")
    public List<JobOfferDto> getAllJobs() {
        List<JobOffer> allJobOffers = jobOfferService.getAllJobOffers();
        return jobOfferConverter.modelToDto(allJobOffers);
    }

    @GetMapping("/jobs/{id}")
    public JobOfferDto getJobById(@PathVariable UUID id) {
        JobOffer jobOffer = jobOfferService.getJobById(id);
        return jobOfferConverter.modelToDto(jobOffer);
    }

    @GetMapping("/jobs/{id}/applicants")
    public Set<User> getJobOfferApplicants(@PathVariable UUID id) {
        Set<User> applicants = jobOfferService.findJobOfferApplicants(id);
        return userConverter.modelToDto(applicants);
    }

    @PostMapping("/companies/{company_id}/jobs")
    public void addJob(@RequestBody JobOfferDto jobOfferDto, @PathVariable UUID company_id) {
        JobOffer jobOffer = jobOfferConverter.dtoToModel(jobOfferDto);
        jobOfferService.addJob(jobOffer, company_id);
    }

    @PutMapping("/jobs/{id}")
    public JobOfferDto updateJobById(@PathVariable UUID id, @RequestBody JobOfferDto jobOfferDto) {
        JobOffer jobOffer = jobOfferConverter.dtoToModel(jobOfferDto);
        JobOffer updatedJobOffer = jobOfferService.updateJobById(id, jobOffer);
        return jobOfferConverter.modelToDto(updatedJobOffer);
    }

    @GetMapping("/jobs/location/{location}")
    public Set<JobOfferDto> getJobByLocation(@PathVariable String location) {
        Set<JobOffer> jobOffers = jobOfferService.findJobsByLocation(location);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @GetMapping("/jobs/jobName/{name}")
    public Set<JobOfferDto> getJobByName(@PathVariable String name) {
        Set<JobOffer> jobOffers = jobOfferService.findJobsByName(name);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @GetMapping("/jobs/name/{name}/location/{location}")
    public Set<JobOfferDto> getJobByNameAndLocation(@PathVariable String name, @PathVariable String location) {
        Set<JobOffer> jobOffers = jobOfferService.findJobsByNameAndLocation(name,location);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteJob(@PathVariable UUID id) {
        jobOfferService.deleteJob(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
