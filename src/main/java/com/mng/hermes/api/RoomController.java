package com.mng.hermes.api;

import com.mng.hermes.model.Room;
import com.mng.hermes.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public String joinRoom(@PathVariable String id) {
        return "You have join to room: " + id;
    }

}
