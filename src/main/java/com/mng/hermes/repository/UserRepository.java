package com.mng.hermes.repository;

import com.mng.hermes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByToken(String token);
    User getUserByEmail(String email);

}
