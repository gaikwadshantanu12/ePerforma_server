package com.eperforma_server.controller;

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

import com.eperforma_server.entity.StudentDetailsEntity;
import com.eperforma_server.model.StudentDetailsModel;
import com.eperforma_server.service.StudentDetailsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentDetailsController {
	
	@Autowired
	StudentDetailsService studentDetailsService;
	
	@GetMapping("/getAllStudentDetails")
	public List<StudentDetailsModel> getStudentDetailsModels() {
		return studentDetailsService.getAllStudentDetails();
	}
	
	@PostMapping("/addNewStudent")
	public String addNewStudent(@RequestBody StudentDetailsEntity studentDetailsEntity) {
		return studentDetailsService.addNewStudent(studentDetailsEntity);
	}
	
	@DeleteMapping(value = "/deleteStudentByCollegeID/{collegeID}")
	public String deleteStudentByCollegeID(@PathVariable("collegeID") String collegeID) {
		return studentDetailsService.deleteStudentByCollegeID(collegeID);
	}

	@GetMapping("/getParticularStudent/{collegeID}") 
	public ResponseEntity<StudentDetailsEntity> getStudentDetailsByCollegeID(@PathVariable("collegeID") String collegeID) {
		return studentDetailsService.getStudentDetailsByCollegeID(collegeID);
	}
	
	@GetMapping("/loginStudent/email={studentEmail}&password={studentPassword}")
	public ResponseEntity<StudentDetailsEntity> loginStudent(@PathVariable("studentEmail") String studentEmail, @PathVariable("studentPassword") String studentPassword) {
		return studentDetailsService.loginStudent(studentEmail, studentPassword);
	}

}
