package com.fluckiger.Contact.controllers;

import com.fluckiger.Contact.DTO.PersonDTO;
import com.fluckiger.Contact.model.Person;
import com.fluckiger.Contact.services.PersonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    //The function receives a GET request, processes it and gives back a list of person as a response.
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> persons = personService.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a skill as a response.
    @GetMapping({"/{personId}"})
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long skillId) {
        return new ResponseEntity<>(personService.getPersonById(skillId), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new skill and saves it to the database, and returns a resource link to the created Person.
    @PostMapping(value = "/", consumes = {"application/json"})
    public ResponseEntity<PersonDTO> savePerson(@RequestBody Person person) {
        PersonDTO person1 = personService.insert(person);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("person", "/api/v1/person/" + person1.getId().toString());
        return new ResponseEntity<>(person1, httpHeaders, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Person with the specified Id and returns the updated Person
    @PutMapping({"/{personId}"})
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("personId") Long personId, @RequestBody Person person) {
        personService.updatePerson(personId, person);
        return new ResponseEntity<>(personService.getPersonById(personId), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the skill with the specified Id.
    @DeleteMapping({"/{personId}"})
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
