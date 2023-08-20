package com.eperforma_server.model;

public class StudentDetailsModel {
	public int ID;
	public String studentCollegeID;
	public String studentName;
	public String studentEmail;
	public String studentPassword;
	public String studentDepartment;
	
	public StudentDetailsModel() {
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getStudentCollegeID() {
		return studentCollegeID;
	}

	public void setStudentCollegeID(String studentCollegeID) {
		this.studentCollegeID = studentCollegeID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentDepartment() {
		return studentDepartment;
	}

	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}
	
}