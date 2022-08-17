package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Message;
import ru.job4j.model.Room;
import ru.job4j.repository.MessageRepository;
import ru.job4j.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    public RoomService(RoomRepository roomRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
    }

    public List<Room> findAll() {
        List<Room> res = new ArrayList<>();
        roomRepository.findAll().forEach(res::add);
        return res;
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }


}
