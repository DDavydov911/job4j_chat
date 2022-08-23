package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Person;
import ru.job4j.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService persons;
    private final BCryptPasswordEncoder encoder;

    public PersonController(PersonService personService, BCryptPasswordEncoder encoder) {
        this.persons = personService;
        this.encoder = encoder;
    }
/**
    @GetMapping
    public ResponseEntity getPersons() {
        return ResponseEntity.ok("Сервер работает!");
    }
*/
    @GetMapping
    public List<Person> getPersons() {
        return persons.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.persons.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        return new ResponseEntity<>(
                this.persons.save(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        this.persons.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        this.persons.delete(person);
        return ResponseEntity.ok().build();
    }
}
