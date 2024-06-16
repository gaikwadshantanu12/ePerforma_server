package com.eperforma_server.subjects.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eperforma_server.subjects.entity.SubjectEntity;
import com.eperforma_server.subjects.model.SubjectModel;
import com.eperforma_server.subjects.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    /* create new subject and allot subject teacher */
    public String createNewSubject(SubjectEntity entity) {
        if(subjectRepository.doSubjectExist(entity.getSubjectName(), entity.getSubjectCode(), entity.getDepartment(), entity.getTeacherName(), entity.getSemester(), entity.getYear(), entity.getSection())) {
            return "Subject and Subject Teacher Already Exists";
        } else {
            subjectRepository.save(entity);
            return "Subject Created & Teacher Assigned !";
        }
    }

    /* view all subjects */
	public List<SubjectModel> getSubjectsByDepartment(String department) {
        List<Object[]> rawData = subjectRepository.getAllSubjects(department);

        return rawData.stream()
                      .map(this::mapRowToSTDetails)
                      .collect(Collectors.toList());
    }

    private SubjectModel mapRowToSTDetails(Object[] row) {
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setSubjectName((String) row[1]);
        subjectModel.setTeacherName((String) row[2]);
        subjectModel.setTeacherID((String) row[3]);
        subjectModel.setDepartment((String) row[4]);
        subjectModel.setSemester((String) row[5]);
        subjectModel.setYear((String) row[6]);
        subjectModel.setSubjectCode((String) row[7]);
        subjectModel.setSection((String) row[8]);
        return subjectModel;
    }

    /* view subjects by year and department */
    public List<SubjectModel> getSubjectsByYearAndDepartment(String department, String year) {
        List<Object[]> rawData = subjectRepository.getAllSubjectsByYearAndDepartment(department, year);

        return rawData.stream().map(this::mapRowToSTDetails).collect(Collectors.toList());
    }
}
