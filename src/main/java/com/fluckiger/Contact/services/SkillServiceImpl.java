package com.fluckiger.Contact.services;

import com.fluckiger.Contact.DTO.SkillDTO;
import com.fluckiger.Contact.model.Person;
import com.fluckiger.Contact.model.Skill;
import com.fluckiger.Contact.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Override
    public List<SkillDTO> getSkills(){
        List<SkillDTO> skillDtos = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        skillRepository.findAll().forEach(skills::add);
        skills.stream().forEach(skill -> {
            ;
            skillDtos.add(skill.mapEntityToDto());
        });
        return skillDtos;
    }

    @Override
    public SkillDTO getSkillById(Long id){
        return skillRepository.findById(id).get().mapEntityToDto();
    }

    @Override
    public SkillDTO insert(Skill skill){
        return  skillRepository.save(skill).mapEntityToDto();
    }


    @Override
    public void updateSkill(Long id, Skill skill){
        Skill skilFromDb = skillRepository.findById(id).get();
        System.out.println(skilFromDb.toString());
        if(skill.getLevel() != null)skilFromDb.setLevel(skill.getLevel());
        if(skill.getName() != null)skilFromDb.setName(skill.getName());
        if(skill.getPerson() != null)skilFromDb.setPerson(skill.getPerson());
        skillRepository.save(skilFromDb);
    }

    @Override
    public void deleteSkill(Long skillId){
        skillRepository.deleteById(skillId);
    }

}
