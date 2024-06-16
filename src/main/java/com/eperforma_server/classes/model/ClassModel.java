package com.eperforma_server.classes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClassModel {
    public int ID;
    public String year;
    public String section;
    public String teacherName;
    public String department;
    public String teacherID;
}
