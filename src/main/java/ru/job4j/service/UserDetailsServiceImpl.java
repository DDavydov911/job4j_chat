package ru.job4j.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.UserStore;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
/**    private final PersonRepository personRepository; */
    private final UserStore personRepository;

    public UserDetailsServiceImpl(UserStore personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getName(), user.getPassword(), emptyList());
    }
}