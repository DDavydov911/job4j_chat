package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Message;
import ru.job4j.model.Room;
import ru.job4j.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService rooms;

    public RoomController(RoomService rooms) {
        this.rooms = rooms;
    }

    @GetMapping
    public List<Room> findAll() {
        return rooms.findAll();
    }

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return new ResponseEntity<>(rooms.save(room), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        var room = rooms.findById(id);
        return new ResponseEntity<>(room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        this.rooms.save(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Room room = new Room();
        room.setId(id);
        this.rooms.delete(room);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Room> sendMessage(@PathVariable int id, @RequestBody Message message) {
        Room room = rooms.findById(id).get();
        room.getMessages().add(message);
        return new ResponseEntity<>(
                rooms.save(room),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}/message")
    public ResponseEntity<Message> findMessage(@PathVariable int id, @RequestBody Message message) {
        Message message1 = rooms.findById(id).get().getMessages().get(message.getId());
        return new ResponseEntity<>(
                message1,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/message")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        Room room = rooms.findById(id).get();
        Message message1 = room.getMessages().set(message.getId(), message);
        rooms.save(room);
        return new ResponseEntity<>(
                message1,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}/message")
    public ResponseEntity<Room> deleteMessage(@PathVariable int id, @RequestBody Message message) {
        Room room = rooms.findById(id).get();
        room.getMessages().remove(message);
        return new ResponseEntity<>(
                rooms.save(room),
                HttpStatus.OK
        );
    }
}