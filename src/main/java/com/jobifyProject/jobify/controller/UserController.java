package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.JobOfferConverter;
import com.jobifyProject.jobify.converter.UserConverter;
import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.dto.UserDto;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JobOfferConverter jobOfferConverter;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return userConverter.modelToDto(users);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return userConverter.modelToDto(user);
    }

    @GetMapping("/users/{id}/favoriteJobs")
    public Set<JobOfferDto> getFavoriteJobs(@PathVariable UUID id) {
        Set<JobOffer> jobOffers = userService.getFavoriteJobs(id);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @GetMapping("/users/{id}/appliedJobs")
    public Set<JobOfferDto> getAppliedJobs(@PathVariable UUID id) {
        Set<JobOffer> jobOffers = userService.getAppliedJobs(id);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody UserDto userDto) {
        User user = userConverter.dtoToModel(userDto);
        userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUserById(@PathVariable UUID id, @RequestBody UserDto userDto) {
        User user = userConverter.dtoToModel(userDto);
        User updatedUser = userService.updateUserById(id, user);

        return userConverter.modelToDto(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

