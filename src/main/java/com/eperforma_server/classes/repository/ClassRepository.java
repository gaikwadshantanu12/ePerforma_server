package com.eperforma_server.classes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eperforma_server.classes.entity.ClassEntity;
import jakarta.transaction.Transactional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer>{
    
	@Query(value = "SELECT EXISTS (SELECT year, section, department, teacher_name FROM class WHERE year = :year AND section = :section AND department = :department AND teacher_name = :teacherName)", nativeQuery = true)
	boolean doClassExist(@Param("year") String year, @Param("section") String section, @Param("department") String department, @Param("teacherName") String teacherName);

	@Query(value = "SELECT * from class WHERE department = :department", nativeQuery = true)
	List<Object[]> getAllClasses(@Param("department") String department);

	@Query(value = "SELECT EXISTS (SELECT * FROM class WHERE teacher_id = :teacherID)", nativeQuery = true)
	boolean doTeacherExistsAsClassTeacher(@Param("teacherID") String teacherID);
	
	ClassEntity findByTeacherID(String teacherID);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM class WHERE year = :year AND section = :section AND department = :department AND teacher_name = :teacherName", nativeQuery = true)
	int deleteClass(@Param("year") String year, @Param("section") String section, @Param("department") String department, @Param("teacherName") String teacherName);
}
