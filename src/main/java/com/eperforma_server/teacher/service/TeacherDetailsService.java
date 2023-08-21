package com.eperforma_server.teacher.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eperforma_server.teacher.entity.TeacherDetailsEntity;
import com.eperforma_server.teacher.model.TeacherDetailsModel;
import com.eperforma_server.teacher.repository.TeacherDetailsRepository;

@Service
public class TeacherDetailsService {
    @Autowired
	TeacherDetailsRepository teacherDetailsRepository;

	/* Get all teacher details */
	public List<TeacherDetailsModel> getAllTeacherDetails() {
		try {
			List<TeacherDetailsEntity> teacherDetailsEntities = teacherDetailsRepository.findAll();
			List<TeacherDetailsModel> custoTeacherDetailsModels = new ArrayList<>();
			teacherDetailsEntities.stream().forEach(s -> {
				TeacherDetailsModel teacher = new TeacherDetailsModel();
				BeanUtils.copyProperties(s, teacher);
				custoTeacherDetailsModels.add(teacher);
			});
			
			return custoTeacherDetailsModels;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Get details of particular teacher using his/her college uid */
	public ResponseEntity<TeacherDetailsEntity> getTeacherDetailsByCollegeID(String collegeID) {
		TeacherDetailsEntity teacherEntity = teacherDetailsRepository.findByTeacherCollegeID(collegeID);

		if(teacherEntity != null) {
			return ResponseEntity.ok(teacherEntity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Add a new teacher in database and his/her details */
	public String addNewTeacher(TeacherDetailsEntity teacherDetailsEntity) {
		try {
			if(!(teacherDetailsRepository.existsByTeacherCollegeID(teacherDetailsEntity.getTeacherCollegeID()) || teacherDetailsRepository.existsByTeacherEmail(teacherDetailsEntity.getTeacherEmail()))) {
				teacherDetailsRepository.save(teacherDetailsEntity);
				return "New teacher registered successfully !";
			}
			else {
				return "Teacher already existing! Check for College ID or email.";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* Login existing teacher */
	public ResponseEntity<TeacherDetailsEntity> loginTeacher(String teacherEmail, String teacherPassword) {
		TeacherDetailsEntity entity = teacherDetailsRepository.findByTeacherEmailAndTeacherPassword(teacherEmail, teacherPassword);
		
		if(entity != null) {
			return ResponseEntity.ok(entity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Delete a teacher from database with given college uid */
	public String deleteTeacherByCollegeID(String collegeID) {
		TeacherDetailsEntity teacher = teacherDetailsRepository.findByTeacherCollegeID(collegeID);
		try {
			if (teacher != null) {
				teacherDetailsRepository.delete(teacher);
				return "Teacher Deleted Successfully !";
			} else {
				return "Teacher Not Found In Database With ID " + collegeID;
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
