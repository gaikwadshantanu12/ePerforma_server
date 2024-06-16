package com.eperforma_server.lecture_attendance.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eperforma_server.lecture_attendance.entity.LectureAttendanceEntity;
import com.eperforma_server.lecture_attendance.service.LectureAttendanceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lectureAttendance")
public class LectureAttendanceController {
    @Autowired
    LectureAttendanceService service;

    @PostMapping("/pushAttendance")
    public String saveAttendance(@RequestBody LectureAttendanceEntity entity) {
        return service.saveAttendance(entity);
    }

    @PostMapping("/getAttendance")
    public LectureAttendanceEntity getAttendance(@RequestBody Map<String, String> requestBody) {
        return service.getAttendanceBySubjectCode(requestBody.get("subjectCode"));
    }

}
