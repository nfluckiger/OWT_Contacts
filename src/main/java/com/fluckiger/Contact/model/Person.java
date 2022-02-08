package com.fluckiger.Contact.model;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

}
