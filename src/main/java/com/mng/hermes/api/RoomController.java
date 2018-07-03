package com.mng.hermes.api;

import com.mng.hermes.model.Room;
import com.mng.hermes.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @RequestMapping("/rooms/{id}")
    public String joinRoom(@PathVariable String id) {
        return "You have join the to room: " + id;
    }

}
