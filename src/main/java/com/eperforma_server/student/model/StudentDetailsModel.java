package com.eperforma_server.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsModel {
	public int ID;
	public String studentCollegeID;
	public String studentName;
	public String studentPersonalEmail;
	public String studentPassword;
	public String studentDepartment;
	public String studentMobile;
	public String studentCurrentYear;
	public int studentRollNo;
	public String studentCollegeEmail;
	public String studentProfilePhoto;	
	public String studentCurrentSection;
}