package com.eperforma_server.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eperforma_server.teacher.entity.TeacherDetailsEntity;
import com.eperforma_server.teacher.model.TeacherDetailsModel;
import com.eperforma_server.teacher.service.TeacherDetailsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/teacher")
public class TeacherDetailsController {

    @Autowired
	TeacherDetailsService teacherDetailsService;

    @GetMapping("/getAllTeacherDetails")
	public List<TeacherDetailsModel> getTeacherDetailsModels() {
		return teacherDetailsService.getAllTeacherDetails();
	}
	
	@PostMapping("/addNewTeacher")
	public String addNewTeacher(@RequestBody TeacherDetailsEntity teacherDetailsEntity) {
		return teacherDetailsService.addNewTeacher(teacherDetailsEntity);
	}
	
	@DeleteMapping(value = "/deleteTeacherByCollegeID/{collegeID}")
	public String deleteTeacherByCollegeID(@PathVariable("collegeID") String collegeID) {
		return teacherDetailsService.deleteTeacherByCollegeID(collegeID);
	}

	@GetMapping("/getParticularTeacher/{collegeID}") 
	public ResponseEntity<TeacherDetailsEntity> getTeacherDetailsByCollegeID(@PathVariable("collegeID") String collegeID) {
		return teacherDetailsService.getTeacherDetailsByCollegeID(collegeID);
	}
	
	@GetMapping("/loginTeacher/email={teacherEmail}&password={teacherPassword}")
	public ResponseEntity<TeacherDetailsEntity> loginTeacher(@PathVariable("teacherEmail") String teacherEmail, @PathVariable("teacherPassword") String teacherPassword) {
		return teacherDetailsService.loginTeacher(teacherEmail, teacherPassword);
	}
}
