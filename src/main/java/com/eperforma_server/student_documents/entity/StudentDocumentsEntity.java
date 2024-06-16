package com.eperforma_server.student_documents.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_documents")
public class StudentDocumentsEntity {
    @Column(name = "document_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int documentID;

    @Column(name = "student_college_id")
    public String studentCollegeID;

    @Column(name = "documentName")
    public String documentName;

    @Column(name = "documentType")
    public String documentType;

}
