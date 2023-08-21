package com.eperforma_server.teacher.model;

public class TeacherDetailsModel {
    public int ID;
	public String teacherCollegeID;
	public String teacherName;
	public String teacherEmail;
	public String teacherPassword;
	public String teacherDepartment;
    
    public TeacherDetailsModel() {
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
