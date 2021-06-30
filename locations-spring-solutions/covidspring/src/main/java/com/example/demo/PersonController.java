package com.example.demo;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/covid")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{taj}")
    public PersonDto findByTaj(@PathVariable String taj) {
        return personService.findByTaj(taj);
    }
}
