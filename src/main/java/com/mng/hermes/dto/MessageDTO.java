package com.mng.hermes.dto;

import com.mng.hermes.model.Message;

public class MessageDTO {
    private int id;
    private String content;

    private MessageDTO(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public static MessageDTO construct(Message message) {
        return new MessageDTO(message.getId(), message.getContent());
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
