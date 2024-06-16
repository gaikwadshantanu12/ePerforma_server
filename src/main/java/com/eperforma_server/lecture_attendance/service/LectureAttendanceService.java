package com.eperforma_server.lecture_attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eperforma_server.lecture_attendance.entity.LectureAttendanceEntity;
import com.eperforma_server.lecture_attendance.repository.LectureAttendanceRepository;

@Service
public class LectureAttendanceService {
    @Autowired
    LectureAttendanceRepository repository;

    public String saveAttendance(LectureAttendanceEntity entity) {
        if (repository.findBySubjectCode(entity.getSubjectCode()) != null) {
            try {
                repository.updateAttendance(entity.getAttendance(), entity.getSubjectCode());
                return "Attendance Updated Successfully!";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            repository.save(entity);
            return "Attendance Saved Successfully !";
        }
    }

    public LectureAttendanceEntity getAttendanceBySubjectCode(String subjectCode) {
        return repository.findBySubjectCode(subjectCode);
    }

}
