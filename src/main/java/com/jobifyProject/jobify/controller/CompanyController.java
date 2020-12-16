package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.Job;
import com.jobifyProject.jobify.repository.CompanyRepository;
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
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable UUID id) {
        Company company = companyRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("Company with id " + id + " not found"));
        return ResponseEntity.ok(company);
    }

    @GetMapping("/companies/{id}/jobs")
    public List<Job> getJobsByCompanyId(@PathVariable UUID id) {
        List<Job> jobs = jobRepository.findAllByCompanyId(id);
        return jobs;
    }

    @PostMapping("/companies")
    public Company addCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompanyById(@PathVariable UUID id, @RequestBody Company updatedCompanyDetails) {
        Company company = companyRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("User with id " + id + " not found"));
        company.setName(updatedCompanyDetails.getName());
        company.setWebsiteLink(updatedCompanyDetails.getWebsiteLink());
        company.setCompanyLogo(updatedCompanyDetails.getCompanyLogo());

        Company updatedCompany = companyRepository.save(company);

        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable UUID id) {
        Company company = companyRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("User with id " + id + " not found"));

        companyRepository.delete(company);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
