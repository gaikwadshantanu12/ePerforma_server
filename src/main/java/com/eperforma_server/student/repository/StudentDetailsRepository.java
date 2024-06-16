package com.eperforma_server.student.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.eperforma_server.student.entity.StudentDetailsEntity;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetailsEntity, Integer> {
	
	public boolean existsByStudentCollegeID(String studentCollegeID);
	
	public boolean existsByStudentPersonalEmail(String studentPersonalEmail);
	
	public boolean deleteByStudentCollegeID(String studentCollegeID);

	public StudentDetailsEntity findByStudentCollegeID(String studentCollegeID);
	
	public List<StudentDetailsEntity> findByStudentDepartment(String studentDepartment);
	
	public List<StudentDetailsEntity> findByStudentDepartmentAndStudentCurrentYear(String studentDepartment, String studentCurrentYear);
	
	public StudentDetailsEntity findByStudentPersonalEmailAndStudentPassword(String studentPersonalEmail, String studentPassword);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO social_links (student_college_id) VALUES (:studentCollegeID)", nativeQuery = true)
	public void addNewStudentToSocialLinks(@Param("studentCollegeID")String studentCollegeID);

	@Modifying
	@Transactional
	@Query(value = "UPDATE student_info SET student_name = :studentName, student_personal_email = :studentPersonalEmail, student_college_email = :studentCollegeEmail, student_mobile = :studentMobile, student_roll_no = :studentRollNo, student_profile_photo = :profilePhoto, student_current_section = :studentCurrentSection WHERE student_college_id = :studentID", nativeQuery = true)
	public void updateProfile(@Param("studentName") String studentName,@Param("studentPersonalEmail") String studentPersonalEmail, @Param("studentCollegeEmail") String studentCollegeEmail, @Param("studentMobile") String studentMobile, @Param("studentRollNo") int studentRollNo, @Param("studentID") String studentID, @Param("profilePhoto") String profilePhoto, @Param("studentCurrentSection") String studentCurrentSection);
}
