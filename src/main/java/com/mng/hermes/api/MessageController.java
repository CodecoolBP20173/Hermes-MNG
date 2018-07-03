package com.mng.hermes.api;

import com.mng.hermes.dto.MessageDTO;
import com.mng.hermes.model.TargetType;
import com.mng.hermes.model.User;
import com.mng.hermes.service.MessageService;
import com.mng.hermes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getMessages(
            @RequestParam(value = "target", required = false) String target,
            @RequestParam(value = "type", required = false) TargetType type
    ) {
        System.out.println(type);
        User user = userService.getCurrentUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if ((type == null && target != null) || (type != null && target == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(messageService.getMessages(target, type, user.getId()), HttpStatus.OK);
    }
}
