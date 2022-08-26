package ru.job4j.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null",
            groups = {Operation.OnUpdate.class})
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person from;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotBlank(message = "Name must be not null",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class,
            Operation.OnDelete.class})
    private Room to;
    @NotBlank(message = "Name must be not null",
            groups = {Operation.OnCreate.class})
    private String text;
    private Calendar created;
}
