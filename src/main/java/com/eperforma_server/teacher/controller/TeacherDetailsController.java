package com.eperforma_server.teacher.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.eperforma_server.student.model.StudentDetailsModel;
import com.eperforma_server.subjects.model.SubjectModel;
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
	
	@PostMapping("/loginTeacher")
	public ResponseEntity<TeacherDetailsEntity> loginTeacher(@RequestBody Map<String, String> requestBody) {
		return teacherDetailsService.loginTeacher(requestBody.get("teacherEmail"), requestBody.get("teacherPassword"));
	}
	
	@GetMapping("/departmentWiseTeacher/{teacherDepartment}")
	public List<TeacherDetailsModel> getTeacherDepartmentWise(@PathVariable("teacherDepartment") String teacherDepartment) {
		return teacherDetailsService.getTeacherDetailsByTeacherDepartment(teacherDepartment);
	}

	@PostMapping("/getMyAllSubjects")
	public List<SubjectModel> getMyAllSubjects(@RequestBody Map<String, String> requestBody) {
		return teacherDetailsService.getMyAllSubjects(requestBody.get("teacherID"));
	}

	// @PostMapping("/studentsByDepartmentAndYear")
	// public List<StudentDetailsModel> getStudentsByDepartmentAndYear(@RequestBody Map<String, String> requestBody) {
	// 	return teacherDetailsService.getStudentsByDepartmentAndYear(requestBody.get("studentDepartment"), requestBody.get("studentCurrentYear"));
	// }

	@PostMapping("/fetchStudentsByDeptYearAndSection")
	public List<StudentDetailsModel> getStudentsByDepartmentYearAndSection(@RequestBody Map<String, String> requestBody) {
		return teacherDetailsService.getStudentsByDepartmentYearAndSection(requestBody.get("studentDepartment"), requestBody.get("studentCurrentYear"), requestBody.get("studentCurrentSection"));
	}

	@PostMapping("/fetchBatchStudents")
	public List<StudentDetailsModel> getStudentByBatch(@RequestBody Map<String, String> requestBody) {
		return teacherDetailsService.getStudentsBybatch(requestBody.get("studentDepartment"), requestBody.get("studentCurrentYear"), requestBody.get("studentCurrentSection"), Integer.valueOf(requestBody.get("studentStartingRollNo")), Integer.valueOf(requestBody.get("studentEndingRollNo")));
	}
}
