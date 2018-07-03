package com.mng.hermes.api;

import com.mng.hermes.dto.UserDTO;
import com.mng.hermes.model.User;
import com.mng.hermes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> validateUser(@RequestHeader(value = "Authorization") String auth){
        try {
            User user = userService.loginUser(auth);
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateProfile(
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "introduction") String introduction
            ) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.updateProfile(nickname, introduction);
        return new ResponseEntity<>(new UserDTO(currentUser), HttpStatus.OK);
    }
}
