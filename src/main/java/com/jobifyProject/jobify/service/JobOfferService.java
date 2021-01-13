package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.exception.CompanyNotFoundException;
import com.jobifyProject.jobify.exception.JobNotFoundException;
import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.CompanyRepository;
import com.jobifyProject.jobify.repository.JobRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobOfferService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<JobOffer> getAllJobOffers() {
        return jobRepository.findAll();
    }

    public JobOffer getJobById(UUID id) {
        return jobRepository.findById(id).
                orElseThrow(() -> new JobNotFoundException(id));
    }

    public Set<JobOffer> findJobsByLocation(String location) {
        return jobRepository.findJobOffersByLocationIs(location);
    }

    public void addJob(JobOffer jobOffer, UUID company_id) {
        Company company = companyRepository.findById(company_id).
                orElseThrow(() -> new CompanyNotFoundException(company_id));
        jobOffer.setCompany(company);
        jobRepository.save(jobOffer);
    }

    public JobOffer updateJobById(UUID id, JobOffer updatedJobOffer) {
        JobOffer jobOffer = getJobById(id);

        jobOffer.setName(updatedJobOffer.getName());
        jobOffer.setDescription(updatedJobOffer.getDescription());
        jobOffer.setApplyLink(updatedJobOffer.getApplyLink());
        jobOffer.setType(updatedJobOffer.getType());
        jobOffer.setLocation(updatedJobOffer.getLocation());

        return jobRepository.save(jobOffer);
    }

    public void deleteJob(UUID id) {
        jobRepository.delete(getJobById(id));
    }

    public Set<User> findJobOfferApplicants(UUID id) {
        JobOffer jobOffer = jobRepository.findById(id).
                orElseThrow(() -> new JobNotFoundException(id));
        return jobOffer.getApplicants();
    }
}
