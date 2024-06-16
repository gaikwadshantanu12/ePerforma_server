package com.eperforma_server.subjects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eperforma_server.subjects.entity.SubjectEntity;

@Repository
public interface SubjectRepository  extends JpaRepository<SubjectEntity, Integer> {
    @Query(value = "SELECT EXISTS (SELECT subject_name, subject_code teacher_name, department, semester, year FROM subjects WHERE subject_name = :subjectName AND subject_code = :subjectCode AND teacher_name = :teacherName AND department = :department AND semester = :semester AND year = :year AND section = :section)", nativeQuery = true)
	boolean doSubjectExist(@Param("subjectName") String subjectName, @Param("subjectCode") String subjectCode, @Param("department") String department, @Param("teacherName") String teacherName, @Param("semester") String semester, @Param("year") String year, @Param("section") String section);

    @Query(value = "SELECT * from subjects WHERE department = :department", nativeQuery = true)
	List<Object[]> getAllSubjects(@Param("department") String department);

    @Query(value = "SELECT * from subjects WHERE department = :department AND year = :year", nativeQuery = true)
    List<Object[]> getAllSubjectsByYearAndDepartment(@Param("department") String department, @Param("year") String year);
}
