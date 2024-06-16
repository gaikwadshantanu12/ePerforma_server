package com.eperforma_server.lecture_attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.eperforma_server.lecture_attendance.entity.LectureAttendanceEntity;

public interface LectureAttendanceRepository extends JpaRepository<LectureAttendanceEntity, Integer>{
    public LectureAttendanceEntity findBySubjectCode(String subjectCode);

    @Modifying
    @Transactional
    @Query(value = "UPDATE lecture_attendance SET student_attendance = :students_attendance WHERE subject_code = :subject_code", nativeQuery = true)
    public void updateAttendance(@Param("students_attendance") String studentsAttendance, @Param("subject_code") String subjectCode);
}
