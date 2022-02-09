package com.fluckiger.Contact.services;

import com.fluckiger.Contact.DTO.PersonDTO;
import com.fluckiger.Contact.model.Person;
import com.fluckiger.Contact.repository.PersonRepository;
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
public class personServiceTests {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    public void insertShouldAddPerson() {
        Person testPerson = new Person();
        testPerson.setFirstname("Nathan");
        testPerson.setLastname("Fluckiger");
        testPerson.setAddress("Les replans 713");
        testPerson.setFullname("Nathan Fluckiger");
        testPerson.setEmail("nathan.fluckiger@hotmail.ch");
        testPerson.setPassword("P@ssw0rd");


        when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(testPerson);

        PersonDTO created = personService.insert(testPerson);

        assertThat(created.getFullname()).isSameAs(testPerson.getFullname());
        verify(personRepository).save(testPerson);
    }

    @Test
    public void getPersonsShouldReturnPersonDTOList() {
        List<Person> pList= new ArrayList<>();
        Person testPerson = new Person();
        testPerson.setFirstname("Nathan");
        testPerson.setLastname("Fluckiger");
        testPerson.setAddress("Les replans 713");
        testPerson.setFullname("Nathan Fluckiger");
        testPerson.setEmail("nathan.fluckiger@hotmail.ch");
        testPerson.setPassword("P@ssw0rd");
        pList.add(testPerson);
        List<PersonDTO> expected = new ArrayList<>();
        expected.add(testPerson.mapEntityToDto());

        when(personRepository.findAll()).thenReturn(pList);

        List<PersonDTO> created = personService.getPersons();

        assertEquals(expected.size(), created.size());
        verify(personRepository).findAll();
    }

    @Test
    public void getPersonByIDShouldReturnPersonDTO() {
        Person testPerson = new Person();
        testPerson.setFirstname("Nathan");
        testPerson.setLastname("Fluckiger");
        testPerson.setAddress("Les replans 713");
        testPerson.setFullname("Nathan Fluckiger");
        testPerson.setEmail("nathan.fluckiger@hotmail.ch");
        testPerson.setPassword("P@ssw0rd");
        PersonDTO expected = testPerson.mapEntityToDto();

        when(personRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(testPerson));

        PersonDTO created = personService.getPersonById(1l);

        assertEquals(expected.getFullname(), created.getFullname());
        verify(personRepository).findById(ArgumentMatchers.any(Long.class));
    }

    @Test
    public void updateShouldUpdatePerson() {
        Person testPerson = new Person();
        testPerson.setFirstname("Nathan");
        testPerson.setLastname("Fluckiger");
        testPerson.setAddress("Les replans 713");
        testPerson.setFullname("Nathan Fluckiger");
        testPerson.setEmail("nathan.fluckiger@hotmail.ch");
        testPerson.setPassword("P@ssw0rd");

        Person updatePerson = new Person();
        updatePerson.setFirstname("Test");

        when(personRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(testPerson));

        personService.updatePerson(1l,updatePerson);

        assertThat(testPerson.getFirstname()).isEqualTo("Test");
        verify(personRepository).save(testPerson);
    }

    @Test
    public void updateShouldNotUpdatePersonIfValueNull() {
        Person testPerson = new Person();
        testPerson.setFirstname("Nathan");
        testPerson.setLastname("Fluckiger");
        testPerson.setAddress("Les replans 713");
        testPerson.setFullname("Nathan Fluckiger");
        testPerson.setEmail("nathan.fluckiger@hotmail.ch");
        testPerson.setPassword("P@ssw0rd");

        Person updatePerson = new Person();
        updatePerson.setFirstname("Test");

        when(personRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(testPerson));

        personService.updatePerson(1l,updatePerson);

        assertThat(testPerson.getLastname()).isEqualTo("Fluckiger");
        verify(personRepository).save(testPerson);
    }
}

