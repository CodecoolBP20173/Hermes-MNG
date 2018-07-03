package com.mng.hermes.service;

import com.google.gson.*;
import com.mng.hermes.model.User;
import com.mng.hermes.repository.UserRepository;
import com.mng.hermes.util.HttpRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    private UserRepository userRepository;
    private HttpRequest http;
    private Gson gson;
    private User currentUser;

    public UserService(UserRepository userRepository, HttpRequest http, Gson gson) {
        this.userRepository = userRepository;
        this.http = http;
        this.gson = gson;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User loginUser(String token) {
        User user = userRepository.getUserByToken(token);
        if (user == null) {
            user = fetchUser(token);
        }
        currentUser = user;
        return user;
    }

    private User fetchUser(String token) {
        String data = http.fetchUserData(token);
        User user = gson.fromJson(data, User.class);
        User userInDb = userRepository.getUserByEmail(user.getEmail());
        if (userInDb == null) {
            user.setToken(token);
            userInDb = userRepository.save(user);
        } else {
            userInDb.setToken(token);
            userRepository.flush();
        }
        return userInDb;
    }
}
