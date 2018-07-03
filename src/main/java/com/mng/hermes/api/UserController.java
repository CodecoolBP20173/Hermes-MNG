package com.mng.hermes.api;

import com.mng.hermes.dto.UserDTO;
import com.mng.hermes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDTO validateUser(@RequestHeader(value="Authorization") String authorization){
        return new UserDTO(userService.loginUser(authorization));
    }
}
