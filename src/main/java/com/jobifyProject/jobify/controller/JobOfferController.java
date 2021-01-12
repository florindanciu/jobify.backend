package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.JobOfferConverter;
import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.service.JobOfferService;
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

//    @GetMapping("/jobs/{id}/applicants")
//    public Set<User> getJobOfferApplicants(@PathVariable UUID id) {
//        JobOffer jobOffer = jobRepository.findById(id).
//                orElseThrow(() -> new ResourceAccessException("Job with id " + id + " not found"));
//        return jobOffer.getApplicants();
//    }

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

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteJob(@PathVariable UUID id) {
        jobOfferService.deleteJob(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
