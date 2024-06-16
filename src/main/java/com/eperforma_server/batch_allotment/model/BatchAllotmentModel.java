package com.eperforma_server.batch_allotment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchAllotmentModel {
    public int ID;
    public String department;
    public String year;
    public String section;    
    public int startingRollNo;
    public int endingRollNo;
    public int batchSize;
    public String teacherID;
    public String teacherName;
}
