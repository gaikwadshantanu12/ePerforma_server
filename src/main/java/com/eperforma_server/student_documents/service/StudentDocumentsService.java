package com.eperforma_server.student_documents.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import com.eperforma_server.student_documents.entity.StudentDocumentsEntity;
import com.eperforma_server.student_documents.model.StudentDocumentsModel;
import com.eperforma_server.student_documents.repository.StudentDocumentsRepository;

@Service
public class StudentDocumentsService {
    @Autowired
    StudentDocumentsRepository repository;

    @Value("${app.upload.dir.student.documents:${user.home}}")
	public String uploadDocumentsDir;

    /* upload documents */
	public String saveDocumentDetails(String studentCollegeID, String documentType, MultipartFile file) {
		try {

			if(repository.existsByStudentCollegeIDAndDocumentType(studentCollegeID, documentType)) {
				String newFileName = uploadDocument(studentCollegeID, documentType, file);

				repository.updateExistingDocumentDetails(studentCollegeID, newFileName, documentType);
			} else {
				String newFileName = uploadDocument(studentCollegeID, documentType, file);
			
				StudentDocumentsEntity entity = new StudentDocumentsEntity();
				entity.setStudentCollegeID(studentCollegeID);
				entity.setDocumentName(newFileName);
				entity.setDocumentType(documentType);
				repository.save(entity);
			}

			return documentType + " successfully uploaded !";
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload document with error - " + e.toString());
		}
	}

	public String uploadDocument(String studentCollegeID, String documentType, MultipartFile file) {
		try {
			String uniqueFileName = generateUniqueFileName(file.getOriginalFilename(), documentType);

			Path targetDirectory = Paths.get(uploadDocumentsDir + File.separator + StringUtils.cleanPath(studentCollegeID));
			Files.createDirectories(targetDirectory);
		
			Path copyLocation = targetDirectory.resolve(uniqueFileName);
		
			// Check if file already exists and overwrite if necessary
			if (Files.exists(copyLocation)) {
			  Files.delete(copyLocation); // Delete existing file before upload
			}
		
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			return uniqueFileName;
		} catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename());
		}
	}

	/* get all student documents */
	public List<StudentDocumentsModel> getDocumentUploadedByStudent(String studentCollegeID) {
		try {
			List<StudentDocumentsEntity> studentDocumentsEntities = repository.findByStudentCollegeID(studentCollegeID);
			List<StudentDocumentsModel> cDocumentsModels = new ArrayList<>();
			studentDocumentsEntities.stream().forEach(s -> {
				StudentDocumentsModel document = new StudentDocumentsModel();
				BeanUtils.copyProperties(s, document);
				cDocumentsModels.add(document);
			});
			
			return cDocumentsModels;
		} catch (Exception e) {
			throw e;
		}
	}

	/* download and display documents */
	public ResponseEntity<Resource> downloadDocument(String studentCollegeID, String fileName) {
    try {
        if (studentCollegeID == null || studentCollegeID.contains("..") || fileName == null || fileName.contains("..")) {
            throw new RuntimeException("Invalid college ID");
        }

		Path filePath = Paths.get(uploadDocumentsDir, StringUtils.cleanPath(studentCollegeID), StringUtils.cleanPath(fileName));
        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }
        String contentType = Files.probeContentType(filePath);
		Resource resource = new FileSystemResource(filePath);
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    	} catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    	}
	}

	public String generateUniqueFileName(String originalFileName, String documentType) {
		String extension = getExtension(originalFileName);
		
		return documentType + "." + extension;
	}

	public String getExtension(String fileName) {
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
	  		return fileName.substring(index + 1);
		} else {
	  		return "";
		}
  	}
  
  	public String getBaseName(String fileName) {
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
	  		return fileName.substring(0, index);
		} else {
	  		return fileName;
		}
  	}

}
