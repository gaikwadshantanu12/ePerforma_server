package com.eperforma_server.subjects.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eperforma_server.subjects.entity.SubjectEntity;
import com.eperforma_server.subjects.model.SubjectModel;
import com.eperforma_server.subjects.service.SubjectService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/createSubject")
	public String addNewTeacher(@RequestBody SubjectEntity subjectEntity) {
		return subjectService.createNewSubject(subjectEntity);
	}

    @PostMapping("/viewSubjects")
    public List<SubjectModel> viewSubjects(@RequestBody Map<String, String> requestBody) {
        return subjectService.getSubjectsByDepartment(requestBody.get("department"));
    }

    @PostMapping("/viewSubjectsByYearAndDepartment")
    public List<SubjectModel> viewSubjectsByYearAndDepartment(@RequestBody Map<String, String> requestBody) {
        return subjectService.getSubjectsByYearAndDepartment(requestBody.get("department"), requestBody.get("year"));
    }
}
