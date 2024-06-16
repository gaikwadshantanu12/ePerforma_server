package com.eperforma_server.student.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.eperforma_server.student.entity.StudentDetailsEntity;
import com.eperforma_server.student.model.StudentDetailsModel;
import com.eperforma_server.student.repository.StudentDetailsRepository;

@Service
public class StudentDetailsService {
	
	@Autowired
	StudentDetailsRepository studentDetailsRepository;

	@Value("${app.upload.dir.student.profile_photo:${user.home}}") 
    public String uploadPhotoDir;

	/* Get all student details */
	public List<StudentDetailsModel> getAllStudentDetails() {
		try {
			List<StudentDetailsEntity> studentDetailsEntities = studentDetailsRepository.findAll();
			List<StudentDetailsModel> custoStudentDetailsModels = new ArrayList<>();
			studentDetailsEntities.stream().forEach(s -> {
				StudentDetailsModel student = new StudentDetailsModel();
				BeanUtils.copyProperties(s, student);
				custoStudentDetailsModels.add(student);
			});
			
			return custoStudentDetailsModels;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Get details of particular student using his/her college uid */
	public ResponseEntity<StudentDetailsEntity> getStudentDetailsByCollegeID(String collegeID) {
		StudentDetailsEntity studentEntity = studentDetailsRepository.findByStudentCollegeID(collegeID);
		
		if(studentEntity != null) {
			return ResponseEntity.ok(studentEntity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Get all students with same department */
	public List<StudentDetailsModel> getStudentDetailsDepartmentWise(String studentDepartment) {
		try {
			List<StudentDetailsEntity> studentDetailsEntities = studentDetailsRepository.findByStudentDepartment(studentDepartment);
			List<StudentDetailsModel> custoStudentDetailsModels = new ArrayList<>();
			studentDetailsEntities.stream().forEach(s -> {
				StudentDetailsModel student = new StudentDetailsModel();
				BeanUtils.copyProperties(s, student);
				custoStudentDetailsModels.add(student);
			});
			
			return custoStudentDetailsModels;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Add a new student in database and his/her details */
	public String addNewStudent(StudentDetailsEntity studentDetailsEntity) {
		try {
			if(!(studentDetailsRepository.existsByStudentCollegeID(studentDetailsEntity.getStudentCollegeID()) || studentDetailsRepository.existsByStudentPersonalEmail(studentDetailsEntity.getStudentPersonalEmail()))) {
				studentDetailsRepository.addNewStudentToSocialLinks(studentDetailsEntity.getStudentCollegeID());
				studentDetailsRepository.save(studentDetailsEntity);
				return "New student registered successfully !";
			}
			else {
				return "Student already existing! Check for College ID or email.";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* Login existing student */
	public ResponseEntity<StudentDetailsEntity> loginStudent(String studentEmail, String studentPassword) {
		StudentDetailsEntity entity = studentDetailsRepository.findByStudentPersonalEmailAndStudentPassword(studentEmail, studentPassword);
		
		if(entity != null) {
			return ResponseEntity.ok(entity);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* Delete a student from database with given college uid */
	public String deleteStudentByCollegeID(String collegeID) {
		StudentDetailsEntity student = studentDetailsRepository.findByStudentCollegeID(collegeID);
		try {
			if (student != null) {
				studentDetailsRepository.delete(student);
				return "Student Deleted Successfully !";
			} else {
				return "Student Not Found In Database With ID " + collegeID;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* update student profile */
	public String updateProfile(StudentDetailsEntity entity, MultipartFile profilePhoto) {
		try {
			@SuppressWarnings("null")
			String fileName = StringUtils.cleanPath(profilePhoto.getOriginalFilename());
			entity.setStudentProfilePhoto(fileName);
			
			studentDetailsRepository.updateProfile(entity.getStudentName(), entity.getStudentPersonalEmail(), entity.getStudentCollegeEmail(), entity.getStudentMobile(), entity.getStudentRollNo(), entity.getStudentCollegeID(), entity.getStudentProfilePhoto(), entity.getStudentCurrentSection());

			uploadStudentProfilePhoto(entity.getStudentCollegeID() ,profilePhoto);
			return "Student Updated Successfully !";
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}

	/* Upload profile photo in folder */
	public void uploadStudentProfilePhoto(String studentID, MultipartFile file) {
        try {   
        	if (studentID == null || studentID.contains("..")) {
            	throw new RuntimeException("Invalid studentID");
        	}
			
			deletePreviousPhotos(studentID);
        	Path targetDirectory = Paths.get(uploadPhotoDir + File.separator + StringUtils.cleanPath(studentID));
        	Files.createDirectories(targetDirectory);        
        	
			@SuppressWarnings("null")
			Path copyLocation = targetDirectory.resolve(StringUtils.cleanPath(file.getOriginalFilename()));

			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename());
        }
    }

	public void deletePreviousPhotos(String studentID) {
		try {
			if (studentID != null) {
				String directoryPath = uploadPhotoDir + File.separator + StringUtils.cleanPath(studentID) + File.separator;
            	System.out.println(directoryPath);
				File directory = new File(directoryPath);
			
            	if(!directory.exists()) {
					System.out.println("Directory does not exist: " + directoryPath);
            		return;
				}

				File[] files = directory.listFiles();
        		if (files != null) { 
            		for (File file : files) {
                		if (file.isFile() && !file.delete()) {
                    		throw new IOException("Failed to delete file: " + file.getAbsolutePath());
                		}
            		}
        		} else {
            		System.out.println("Directory is empty: " + directoryPath);
        		}
				
			} else {
				throw new RuntimeException("Student Not Found In Database With ID " + studentID);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error deleting profile photo file : " + e.getMessage()); 
		} catch (Exception e) { // Catch potential issues with database deletion
			throw new RuntimeException("Error deleting profile photo file : " + e.getMessage());
		}
	}

	
}