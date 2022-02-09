package com.fluckiger.Contact.services;

import com.fluckiger.Contact.DTO.PersonDTO;
import com.fluckiger.Contact.model.Person;
import com.fluckiger.Contact.model.Skill;
import com.fluckiger.Contact.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{

    PersonRepository personRepository;
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDTO> getPersons(){
        List<PersonDTO> personDTOs = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        persons.stream().forEach(person -> {
            PersonDTO p = mapEntityToDto(person);
            personDTOs.add(p);
        });
        return personDTOs;
    }

    @Override
    public PersonDTO getPersonById(Long id){
        return mapEntityToDto(personRepository.findById(id).get());
    }

    @Override
    public PersonDTO insert(Person person){
        return  mapEntityToDto(personRepository.save(person));
    }


    @Override
    public void updatePerson(Long id, Person person){
        Person personFromDb = personRepository.findById(id).get();
        System.out.println(personFromDb.toString());
        if(person.getFirstname() != null){personFromDb.setFirstname(person.getFirstname());}
        if(person.getLastname() != null){personFromDb.setLastname(person.getLastname());}
        if(person.getFullname() != null){personFromDb.setFullname(person.getFullname());}
        if(person.getEmail() != null){personFromDb.setEmail(person.getEmail());}
        if(person.getPassword() != null){personFromDb.setPassword(person.getPassword());}
        if(person.getAddress() != null){personFromDb.setAddress(person.getAddress());}
        personFromDb.setSkills(person.getSkills());
        personRepository.save(personFromDb);
    }

    @Override
    public void deletePerson(Long personId){
        personRepository.deleteById(personId);
    }

    private PersonDTO mapEntityToDto(Person person){
        PersonDTO personDto = new PersonDTO();
        personDto.setId(person.getId());
        personDto.setFirstname(person.getFirstname());
        personDto.setLastname(person.getLastname());
        personDto.setFullname(person.getFullname());
        personDto.setAddress(person.getAddress());
        personDto.setSkills(person.getSkills().stream().map(Skill::toString).collect(Collectors.toSet()));
        return personDto;
    }
}
