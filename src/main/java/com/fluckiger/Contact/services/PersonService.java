package com.fluckiger.Contact.services;

import com.fluckiger.Contact.DTO.PersonDTO;
import com.fluckiger.Contact.model.Person;
import java.util.List;

public interface PersonService {
    List<PersonDTO> getPersons();

    PersonDTO getPersonById(Long id);

    PersonDTO insert(Person person);

    void updatePerson(Long id, Person person);

    void deletePerson(Long id);
}
