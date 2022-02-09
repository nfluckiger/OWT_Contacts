package com.fluckiger.Contact.model;
import com.fluckiger.Contact.DTO.SkillDTO;
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
public class Skill {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String name;
    @Column
    SkillLevel level;
    @Builder.Default
    @ManyToMany
    Set<Person> person = new HashSet<>();

    @Override
    public String toString(){
        return name + " " +level;
    }

    public SkillDTO mapEntityToDto(){
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(this.getId());
        skillDTO.setName(this.getName());
        skillDTO.setLevel(this.getLevel());
        skillDTO.setPersons(this.getPerson().stream().map(Person::getFullname).collect(Collectors.toSet()));
        return skillDTO;
    }
}
