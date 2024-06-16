package com.eperforma_server.messages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eperforma_server.messages.entity.MessagesEntity;

@Repository
public interface MessagesRepository extends JpaRepository<MessagesEntity, Integer>{
    public List<MessagesEntity> findByTeacherID(String teacherID);
}
