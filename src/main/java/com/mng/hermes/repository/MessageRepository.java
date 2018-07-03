package com.mng.hermes.repository;


import com.mng.hermes.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(path = "message")
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByTargetAddressEquals(String targetAddress);
    List<Message> findAllByTargetAddressIsNull();
    List<Message> findAllByTargetAddressEqualsAndOwnerIdEquals(String targetAddress, int ownerId);
}
