package com.fluckiger.Contact.bootstrap;

import com.fluckiger.Contact.model.Skill;
import com.fluckiger.Contact.model.SkillLevel;
import com.fluckiger.Contact.model.*;
import com.fluckiger.Contact.repository.PersonRepository;
import com.fluckiger.Contact.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner{
    public final SkillRepository skillRepository;
    public final PersonRepository personRepository;
    private Skill stest;

    public DataLoader(SkillRepository skillRepository, PersonRepository personRepository) {
        this.skillRepository = skillRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSkills();
        loadPerson();
    }

    private void loadSkills() {
        if (skillRepository.count() == 0) {
            stest= Skill.builder()
                    .name("Dance")
                    .level(SkillLevel.BEGINNER)
                    .build();
            skillRepository.save(stest);
            skillRepository.save(
                    Skill.builder()
                            .name("Dance")
                            .level(SkillLevel.INTERMEDIATE)
                            .build()
            );
            skillRepository.save(
                    Skill.builder()
                            .name("Dance")
                            .level(SkillLevel.MASTER)
                            .build()
            );
            System.out.println("Sample Skills Loaded");
        }
    }
    private void loadPerson() {
        if (personRepository.count() == 0) {
            Person test = Person.builder()
                    .firstname("Dalia")
                    .lastname("Danseuse")
                    .fullname("Dalia Danseuse")
                    .address("Rue de la terre 2, 1 la terre")
                    .email("dalia.danseuse@email.com")
                    .password("p@ssw0rd")
                    .build();
            test.getSkills().add(stest);
            personRepository.save(test);
            personRepository.save(
                    Person.builder()
                            .firstname("Maria")
                            .lastname("DanseusePLus")
                            .fullname("Maria DanseusePlus")
                            .address("Rue de la terre 3, 1 la terre")
                            .email("maria-danseuseplus@email.com")
                            .password("p@ssw0rd")
                            .build()
            );
            System.out.println("Sample Persons Loaded");
        }
    }
}
