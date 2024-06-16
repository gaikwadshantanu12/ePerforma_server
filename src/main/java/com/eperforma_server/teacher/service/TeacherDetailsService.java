package com.eperforma_server.teacher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eperforma_server.student.model.StudentDetailsModel;
import com.eperforma_server.subjects.model.SubjectModel;
import com.eperforma_server.teacher.entity.TeacherDetailsEntity;
import com.eperforma_server.teacher.model.TeacherDetailsModel;
import com.eperforma_server.teacher.repository.TeacherDetailsRepository;

@Service
public class TeacherDetailsService {
    @Autowired
	TeacherDetailsRepository teacherDetailsRepository;

	/* Get all teacher details */
	@SuppressWarnings("null")
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
	
	/* Get all teacher with same department */
	@SuppressWarnings("null")
	public List<TeacherDetailsModel> getTeacherDetailsByTeacherDepartment(String teacherDepartment) {
		try {
			List<TeacherDetailsEntity> teacherDetailsEntities = teacherDetailsRepository.findByTeacherDepartment(teacherDepartment);
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

	/* get all my assigned subjects */
	public List<SubjectModel> getMyAllSubjects(String teacherID) {
    	List<Object[]> rawData = teacherDetailsRepository.getMyAllSubjects(teacherID);
    	
    	return rawData.stream().map(this::mapRowToSubject).collect(Collectors.toList());
    }
    
    private SubjectModel mapRowToSubject(Object[] row) {
    	SubjectModel subject = new SubjectModel();
    	subject.setSubjectName((String) row[1]);
		subject.setTeacherName((String) row[2]);
		subject.setTeacherID((String) row[3]);
		subject.setDepartment((String) row[4]);
		subject.setSemester((String) row[5]);
		subject.setYear((String) row[6]);
		subject.setSubjectCode((String) row[7]);
		subject.setSection((String) row[8]);
    	return subject;
    }

	// /* Get all students with same department - year wise */
	// public List<StudentDetailsModel> getStudentsByDepartmentAndYear(String department, String currentYear) {
    //     List<Object[]> rawData = teacherDetailsRepository.findStudentsByDepartmentAndYear(department, currentYear);

    //     return rawData.stream()
    //                   .map(this::mapRowToStudent)
    //                   .collect(Collectors.toList());
    // }

	/* Get all students by department, year and section */
	public List<StudentDetailsModel> getStudentsByDepartmentYearAndSection(String department, String currentYear, String currentSection) {
		List<Object[]> rawData = teacherDetailsRepository.findStudentsByDepartmentYearAndSection(department, currentYear, currentSection);

        return rawData.stream()
                      .map(this::mapRowToStudent)
                      .collect(Collectors.toList());
	}

	private StudentDetailsModel mapRowToStudent(Object[] row) {
        StudentDetailsModel student = new StudentDetailsModel();
        student.setStudentName((String) row[0]);
        student.setStudentPersonalEmail((String) row[1]);
		student.setStudentCollegeEmail((String) row[2]);
        student.setStudentCollegeID((String) row[3]);
		student.setStudentMobile((String) row[4]);
		student.setStudentCurrentYear((String) row[5]);
		student.setStudentDepartment((String) row[6]);
		student.setStudentRollNo((int) row[7]);
		student.setStudentCurrentSection((String) row[8]);
        return student;
    }

	/* Get all students by department, year and section */
	public List<StudentDetailsModel> getStudentsBybatch(String department, String currentYear, String currentSection, int startingRollNo, int endingRollNo) {
		List<Object[]> rawData = teacherDetailsRepository.findStudentsByBatch(department, currentYear, currentSection, startingRollNo, endingRollNo);

        return rawData.stream()
                      .map(this::mapRowToStudent)
                      .collect(Collectors.toList());
	}
}
