package com.eperforma_server.classes.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eperforma_server.classes.entity.ClassEntity;
import com.eperforma_server.classes.model.ClassModel;
import com.eperforma_server.classes.service.ClassService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping("/createClass")
	public String createClass(@RequestBody ClassEntity entity) {
		return classService.createNewClass(entity);
	}

    @PostMapping("/viewClass")
    public List<ClassModel> viewClass(@RequestBody Map<String, String> requestBody) {
        return classService.getClassesByDepartment(requestBody.get("department"));
    }

    @PostMapping("/deleteClass")
    public String deleteClass(@RequestBody ClassEntity entity) {
        return classService.deleteClass(entity);
    }

    @PostMapping("/checkClassTeacher")
    public boolean checkClassTeacher(@RequestBody Map<String, String> requestBody) {
        return classService.checkTeacherAsClassTeacher(requestBody.get("teacherCollegeID"));
    }

    @PostMapping("/fetchClassByTeacherID")
    public ClassEntity fetchClassByTeacherID(@RequestBody Map<String, String> requestBody) {
        return classService.fetchClassByClassTeacher(requestBody.get("teacherCollegeID"));
    }

}
