package com.eperforma_server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "student_info")
public class StudentDetailsEntity {
	
	@Column(name = "student_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ID;
	
	@Column(name = "student_college_id")
	public String studentCollegeID;
	
	@Column(name = "student_name")
	public String studentName;
	
	@Column(name = "student_email")
	public String studentEmail;
	
	@Column(name = "student_password")
	public String studentPassword;
	
	@Column(name = "student_department")
	public String studentDepartment;
	
	public StudentDetailsEntity() {
		
	}

	public StudentDetailsEntity(String studentCollegeID, String studentName, String studentEmail,
			String studentPassword, String studentDepartment) {
		super();
		this.studentCollegeID = studentCollegeID;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.studentDepartment = studentDepartment;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

