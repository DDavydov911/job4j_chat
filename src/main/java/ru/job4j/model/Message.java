package ru.job4j.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person from;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room to;
    private String text;
    private Calendar created;
}
