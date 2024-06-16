package com.eperforma_server.batch_allotment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eperforma_server.batch_allotment.entity.BatchAllotmentEntity;
import com.eperforma_server.batch_allotment.model.BatchAllotmentModel;
import com.eperforma_server.batch_allotment.service.BatchAllotmentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/batch_allotment")
public class BatchAllotmentController {
    @Autowired
    BatchAllotmentService batchAllotmentService;

    @PostMapping("/createBatch")
    public String createBatch(@RequestBody BatchAllotmentEntity entity) {
        return batchAllotmentService.createBatch(entity);
    }

    @PostMapping("/viewBatches")
    public List<BatchAllotmentModel> viewBatches(@RequestBody Map<String, String> requestBody) {
        return batchAllotmentService.getBatchesByDepartment(requestBody.get("department"));
    }

    @PostMapping("/deleteBatch")
    public String deleteBatch(@RequestBody BatchAllotmentEntity entity) {
        return batchAllotmentService.deleteBatch(entity);
    }
    
    @PostMapping("/checkBatchMentor")
    public boolean checkBatchMentor(@RequestBody Map<String, String> requestBody) {
        return batchAllotmentService.checkTeacherAsBatchMentor(requestBody.get("teacherCollegeID"));
    }

    @PostMapping("/fetchBatchByTeacherID")
    public BatchAllotmentEntity fetchbatchByTeacherID(@RequestBody Map<String, String> requestBody) {
        return batchAllotmentService.fetchBatchByClassTeacher(requestBody.get("teacherCollegeID"));
    }
}
