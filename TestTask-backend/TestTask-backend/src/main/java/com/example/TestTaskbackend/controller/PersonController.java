package com.example.TestTaskbackend.controller;

import com.example.TestTaskbackend.model.Person;
import com.example.TestTaskbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("persons")
    public List<Person> getPersons(){
        return this.personRepository.findAll();
    }
    @PostMapping("persons")
    Person newPerson(@RequestBody Person newPerson) {
        return this.personRepository.save(newPerson);
    }
    // Single item

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id) {

        return this.personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return this.personRepository.findById(id)
                .map(employee -> {
                    employee.setName(newPerson.getName());
                    employee.setUrl(newPerson.getUrl());
                    return this.personRepository.save(employee);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return this.personRepository.save(newPerson);
                });
    }

    @DeleteMapping("/persons/{id}")
    void deletePerson(@PathVariable Long id) {
        this.personRepository.deleteById(id);
    }
}
