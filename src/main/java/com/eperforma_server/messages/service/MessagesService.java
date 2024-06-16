package com.eperforma_server.messages.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eperforma_server.messages.entity.MessagesEntity;
import com.eperforma_server.messages.repository.MessagesRepository;

@Service
public class MessagesService {
    @Autowired
    MessagesRepository repository;

    /* send message */
    public void sendMessage(MessagesEntity entity) {
        repository.save(entity);
    }

    /* Get all students by department, year and section */
	public List<MessagesEntity> getMessages(String teacherID) {
		List<MessagesEntity> messages = repository.findByTeacherID(teacherID);
        System.out.println("Messages - " + messages.size());
        return messages;
	}
}
