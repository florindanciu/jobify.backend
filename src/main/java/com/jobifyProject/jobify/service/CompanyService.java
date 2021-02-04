package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.exception.CompanyNotFoundException;
import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.CompanyRepository;
import com.jobifyProject.jobify.repository.JobRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(UUID id) {
        return companyRepository.findById(id).
                orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public List<JobOffer> getJobsByCompanyId(UUID id) {
        return jobRepository.findAllByCompanyId(id);
    }

    public JobOffer getJobByIdAndCompanyId(UUID companyId, UUID jobId) {
        Company company = getCompanyById(companyId);
        return jobRepository.findByIdAndCompany(company,jobId);
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company updateCompanyById(UUID id, Company updatedCompany) {
        Company company = getCompanyById(id);
        company.setName(updatedCompany.getName());
        company.setEmail(updatedCompany.getEmail());
        company.setWebsiteLink(updatedCompany.getWebsiteLink());
        company.setCompanyLogo(updatedCompany.getCompanyLogo());
        return companyRepository.save(company);
    }

    public void deleteCompany(UUID companyId) {
        List<JobOffer> companyJobs = jobRepository.findAllByCompanyId(companyId);
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.getFavoriteJobOffers().removeIf(companyJobs::contains);
        });
        jobRepository.deleteAll(getJobsByCompanyId(companyId));
        companyRepository.delete(getCompanyById(companyId));
    }

    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }

    public Boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }
}