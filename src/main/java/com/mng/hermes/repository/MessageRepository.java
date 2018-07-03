package com.mng.hermes.repository;


import com.mng.hermes.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "message")
public interface MessageRepository extends JpaRepository<Message,Integer> {
}
