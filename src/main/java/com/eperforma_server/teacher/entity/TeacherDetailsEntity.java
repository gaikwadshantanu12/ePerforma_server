package com.eperforma_server.teacher.entity;


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
}
