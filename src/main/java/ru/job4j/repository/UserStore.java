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
        Person user = new Person();
        user.setId(1);
        user.setPassword("123");
        user.setName("Ivan");
        users.put(user.getName(), user);
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

    public Optional<Person> findById(int id) {
        return Optional.of(users.get(id));
    }

    public void delete(Person person) {
        users.remove(person.getName());
    }
}
