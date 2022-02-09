package com.fluckiger.Contact.services;

import com.fluckiger.Contact.DTO.SkillDTO;
import com.fluckiger.Contact.model.Skill;
import com.fluckiger.Contact.model.SkillLevel;
import com.fluckiger.Contact.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class skillServiceTests {

    @Mock
    SkillRepository skillRepository;

    @InjectMocks
    SkillServiceImpl skillService;


    @Test
    public void insertShouldAddSkill(){
        Skill skill = new Skill();

        skill.setName("Dance");
        skill.setLevel(SkillLevel.INTERMEDIATE);

        when(skillRepository.save(ArgumentMatchers.any(Skill.class))).thenReturn(skill);

        SkillDTO created = skillService.insert(skill);

        assertThat(created.getName()).isSameAs(skill.getName());
        verify(skillRepository).save(skill);
    }

    @Test
    public void getSkillsShouldGetAllSkillInDTO(){
        List<Skill> sList = new ArrayList<>();
        Skill skill = new Skill();
        skill.setName("Dance");
        skill.setLevel(SkillLevel.INTERMEDIATE);

        sList.add(skill);
        List<SkillDTO> expected = new ArrayList();
        expected.add(skill.mapEntityToDto());

        when(skillRepository.findAll()).thenReturn(sList);

        List<SkillDTO> created = skillService.getSkills();

        assertEquals(expected.size(), created.size());
        verify(skillRepository).findAll();
    }

    @Test
    public void getPersonByIDShouldReturnPersonDTO() {
        Skill skill = new Skill();
        skill.setName("Dance");
        skill.setLevel(SkillLevel.INTERMEDIATE);;
        SkillDTO expected = skill.mapEntityToDto();

        when(skillRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(skill));

        SkillDTO created = skillService.getSkillById(1l);

        assertEquals(expected.getName(), created.getName());
        verify(skillRepository).findById(ArgumentMatchers.any(Long.class));
    }

    @Test
    public void updateShouldUpdateSkill() {
        Skill skill = new Skill();
        skill.setName("Dance");
        skill.setLevel(SkillLevel.INTERMEDIATE);

        Skill updateSkill = new Skill();
        updateSkill.setName("Test");

        when(skillRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(skill));

        skillService.updateSkill(1l,updateSkill);

        assertThat(skill.getName()).isEqualTo("Test");
        verify(skillRepository).findById(ArgumentMatchers.any(Long.class));
    }

    @Test
    public void updateShouldNotUpdateSkillIfValueNull() {
        Skill skill = new Skill();
        skill.setName("Dance");
        skill.setLevel(SkillLevel.INTERMEDIATE);

        Skill updateSkill = new Skill();
        updateSkill.setName("Test");

        when(skillRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(skill));

        skillService.updateSkill(1l,updateSkill);

        assertEquals(skill.getLevel(), SkillLevel.INTERMEDIATE);
        verify(skillRepository).findById(ArgumentMatchers.any(Long.class));
    }
}
