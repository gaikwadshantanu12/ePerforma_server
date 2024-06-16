package com.eperforma_server.batch_allotment.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eperforma_server.batch_allotment.entity.BatchAllotmentEntity;
import com.eperforma_server.batch_allotment.model.BatchAllotmentModel;
import com.eperforma_server.batch_allotment.repository.BatchAllotmentRepository;

@Service
public class BatchAllotmentService {
    @Autowired
    BatchAllotmentRepository repository;

    /* create new batch */
    public String createBatch(BatchAllotmentEntity entity) {
        if(repository.doBatchExists(entity.getYear(), entity.getSection(), entity.getDepartment(), entity.getTeacherID())) {
            return "Batch already exists for ";
        } else {
            repository.save(entity);
            return "Batch created successfully !";
        }
    }

    /* view all batches */
	public List<BatchAllotmentModel> getBatchesByDepartment(String department) {
        List<Object[]> rawData = repository.getAllBatches(department);

        return rawData.stream()
                      .map(this::mapRowToCTDetails)
                      .collect(Collectors.toList());
    }

    private BatchAllotmentModel mapRowToCTDetails(Object[] row) {
        BatchAllotmentModel model = new BatchAllotmentModel();
        model.setDepartment((String) row[0]);
        model.setYear((String) row[1]);
        model.setSection((String) row[2]);
        model.setStartingRollNo(Integer.valueOf((String) row[3]));
        model.setEndingRollNo(Integer.valueOf((String) row[4]));
        model.setBatchSize(Integer.valueOf((String) row[5]));
        model.setTeacherID((String) row[6]);
        model.setTeacherName((String) row[7]);

        return model;
    }

    /* delete class */
    public String deleteBatch(BatchAllotmentEntity entity) {
        if(repository.doBatchExists(entity.getYear(), entity.getSection(), entity.getDepartment(), entity.getTeacherID())) {
            int rowsDeleted = repository.deleteBatch(entity.getYear(), entity.getSection(), entity.getDepartment(), entity.getTeacherID());
            if(rowsDeleted > 0) {
                return "Batch Successfully Deleted";
            } else {
                return "Batch Not Deleted";
            }
            
        } else {
            return "Batch not found";
        }
    }

    /* check if a loggedin teacher is a batch mentor or not */
    public boolean checkTeacherAsBatchMentor(String teacherID) {
        if(repository.doTeacherExistsAsBatchMentor(teacherID)) {
            return true;
        } else {
            return false;
        }
    }

    /* fetch batch details by teacher id */
    public BatchAllotmentEntity fetchBatchByClassTeacher(String teacherID) {
        BatchAllotmentEntity entity = repository.findByTeacherID(teacherID);

        return entity;
    }
}
