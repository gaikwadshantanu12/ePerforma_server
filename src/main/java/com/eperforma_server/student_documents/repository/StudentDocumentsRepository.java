package com.eperforma_server.student_documents.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.eperforma_server.student_documents.entity.StudentDocumentsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface StudentDocumentsRepository extends JpaRepository<StudentDocumentsEntity, Integer>{
    public boolean existsByStudentCollegeID(String studentCollegeID);

    public List<StudentDocumentsEntity> findByStudentCollegeID(String studentCollegeID);

    public boolean existsByStudentCollegeIDAndDocumentType(String studentCollegeID, String documentType);

    @Modifying
    @Transactional
    @Query(value = "UPDATE student_documents SET document_name = :documentName, document_type = :documentType WHERE student_college_id = :studentCollegeID AND document_type = :documentType", nativeQuery = true)
    public void updateExistingDocumentDetails(@Param("studentCollegeID") String studentCollegeID, @Param("documentName") String fileName, @Param("documentType") String documentType);
}
