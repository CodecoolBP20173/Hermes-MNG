package com.mng.hermes.repository;

import com.mng.hermes.model.Room;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    public Initializer(RoomRepository roomRepository) {
        roomRepository.save(new Room("Room one"));
        roomRepository.save(new Room("Room two"));
        roomRepository.save(new Room("Room three"));

    }
}
