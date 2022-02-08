package com.fluckiger.Contact.DTO;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDTO {
    Long id;
    String firstname;
    String lastname;
    String fullname;
    String address;
    Set<String> skills = new HashSet<>();
}
