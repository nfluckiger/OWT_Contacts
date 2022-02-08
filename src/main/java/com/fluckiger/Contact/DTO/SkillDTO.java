package com.fluckiger.Contact.DTO;
import java.util.HashSet;
import java.util.Set;

import com.fluckiger.Contact.model.SkillLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SkillDTO {
    Long id;
    String name;
    SkillLevel level;
    Set<String> persons = new HashSet<>();
}
