package com.eperforma_server.messages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class MessagesEntity {
    @Column(name = "message_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int messageID;

    @Column(name = "teacher_id")
    public String teacherID;

    @Column(name = "message")
    public String message;

    @Column(name = "date")
    public String date;

    @Column(name = "time")
    public String time;
}
