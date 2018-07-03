package com.mng.hermes.api;

import com.mng.hermes.model.TargetType;
import com.mng.hermes.service.MessageService;
import com.mng.hermes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/get-message")
    public ResponseEntity getMessages(@RequestParam("target") String target, @RequestParam("type") TargetType type) {
        int ownerId = userService.getCurrentUser().getId();
        if ((type == null && target!=null) ||(type != null && target==null) ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(messageService.getMessages(target, type, ownerId),HttpStatus.OK);

    }
}
