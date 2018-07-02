package com.mng.hermes.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private Set<User> admins;

    public String getName() {
        return name;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admins=" + admins +
                '}';
    }
}
