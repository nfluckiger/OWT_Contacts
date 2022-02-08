package com.fluckiger.Contact.services;

import com.fluckiger.Contact.DTO.SkillDTO;
import com.fluckiger.Contact.model.Skill;

import java.util.List;

public interface SkillService {
    List<SkillDTO> getSkills();

    SkillDTO getSkillById(Long id);

    SkillDTO insert(Skill skill);

    void updateSkill(Long id, Skill skill);

    void deleteSkill(Long id);
}
