package com.fluckiger.Contact.controllers;

import com.fluckiger.Contact.DTO.SkillDTO;
import com.fluckiger.Contact.model.Skill;
import com.fluckiger.Contact.services.SkillService;
import com.fluckiger.Contact.utility.dataValidator;
import com.fluckiger.Contact.utility.personValidator;
import com.fluckiger.Contact.utility.skillValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/skill")
public class SkillController {
    SkillService skillService;
    dataValidator validator = new skillValidator();

    public SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    //The function receives a GET request, processes it and gives back a list of skill as a response.
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a skill as a response.
    @GetMapping({"/{skillId}"})
    public ResponseEntity<SkillDTO> getSkill(@PathVariable Long skillId) {
        return new ResponseEntity<>(skillService.getSkillById(skillId), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new skill and saves it to the database, and returns a resource link to the created skill.
    @PostMapping(value = "/", consumes = {"application/json"})
    public ResponseEntity<SkillDTO> saveSkill(@RequestBody Skill skill) {
        if(!validator.validation(skill)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SkillDTO skill1 = skillService.insert(skill);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("skill", "/api/v1/skill/" + skill1.getId().toString());
        return new ResponseEntity<>(skill1, httpHeaders, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the skill with the specified Id and returns the updated skill
    @PutMapping({"/{skillId}"})
    public ResponseEntity<SkillDTO> updateTodo(@PathVariable("skillId") Long skillId, @RequestBody Skill skill) {
        if(!validator.validation(skill)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        skillService.updateSkill(skillId, skill);
        return new ResponseEntity<>(skillService.getSkillById(skillId), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the skill with the specified Id.
    @DeleteMapping({"/{skillId}"})
    public ResponseEntity<SkillDTO> deleteTodo(@PathVariable("skillId") Long skillId) {
        skillService.deleteSkill(skillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
