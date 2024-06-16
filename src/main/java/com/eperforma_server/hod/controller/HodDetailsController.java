package com.eperforma_server.hod.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eperforma_server.hod.entity.HodDetailsEntity;
import com.eperforma_server.hod.model.HodDetailsModel;
import com.eperforma_server.hod.service.HodDetailsService;
import com.eperforma_server.student.model.StudentDetailsModel;
import com.eperforma_server.teacher.model.TeacherDetailsModel;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hod")
public class HodDetailsController {

	@Autowired
	HodDetailsService hodDetailsService;
	
	@GetMapping("/getAllHodDetails")
	public List<HodDetailsModel> getHodDetailsModels() {
		return hodDetailsService.getAllHodDetails();
	}
	
	@GetMapping("/getParticularHod/{collegeID}")
	public ResponseEntity<HodDetailsEntity> getHodDetailsByCollegeID(@PathVariable("collegeID") String collegeID) {
		return hodDetailsService.getHodDetailsByCollegeID(collegeID);
	}
	
	@PostMapping("/loginHod")
	public ResponseEntity<HodDetailsEntity> loginHod(@RequestBody Map<String, String> requestBody) {
		return hodDetailsService.loginHod(requestBody.get("hodEmail"), requestBody.get("hodPassword"));
	}
	
	@PostMapping("/studentsByDepartmentYearAndSection")
	public List<StudentDetailsModel> getStudentsByDepartmentYearAndSection(@RequestBody Map<String, String> requestBody) {
		return hodDetailsService.getStudentsByDepartmentYearAndSection(requestBody.get("studentDepartment"), requestBody.get("studentCurrentYear"), requestBody.get("studentCurrentSection"));
	}
	
	@PostMapping("/teachersByDepartment")
	public List<TeacherDetailsModel> getTeachersByDepartment(@RequestBody Map<String, String> requestBody) {
		return hodDetailsService.getTeachersByDepartment(requestBody.get("teacherDepartment"));
	}
}
