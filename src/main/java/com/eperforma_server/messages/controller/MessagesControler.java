package com.eperforma_server.messages.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eperforma_server.messages.entity.MessagesEntity;
import com.eperforma_server.messages.service.MessagesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/messages")
public class MessagesControler {
    @Autowired
    MessagesService service;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody MessagesEntity entity) {
        service.sendMessage(entity);
    }

    @PostMapping("/getMessages")
    public List<MessagesEntity> getMessages(@RequestBody Map<String, String> requestBody) {
        return service.getMessages(requestBody.get("teacherCollegeID"));
    }
}
