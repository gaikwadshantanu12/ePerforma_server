package com.eperforma_server.teacher.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eperforma_server.teacher.entity.TeacherDetailsEntity;

@Repository
public interface TeacherDetailsRepository extends JpaRepository<TeacherDetailsEntity, Integer>{
    public boolean existsByTeacherCollegeID(String teacherCollegeID);
	
	public boolean existsByTeacherEmail(String teacherEmail);
	
	public boolean deleteByTeacherCollegeID(String teacherCollegeID);

	public TeacherDetailsEntity findByTeacherCollegeID(String teacherCollegeID);
	
	public List<TeacherDetailsEntity> findByTeacherDepartment(String teacherDepartment);
	
	public TeacherDetailsEntity findByTeacherEmailAndTeacherPassword(String teacherEmail, String teacherPassword);

	@Query(value = "SELECT * FROM subjects WHERE teacher_id = :teacherID", nativeQuery = true)
	List<Object[]> getMyAllSubjects(@Param("teacherID") String teacherID);

	// @Query(value = "SELECT student_name, student_personal_email, student_college_email, student_college_id, student_mobile, student_current_year, student_department, student_roll_no " +
    //         "FROM student_info " +
    //         "WHERE student_department = :department AND student_current_year = :currentYear ORDER BY student_roll_no ASC",nativeQuery = true)
	// List<Object[]> findStudentsByDepartmentAndYear(@Param("department") String department,@Param("currentYear") String currentYear);

	@Query(value = "SELECT student_name, student_personal_email, student_college_email, student_college_id, student_mobile, student_current_year, student_department, student_roll_no, student_current_section " +
            "FROM student_info " +
            "WHERE student_department = :department AND student_current_year = :currentYear AND student_current_section = :currentSection ORDER BY student_roll_no ASC",nativeQuery = true)
	List<Object[]> findStudentsByDepartmentYearAndSection(@Param("department") String department,@Param("currentYear") String currentYear, @Param("currentSection") String currentSection);

	@Query(value = "SELECT student_name, student_personal_email, student_college_email, student_college_id, student_mobile, student_current_year, student_department, student_roll_no, student_current_section " +
            "FROM student_info " +
            "WHERE student_department = :department AND student_current_year = :currentYear AND student_current_section = :currentSection AND student_roll_no BETWEEN :startingRollNo AND :endingRollNo ORDER BY student_roll_no ASC",nativeQuery = true)
	List<Object[]> findStudentsByBatch(@Param("department") String department,@Param("currentYear") String currentYear, @Param("currentSection") String currentSection, @Param("startingRollNo") int startingRollNo, @Param("endingRollNo") int endingRollNo);
}
