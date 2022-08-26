package ru.job4j.repository;

import org.springframework.stereotype.Component;
import ru.job4j.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {
    private final ConcurrentHashMap<String, Person> users = new ConcurrentHashMap<>();

    public UserStore() {
        Person person = new Person();
        person.setName("Ivan");
        person.setPassword("123");
        this.users.put(person.getName(), person);
    }

    public Person save(Person person) {
        users.put(person.getName(), person);
        return person;
    }

    public Person findByName(String username) {
        return users.get(username);
    }

    public List<Person> findAll() {
        return new ArrayList<>(users.values());
    }

    public void delete(Person person) {
        users.remove(person.getName());
    }

    public Optional<Person> findById(int id) {
        for (Person value : users.values()) {
            if (value.getId() == id) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}

