package ru.job4j.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @OneToMany
    List<Message> messages;
}
