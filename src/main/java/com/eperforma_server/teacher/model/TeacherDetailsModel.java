package com.eperforma_server.teacher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDetailsModel {
    public int ID;
	public String teacherCollegeID;
	public String teacherName;
	public String teacherEmail;
	public String teacherPassword;
	public String teacherDepartment;
    
}
