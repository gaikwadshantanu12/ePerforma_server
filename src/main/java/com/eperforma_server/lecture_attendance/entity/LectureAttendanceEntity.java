package com.eperforma_server.lecture_attendance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecture_attendance")
public class LectureAttendanceEntity {
    @Column(name = "attendance_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int attendID;

    @Column(name = "subject_code")
    public String subjectCode;

    @Column(name = "student_attendance")
    public String attendance;
}
