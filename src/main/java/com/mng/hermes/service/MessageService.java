package com.mng.hermes.service;

import com.mng.hermes.dto.MessageDTO;
import com.mng.hermes.model.TargetType;
import com.mng.hermes.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDTO> getMessages(String targetAddress, TargetType type, int ownerId) {
        if (targetAddress == null && type == null) {
            return messageRepository.findAllByTargetAddressIsNull().stream()
                    .map(MessageDTO::construct)
                    .collect(Collectors.toList());
        }
        if (type.equals(TargetType.ROOM)) {
            return messageRepository.findAllByTargetAddressEquals(targetAddress).stream()
                    .map(MessageDTO::construct)
                    .collect(Collectors.toList());
        }else{
            return messageRepository.findAllByTargetAddressEqualsAndOwnerIdEquals(targetAddress, ownerId).stream()
                    .map(MessageDTO::construct)
                    .collect(Collectors.toList());
        }
    }
}
