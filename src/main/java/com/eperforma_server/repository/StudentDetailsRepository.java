package com.eperforma_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eperforma_server.entity.StudentDetailsEntity;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetailsEntity, Integer> {
	
	public boolean existsByStudentCollegeID(String studentCollegeID);
	
	public boolean existsByStudentEmail(String studentEmail);
	
	public boolean deleteByStudentCollegeID(String studentCollegeID);

	public StudentDetailsEntity findByStudentCollegeID(String studentCollegeID);
	
	public StudentDetailsEntity findByStudentEmailAndStudentPassword(String studentEmail, String studentPassword);
}
