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
            SkillDTO s = mapEntityToDto(skill);
            skillDtos.add(s);
        });
        return skillDtos;
    }

    @Override
    public SkillDTO getSkillById(Long id){
        return mapEntityToDto(skillRepository.findById(id).get());
    }

    @Override
    public SkillDTO insert(Skill skill){
        return  mapEntityToDto(skillRepository.save(skill));
    }


    @Override
    public void updateSkill(Long id, Skill skill){
        Skill skilFromDb = skillRepository.findById(id).get();
        System.out.println(skilFromDb.toString());
        skilFromDb.setLevel(skill.getLevel());
        skilFromDb.setName(skill.getName());
        skillRepository.save(skilFromDb);
    }

    @Override
    public void deleteSkill(Long skillId){
        skillRepository.deleteById(skillId);
    }

    private SkillDTO mapEntityToDto(Skill skill){
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(skill.getId());
        skillDTO.setName(skill.getName());
        skillDTO.setLevel(skill.getLevel());
        skillDTO.setPersons(skill.getPerson().stream().map(Person::getFullname).collect(Collectors.toSet()));
        return skillDTO;
    }
}
