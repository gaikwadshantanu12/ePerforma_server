package com.eperforma_server.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name = "student_personal_email")
	public String studentPersonalEmail;
	
	@Column(name = "student_password")
	public String studentPassword;
	
	@Column(name = "student_department")
	public String studentDepartment;
	
	@Column(name = "student_mobile")
	public String studentMobile;
	
	@Column(name = "student_current_year")
	public String studentCurrentYear;

	@Column(name = "student_roll_no")
	public int studentRollNo;

	@Column(name = "student_college_email")
	public String studentCollegeEmail;

	@Column(name = "student_profile_photo")
	public String studentProfilePhoto;

	@Column(name = "student_current_section")
	public String studentCurrentSection;
}