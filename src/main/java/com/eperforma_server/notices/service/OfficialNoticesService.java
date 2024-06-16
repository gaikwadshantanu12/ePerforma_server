package com.eperforma_server.notices.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.eperforma_server.notices.entity.OfficialNoticesEntity;
import com.eperforma_server.notices.model.OfficialNoticesModel;
import com.eperforma_server.notices.repository.OfficialNoticesRepository;

@Service
public class OfficialNoticesService {

	@Autowired
	OfficialNoticesRepository officialNoticesRepository;

	@Value("${app.upload.dir.notices:${user.home}}") 
    public String uploadDir;
	
	/* Add new notice */
	public String addNewNotice(OfficialNoticesEntity entity, MultipartFile file) {
		try {
			@SuppressWarnings("null")
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			entity.setNoticeFileName(fileName);
			
			officialNoticesRepository.save(entity);

			uploadNoticeFile(entity.hodCollegeID ,file);
			return "New Notice Posted Successfully !";
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}

	/* Upload file in folder */
	public void uploadNoticeFile(String hodCollegeID, MultipartFile file) {
        try {   
        	if (hodCollegeID == null || hodCollegeID.contains("..")) {
            	throw new RuntimeException("Invalid hodCollegeID");
        	}
        	Path targetDirectory = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(hodCollegeID));
        	Files.createDirectories(targetDirectory);        
        	
			@SuppressWarnings("null")
			Path copyLocation = targetDirectory.resolve(StringUtils.cleanPath(file.getOriginalFilename()));

			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename());
        }
    }
	
	/* Delete a notice from database with given notice id */
	public String deleteNoticeByNoticeID(int noticeID) {
		OfficialNoticesEntity notice = officialNoticesRepository.findByNoticeID(noticeID);
		
		try {
			if (notice != null) {
				@SuppressWarnings("null")
				String filePath = uploadDir + File.separator + StringUtils.cleanPath(notice.getHodCollegeID()) + File.separator + StringUtils.cleanPath(notice.getNoticeFileName());
            	
				File noticeFile = new File(filePath);
			
            	if (noticeFile.exists()) {
                	if (!noticeFile.delete()) {
                    	throw new IOException("Failed to delete notice file: " + filePath);
                	}
            	}
				
				officialNoticesRepository.delete(notice);
				return "Notice and associated file deleted successfully!";
			} else {
				return "Notice Not Found In Database With ID " + noticeID;
			}
		} catch (IOException e) {
			throw new RuntimeException("Error deleting notice file : " + e.getMessage()); 
		} catch (Exception e) { // Catch potential issues with database deletion
			throw new RuntimeException("Error deleting notice : " + e.getMessage());
		}
	}
	
	/* Get all notices posted by particular hod */
	@SuppressWarnings("null")
	public List<OfficialNoticesModel> getAllNoticesByParticularHod(String hodCollegeID) {
		try {
			List<OfficialNoticesEntity> officialNoticesEntities = officialNoticesRepository.findByHodCollegeID(hodCollegeID);
			List<OfficialNoticesModel> custoOfficialNoticesModels = new ArrayList<>();
			officialNoticesEntities.stream().forEach(s -> {
				OfficialNoticesModel notice = new OfficialNoticesModel();
				BeanUtils.copyProperties(s, notice);
				custoOfficialNoticesModels.add(notice);
			});
			
			return custoOfficialNoticesModels;
		} catch (Exception e) {
			throw e;
		}
	}

	/* Download and display notice in PDF Viewer */
	@SuppressWarnings("null")
	public ResponseEntity<Resource> downloadNoticeFile(String hodCollegeID, String noticeFileName) {
    try {
        if (hodCollegeID == null || hodCollegeID.contains("..") || noticeFileName == null || noticeFileName.contains("..")) {
            throw new RuntimeException("Invalid file path");
        }
		Path filePath = Paths.get(uploadDir, StringUtils.cleanPath(hodCollegeID), StringUtils.cleanPath(noticeFileName));
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

	/* get notices by department */
	public List<OfficialNoticesModel> getNoticesByDepartment(String department) {
        List<Object[]> rawData = officialNoticesRepository.getNoticesByDepartment(department);

        return rawData.stream()
                      .map(this::mapRowToNotices)
                      .collect(Collectors.toList());
    }

    private OfficialNoticesModel mapRowToNotices(Object[] row) {
        OfficialNoticesModel notice = new OfficialNoticesModel();
        notice.setNoticeTitle((String) row[1]);
		notice.setNoticeMessage((String) row[2]);
		notice.setNoticeFileName((String) row[3]);
		notice.setHodCollegeID((String) row[4]);
		notice.setHodDepartment((String) row[5]);
        return notice;
    }
}
