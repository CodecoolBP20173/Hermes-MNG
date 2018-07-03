package com.mng.hermes.repository;


import com.mng.hermes.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "room")
public interface RoomRepository extends JpaRepository<Room,Integer> {
}
