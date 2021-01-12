package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.exception.UserNotFoundException;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
    }

    public Set<JobOffer> getFavoriteJobs(UUID id) {
        User user = getUserById(id);
        return user.getFavoriteJobOffers();
    }

    public Set<JobOffer> getAppliedJobs(UUID id) {
        User user = getUserById(id);
        return user.getAppliedJobs();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User updateUserById(UUID id, User updatedUser) {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setRole(updatedUser.getRole());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

       return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.delete(getUserById(id));
    }
}
