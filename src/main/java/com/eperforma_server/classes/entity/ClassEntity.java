package com.eperforma_server.classes.entity;

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
@Table(name = "class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Column(name = "class_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ID;

    @Column(name = "year")
    public String year;

    @Column(name = "section")
    public String section;

    @Column(name = "teacher_name")
    public String teacherName;

    @Column(name = "department")
    public String department;

    @Column(name = "teacher_id")
    public String teacherID;
}
