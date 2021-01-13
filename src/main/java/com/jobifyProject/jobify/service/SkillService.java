package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.SkillRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    private Set<Skill> findSkillsOfUser(UUID userId){
        Optional<User> user = userRepository.findById(userId);
        return skillRepository.findSkillsByUserIs(user.get());
    }
}
