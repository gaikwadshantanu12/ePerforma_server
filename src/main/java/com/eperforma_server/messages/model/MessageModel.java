package com.eperforma_server.messages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MessageModel {
    public int messageID;
    public String teacherID;
    public String message;
    public String date;
    public String time;
}
