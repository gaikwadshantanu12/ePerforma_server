package com.eperforma_server.batch_allotment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.eperforma_server.batch_allotment.entity.BatchAllotmentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BatchAllotmentRepository extends JpaRepository<BatchAllotmentEntity, Integer>{
    @Query(value = "SELECT EXISTS (SELECT department, year, section, teacher_id FROM batch_allotment WHERE department = :department AND year = :year AND section = :section AND teacher_id = :teacherID)", nativeQuery = true)
    boolean doBatchExists(@Param("year") String year, @Param("section") String section, @Param("department") String department, @Param("teacherID") String teacherID);

    @Query(value = "SELECT department, year, section, CAST(starting_roll_no AS VARCHAR) AS starting_roll_no, CAST(ending_roll_no AS VARCHAR) AS ending_roll_no, CAST(batch_size AS VARCHAR) AS batch_size, teacher_id, teacher_name  from batch_allotment WHERE department = :department", nativeQuery = true)
	List<Object[]> getAllBatches(@Param("department") String department);

    @Modifying
	@Transactional
	@Query(value = "DELETE FROM batch_allotment WHERE year = :year AND section = :section AND department = :department AND teacher_id = :teacherID", nativeQuery = true)
	int deleteBatch(@Param("year") String year, @Param("section") String section, @Param("department") String department, @Param("teacherID") String teacherID);
    
    @Query(value = "SELECT EXISTS (SELECT * FROM batch_allotment WHERE teacher_id = :teacherID)", nativeQuery = true)
	boolean doTeacherExistsAsBatchMentor(@Param("teacherID") String teacherID);

    BatchAllotmentEntity findByTeacherID(String teacherID);
}
