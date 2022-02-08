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
}
