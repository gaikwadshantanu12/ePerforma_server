package com.eperforma_server.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eperforma_server.teacher.entity.TeacherDetailsEntity;

@Repository
public interface TeacherDetailsRepository extends JpaRepository<TeacherDetailsEntity, Integer>{
    public boolean existsByTeacherCollegeID(String teacherCollegeID);
	
	public boolean existsByTeacherEmail(String teacherEmail);
	
	public boolean deleteByTeacherCollegeID(String teacherCollegeID);

	public TeacherDetailsEntity findByTeacherCollegeID(String teacherCollegeID);
	
	public TeacherDetailsEntity findByTeacherEmailAndTeacherPassword(String teacherEmail, String teacherPassword);
}
