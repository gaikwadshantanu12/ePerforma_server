package com.eperforma_server.student_documents.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDocumentsModel {
    public int documentID;
    public String studentCollegeID;
    public String documentName;
    public String documentType;
    
}
