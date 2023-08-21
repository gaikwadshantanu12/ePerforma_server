package com.eperforma_server.teacher.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher_info")
public class TeacherDetailsEntity {
    
    @Column(name = "teacher_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ID;
	
	@Column(name = "teacher_college_id")
	public String teacherCollegeID;
	
	@Column(name = "teacher_name")
	public String teacherName;
	
	@Column(name = "teacher_email")
	public String teacherEmail;
	
	@Column(name = "teacher_password")
	public String teacherPassword;
	
	@Column(name = "teacher_department")
	public String teacherDepartment;

    public TeacherDetailsEntity() {
    }

    public TeacherDetailsEntity(String teacherCollegeID, String teacherName, String teacherEmail,
            String teacherPassword, String teacherDepartment) {
        this.teacherCollegeID = teacherCollegeID;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
        this.teacherDepartment = teacherDepartment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getTeacherCollegeID() {
        return teacherCollegeID;
    }

    public void setTeacherCollegeID(String teacherCollegeID) {
        this.teacherCollegeID = teacherCollegeID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    
}
