package com.eperforma_server.subjects.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectModel {
    public int ID;
    public String subjectName;
    public String subjectCode;
    public String teacherName;
    public String teacherID;
    public String department;
    public String semester;
    public String year;
    public String section;
}
