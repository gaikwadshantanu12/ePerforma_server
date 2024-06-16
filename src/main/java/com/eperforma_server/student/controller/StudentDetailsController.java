package com.eperforma_server.student.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eperforma_server.student.entity.StudentDetailsEntity;
import com.eperforma_server.student.model.StudentDetailsModel;
import com.eperforma_server.student.service.StudentDetailsService;

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

	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam("studentName") String studentName,@RequestParam("studentPersonalEmail") String studentPersonalEmail, @RequestParam("studentCollegeEmail") String studentCollegeEmail, @RequestParam("studentMobile") String studentMobile, @RequestParam("studentRollNo") String studentRollNo, @RequestParam("studentID") String studentID,@RequestParam("studentCurrentYear") String studentCurrentYear, @RequestParam("studentCurrentSection") String studentCurrentSection , @RequestParam("profilePhoto") MultipartFile profilePhoto) {
		StudentDetailsEntity entity = new StudentDetailsEntity();
		entity.setStudentName(studentName);
		entity.setStudentPersonalEmail(studentPersonalEmail);
		entity.setStudentCollegeEmail(studentCollegeEmail);
		entity.setStudentMobile(studentMobile);
		entity.setStudentRollNo(Integer.parseInt(studentRollNo));
		entity.setStudentCollegeID(studentID);
		entity.setStudentCurrentYear(studentCurrentYear);
		entity.setStudentCurrentSection(studentCurrentSection);
		return studentDetailsService.updateProfile(entity, profilePhoto);
	}
	
	@DeleteMapping(value = "/deleteStudentByCollegeID/college_id={collegeID}")
	public String deleteStudentByCollegeID(@PathVariable("collegeID") String collegeID) {
		return studentDetailsService.deleteStudentByCollegeID(collegeID);
	}

	@PostMapping("/getParticularStudent") 
	public ResponseEntity<StudentDetailsEntity> getStudentDetailsByCollegeID(@RequestBody Map<String, String> requestBody) {
		return studentDetailsService.getStudentDetailsByCollegeID(requestBody.get("collegeID"));
	}
	
	@PostMapping("/loginStudent")
	public ResponseEntity<StudentDetailsEntity> loginStudent(@RequestBody Map<String, String> requestBody) {
		return studentDetailsService.loginStudent(requestBody.get("studentEmail"), requestBody.get("studentPassword"));
	}
	
	@GetMapping("/departmentWiseStudents/department={studentDepartment}")
	public List<StudentDetailsModel> getStudentDetailsDepartmentWise(@PathVariable("studentDepartment") String studentDepartment) {
		return studentDetailsService.getStudentDetailsDepartmentWise(studentDepartment);
	}
}
