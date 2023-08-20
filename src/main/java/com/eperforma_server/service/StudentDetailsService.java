package com.eperforma_server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eperforma_server.entity.StudentDetailsEntity;
import com.eperforma_server.model.StudentDetailsModel;
import com.eperforma_server.repository.StudentDetailsRepository;

@Service
public class StudentDetailsService {
	
	@Autowired
	StudentDetailsRepository studentDetailsRepository;

	/* Get all student details */
	public List<StudentDetailsModel> getAllStudentDetails() {
		try {
			List<StudentDetailsEntity> studentDetailsEntities = studentDetailsRepository.findAll();
			List<StudentDetailsModel> custoStudentDetailsModels = new ArrayList<>();
			studentDetailsEntities.stream().forEach(s -> {
				StudentDetailsModel student = new StudentDetailsModel();
				BeanUtils.copyProperties(s, student);
				custoStudentDetailsModels.add(student);
			});
			
			return custoStudentDetailsModels;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Get details of particular student using his/her college uid */
	public ResponseEntity<StudentDetailsEntity> getStudentDetailsByCollegeID(String collegeID) {
		StudentDetailsEntity studentEntity = studentDetailsRepository.findByStudentCollegeID(collegeID);

		if(studentEntity != null) {
			return ResponseEntity.ok(studentEntity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Add a new student in database and his/her details */
	public String addNewStudent(StudentDetailsEntity studentDetailsEntity) {
		try {
			if(!(studentDetailsRepository.existsByStudentCollegeID(studentDetailsEntity.getStudentCollegeID()) || studentDetailsRepository.existsByStudentEmail(studentDetailsEntity.getStudentEmail()))) {
				studentDetailsRepository.save(studentDetailsEntity);
				return "New student registered successfully !";
			}
			else {
				return "Student already existing! Check for College ID or email.";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* Login existing student */
	public ResponseEntity<StudentDetailsEntity> loginStudent(String studentEmail, String studentPassword) {
		StudentDetailsEntity entity = studentDetailsRepository.findByStudentEmailAndStudentPassword(studentEmail, studentPassword);
		
		if(entity != null) {
			return ResponseEntity.ok(entity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Delete a student from database with given college uid */
	public String deleteStudentByCollegeID(String collegeID) {
		StudentDetailsEntity student = studentDetailsRepository.findByStudentCollegeID(collegeID);
		try {
			if (student != null) {
				studentDetailsRepository.delete(student);
				return "Student Deleted Successfully !";
			} else {
				return "Student Not Found In Database With ID " + collegeID;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
}