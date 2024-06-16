package com.eperforma_server.subjects.entity;

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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class SubjectEntity {
    @Column(name = "subject_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;

    @Column(name = "subject_name")
    public String subjectName;

    @Column(name = "subject_code")
    public String subjectCode;

    @Column(name = "teacher_name")
    public String teacherName;

    @Column(name = "teacher_id")
    public String teacherID;

    @Column(name = "department")
    public String department;

    @Column(name = "semester")
    public String semester;

    @Column(name = "year")
    public String year;

    @Column(name = "section")
    public String section;
}
