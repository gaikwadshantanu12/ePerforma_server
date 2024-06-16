package com.eperforma_server.hod.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.eperforma_server.hod.entity.HodDetailsEntity;
import com.eperforma_server.hod.model.HodDetailsModel;
import com.eperforma_server.hod.repository.HodDetailsRepository;
import com.eperforma_server.student.model.StudentDetailsModel;
import com.eperforma_server.teacher.model.TeacherDetailsModel;

@Service
public class HodDetailsService {
	
	@Autowired
	HodDetailsRepository hodDetailsRepository;
	
	/* Get All HODs Details */
	@SuppressWarnings("null")
	public List<HodDetailsModel> getAllHodDetails() {
		try {
			List<HodDetailsEntity> hodDetailsEntities = hodDetailsRepository.findAll();
			List<HodDetailsModel> customHodDetailsModels = new ArrayList<>();
			hodDetailsEntities.stream().forEach(h -> {
				HodDetailsModel hod = new HodDetailsModel();
				BeanUtils.copyProperties(h, hod);
				customHodDetailsModels.add(hod);
			});
			
			return customHodDetailsModels;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Get details of hod using his/her college ID */
	public ResponseEntity<HodDetailsEntity> getHodDetailsByCollegeID(String collegeID) {
		HodDetailsEntity hodEntity = hodDetailsRepository.findByHodCollegeID(collegeID);
		
		if(hodEntity != null) {
			return ResponseEntity.ok(hodEntity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Login existing hod */
	public ResponseEntity<HodDetailsEntity> loginHod(String hodEmail, String hodPassword) {
		HodDetailsEntity entity = hodDetailsRepository.findByHodEmailAndHodPassword(hodEmail, hodPassword);
		
		if(entity != null) {
			return ResponseEntity.ok(entity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Get all students with same department - year wise */
	public List<StudentDetailsModel> getStudentsByDepartmentYearAndSection(String department, String currentYear, String currentSection) {
        List<Object[]> rawData = hodDetailsRepository.getStudentInSortedOrded(department, currentYear, currentSection);

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
		student.setStudentRollNo(Integer.valueOf((String) row[7]));
		student.setStudentCurrentSection((String) row[8]);
        return student;
    }
    
    /* Get all teachers from the same department */
    public List<TeacherDetailsModel> getTeachersByDepartment(String department) {
    	List<Object[]> rawData = hodDetailsRepository.findTeachersByDepartment(department);
    	
    	return rawData.stream().map(this::mapRowToTeacher).collect(Collectors.toList());
    }
    
    private TeacherDetailsModel mapRowToTeacher(Object[] row) {
    	TeacherDetailsModel teacher = new TeacherDetailsModel();
    	teacher.setTeacherName((String) row[0]);
    	teacher.setTeacherEmail((String) row[1]);
    	teacher.setTeacherCollegeID((String) row[2]);
    	return teacher;
    }

}
