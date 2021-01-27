package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {

    List<Skill> findSkillsByUserIs(User user);
}
