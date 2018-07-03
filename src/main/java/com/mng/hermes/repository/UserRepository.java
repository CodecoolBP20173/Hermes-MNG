package com.mng.hermes.repository;

import com.mng.hermes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "user")
public interface UserRepository extends JpaRepository<User,Integer> {
}
