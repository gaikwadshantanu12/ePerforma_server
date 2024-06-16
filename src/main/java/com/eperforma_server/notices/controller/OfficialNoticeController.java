package com.eperforma_server.notices.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eperforma_server.notices.entity.OfficialNoticesEntity;
import com.eperforma_server.notices.model.OfficialNoticesModel;
import com.eperforma_server.notices.service.OfficialNoticesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/notices")
public class OfficialNoticeController {
	
	@Autowired
	OfficialNoticesService officialNoticesService;
	
	@PostMapping("/addNewNotice")
	public String addNewNotice(@RequestParam("noticeTitle") String noticeTitle, @RequestParam("noticeMessage") String noticeMessage, @RequestParam("hodCollegeID") String hodCollegeID, @RequestParam("hodDepartment") String hodDepartment ,@RequestParam("noticeFile") MultipartFile noticeFile) {
		OfficialNoticesEntity entity = new OfficialNoticesEntity();
		entity.setNoticeTitle(noticeTitle);
		entity.setNoticeMessage(noticeMessage);
		entity.setHodCollegeID(hodCollegeID);
		entity.setHodDepartment(hodDepartment);
		return officialNoticesService.addNewNotice(entity, noticeFile);
	}
	
	@DeleteMapping(value = "/deleteNotice/noticeID={noticeID}")
	public String deleteNoticeByNoticeID(@PathVariable("noticeID") int noticeID) {
		return officialNoticesService.deleteNoticeByNoticeID(noticeID);
	}
	
	@PostMapping("/hodWiseNotices")
	public List<OfficialNoticesModel> getAllNoticesByParticularHod(@RequestBody Map<String, String> requestBody) {
		return officialNoticesService.getAllNoticesByParticularHod(requestBody.get("hodCollegeID"));
	}

	@GetMapping("/downloadNotice/hod_id={hodCollegeID}&filename={fileName}")
	public ResponseEntity<Resource> downloadNoticeFile(@PathVariable("hodCollegeID") String hodCollegeID, @PathVariable("fileName") String fileName) {
   		return officialNoticesService.downloadNoticeFile(hodCollegeID, fileName);
	}

	@PostMapping("/getNoticesByDepartment")
	public List<OfficialNoticesModel> getNoticesByDepartment(@RequestBody Map<String, String> requestBody) {
		return officialNoticesService.getNoticesByDepartment(requestBody.get("department"));
	}
}