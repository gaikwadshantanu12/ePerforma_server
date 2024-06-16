package com.eperforma_server.classes.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eperforma_server.classes.entity.ClassEntity;
import com.eperforma_server.classes.model.ClassModel;
import com.eperforma_server.classes.repository.ClassRepository;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;

    /* create a new class */
	public String createNewClass(ClassEntity entity) {
        if (classRepository.doClassExist(entity.getYear(), entity.getSection(), entity.getDepartment(), entity.getTeacherName())) {
        	return "Class Already Exists With The Teacher !"; 
    	} else {
        	classRepository.save(entity);
        	return "Class Created Successfully And Teacher Allotted !";
    	}
	}

    /* view all classes */
	public List<ClassModel> getClassesByDepartment(String department) {
        List<Object[]> rawData = classRepository.getAllClasses(department);

        return rawData.stream()
                      .map(this::mapRowToCTDetails)
                      .collect(Collectors.toList());
    }

    private ClassModel mapRowToCTDetails(Object[] row) {
        ClassModel classModel = new ClassModel();
        classModel.setYear((String) row[1]);
        classModel.setSection((String) row[2]);
        classModel.setTeacherName((String) row[3]);
        classModel.setDepartment((String) row[4]);
        classModel.setTeacherID((String) row[5]);
        return classModel;
    }

    /* delete class */
    public String deleteClass(ClassEntity entity) {
        if(classRepository.doClassExist(entity.getYear(), entity.getSection(), entity.getDepartment(), entity.getTeacherName())) {
            int rowsDeleted = classRepository.deleteClass(entity.getYear(), entity.getSection(), entity.getDepartment(), entity.getTeacherName());
            if(rowsDeleted > 0) {
                return "Class Successfully Deleted";
            } else {
                return "Class Not Deleted";
            }
            
        } else {
            return "Class not found";
        }
    }

    /* check if a loggedin teacher is a class teacher or not */
    public boolean checkTeacherAsClassTeacher(String teacherID) {
        if(classRepository.doTeacherExistsAsClassTeacher(teacherID)) {
            return true;
        } else {
            return false;
        }
    }

    /* fetch class details by teacher id */
    public ClassEntity fetchClassByClassTeacher(String teacherID) {
        ClassEntity entity = classRepository.findByTeacherID(teacherID);

        return entity;
    }
}
