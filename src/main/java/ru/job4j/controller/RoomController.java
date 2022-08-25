package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public String findAll() {
        return "This is a Room's page.";
    }
/**
    @GetMapping
    public List<Room> findAll() {
        return rooms.findAll();
    }
*/
    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room) {
        if (room.getName() == null) {
            throw new NullPointerException("Field name must not be emtpy");
        }
        return new ResponseEntity<>(rooms.save(room), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        var room = rooms.findById(id);
        if (room.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room is not found");
        }
        return new ResponseEntity<>(room.get(), HttpStatus.OK);
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
