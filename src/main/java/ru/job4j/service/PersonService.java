package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import ru.job4j.repository.UserStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
/**    private final PersonRepository repository; */
    private final UserStore repository;

    public PersonService(UserStore repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        List<Person> res = new ArrayList<>();
        repository.findAll().forEach(res::add);
        return res;
    }

    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void delete(Person person) {
        repository.delete(person);
    }

    public boolean findByName(String name) {
        return repository.findByName(name) != null;
    }
}
