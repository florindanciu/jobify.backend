package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @PostMapping("/skills")
    public void addSkill(@RequestBody Skill skill) {
        skillRepository.save(skill);
    }

    @DeleteMapping("/skills/{skillId}")
    public void deleteSkill(@PathVariable UUID skillId) {
        Optional<Skill> skill = skillRepository.findById(skillId);
        skillRepository.delete(skill.get());
    }
}
