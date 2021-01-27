package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.model.UserDetailsImpl;
import com.jobifyProject.jobify.repository.CompanyRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        Company company = companyRepository.findByName(username);

        if (user != null) {
            return UserDetailsImpl.buildUser(user);
        }
        return UserDetailsImpl.buildCompany(company);
    }
}
