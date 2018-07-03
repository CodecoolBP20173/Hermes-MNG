package com.mng.hermes.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    @RequestMapping("/room/{name}")
    public String joinRoom(@PathVariable String name) {
        return "You have join the to room: " + name;
    }

}
