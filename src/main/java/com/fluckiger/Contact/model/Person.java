package com.fluckiger.Contact.model;
import com.fluckiger.Contact.DTO.PersonDTO;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String firstname;
    @Column
    String lastname;
    @Column
    String fullname;
    @Column
    String address;
    @Column
    String email;
    @Column
    String password;
    @Builder.Default
    @ManyToMany
    Set<Skill> skills = new HashSet<>();

    public PersonDTO mapEntityToDto(){
        PersonDTO personDto = new PersonDTO();
        personDto.setId(this.getId());
        personDto.setFirstname(this.getFirstname());
        personDto.setLastname(this.getLastname());
        personDto.setFullname(this.getFullname());
        personDto.setAddress(this.getAddress());
        personDto.setSkills(this.getSkills().stream().map(Skill::toString).collect(Collectors.toSet()));
        return personDto;
    }

}
