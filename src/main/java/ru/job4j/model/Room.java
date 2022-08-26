package ru.job4j.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null",
            groups = {Operation.OnUpdate.class})
    int id;
    @NotBlank(message = "Name must be not null",
            groups = {Operation.OnCreate.class})
    String name;
    @OneToMany
    List<Message> messages;
}
