package ru.job4j.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null",
            groups = {Operation.OnUpdate.class})
    private int id;
    @NotBlank(message = "Name must be not null",
    groups = {Operation.OnCreate.class})
    private String name;
    @NotNull(message = "Email must be not null",
            groups = {Operation.OnCreate.class})
    private String email;
    @Min(message = "Password must be minimum 5 chapters", value = 5L,
            groups = {Operation.OnCreate.class})
    private String password;
    @ManyToMany
    @JoinTable(name = "person_role",
            joinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false,
                    updatable = false)}
    )
    private Set<Role> roles;
}
