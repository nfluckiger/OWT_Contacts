package com.fluckiger.Contact.repository;

import com.fluckiger.Contact.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {
}
