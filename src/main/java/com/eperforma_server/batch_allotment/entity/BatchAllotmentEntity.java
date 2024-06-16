package com.eperforma_server.batch_allotment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "batch_allotment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchAllotmentEntity {
    @Column(name = "batch_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ID;

    @Column(name = "department")
    public String department;

    @Column(name = "year")
    public String year;

    @Column(name = "section")
    public String section;

    @Column(name = "starting_roll_no")
    public int startingRollNo;

    @Column(name = "ending_roll_no")
    public int endingRollNo;

    @Column(name = "batch_size")
    public int batchSize;

    @Column(name = "teacher_id")
    public String teacherID;

    @Column(name = "teacher_name")
    public String teacherName;
    
}
