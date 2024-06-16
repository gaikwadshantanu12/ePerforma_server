package com.eperforma_server.hod.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eperforma_server.hod.entity.HodDetailsEntity;

@Repository
public interface HodDetailsRepository extends JpaRepository<HodDetailsEntity, Integer>{
	
	public HodDetailsEntity findByHodCollegeID(String hodCollegeID);
	
	public HodDetailsEntity findByHodEmailAndHodPassword(String hodEmail, String hodPassword);
	
	@Query(value = "SELECT teacher_name, teacher_email, teacher_college_id " +
            "FROM teacher_info " +
            "WHERE teacher_department = :department", nativeQuery = true)
	List<Object[]> findTeachersByDepartment(@Param("department") String department);
	
	@Query(value = "SELECT student_name, student_personal_email, student_college_email, student_college_id, student_mobile, student_current_year, student_department, student_current_section " +
            "FROM student_info " +
            "WHERE student_department = :department AND student_current_year = :currentYear",nativeQuery = true)
	List<Object[]> findStudentsByDepartmentAndYear(@Param("department") String department,@Param("currentYear") String currentYear);

	@Query(value = "SELECT student_name, student_personal_email, student_college_email, student_college_id, student_mobile, student_current_year, student_department, CAST(student_roll_no AS VARCHAR) AS student_roll_no, student_current_section FROM student_info WHERE student_department = :department AND student_current_year = :currentYear AND student_current_section = :currentSection ORDER BY student_roll_no ASC", nativeQuery = true)
	List<Object[]> getStudentInSortedOrded(@Param("department") String department, @Param("currentYear") String currentYear, @Param("currentSection") String currentSection);
}
