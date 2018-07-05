package com.mng.hermes.repository;

import com.mng.hermes.model.Message;
import com.mng.hermes.model.Room;
import com.mng.hermes.model.TargetType;
import com.mng.hermes.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Initializer {

    public Initializer(RoomRepository roomRepository, UserRepository userRepository, MessageRepository messageRepository) {

        User user1 = new User("user1@email.com",
                "MickNane",
                "user1 intro",
                "John",
                "Doe",
                "",
                new HashSet<>(),
                new HashSet<>());
        User user2 = new User(
                "admin@example.com",
                "Keklord",
                "Hello!",
                "Admin",
                "Administras",
                "",
                new HashSet<>(),
                new HashSet<>());

        userRepository.save(user1);
        userRepository.save(user2);
        roomRepository.save(new Room("Room one", new HashSet<>()));
        roomRepository.save(new Room("Room two", new HashSet<>()));
        roomRepository.save(new Room("Room three", new HashSet<>()));
        messageRepository.save(new Message(user1, "Random message from user1", TargetType.ROOM, "Room one"));
        messageRepository.save(new Message(user1, "Also a message from user1", TargetType.ROOM, "Room one"));
        messageRepository.save(new Message(user1, "Another message from user1", TargetType.ROOM, "Room one"));
        messageRepository.save(new Message(user2, "Random message from user2", TargetType.ROOM, "Room one"));
        messageRepository.save(new Message(user2, "Also a message from user2", TargetType.ROOM, "Room one"));
        messageRepository.save(new Message(user2, "Another message from user2", TargetType.ROOM, "Room one"));

    }
}
