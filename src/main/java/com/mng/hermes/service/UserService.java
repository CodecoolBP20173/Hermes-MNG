package com.mng.hermes.service;

import com.mng.hermes.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private User currentUser = new User(
            "admin@example.com",
            "Keklord",
            "Hello!",
            "Admin",
            "Administras",
            "",
            new HashSet<>(),
            new HashSet<>()
    );

    public User getCurrentUser() {
        return currentUser;
    }

    public User loginUser(String token) {
        return currentUser;
    }

}
