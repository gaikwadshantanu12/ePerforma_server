package com.eperforma_server.student_documents.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eperforma_server.student_documents.model.StudentDocumentsModel;
import com.eperforma_server.student_documents.service.StudentDocumentsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student_documents")
public class StudentDocumentsController {
    @Autowired
    StudentDocumentsService service;

    @PostMapping("/uploadDocument")
	public String uploadDocuments(@RequestParam("studentCollegeID") String studentCollegeID, @RequestParam("documentType") String documentType, @RequestParam("document") MultipartFile document) {
		return service.saveDocumentDetails(studentCollegeID, documentType, document);
	}

    @PostMapping("/studentDocuments")
    public List<StudentDocumentsModel> getStudentDocuments(@RequestBody Map<String, String> requestBody) {
        return service.getDocumentUploadedByStudent(requestBody.get("studentCollegeID"));
    }

    @GetMapping("/downloadDocument/student_id={studentCollegeID}&filename={fileName}")
    public ResponseEntity<Resource> downloadDocumentFile(@PathVariable("studentCollegeID") String studentCollegeID, @PathVariable("fileName") String fileName) {
        return service.downloadDocument(studentCollegeID, fileName);
    }

}
