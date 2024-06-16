package com.eperforma_server.lecture_attendance.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureAttendanceModel {
    public int attendID;
    public String subjectCode;
    public Map<String, Map<String, Map<String, Integer>>> attendance;
}
