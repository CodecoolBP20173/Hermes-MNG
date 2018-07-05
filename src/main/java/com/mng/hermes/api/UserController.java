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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> validateUser(@RequestHeader(value = "Authorization") String auth){
        try {
            User user = userService.loginUser(auth);
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO user) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.updateProfile(user.getNickName(), user.getIntroduction());
        return new ResponseEntity<>(new UserDTO(currentUser), HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void logoutUser(@RequestHeader(value="Authorization") String authorization){
        userService.logoutUser(authorization);

    }
}
