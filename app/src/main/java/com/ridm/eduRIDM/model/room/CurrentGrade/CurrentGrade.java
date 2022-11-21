package com.ridm.eduRIDM.model.room.CurrentGrade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "CurrentGrade", primaryKeys = {"deptCode", "courseCode"})
public class CurrentGrade  implements Serializable {

    @NotNull
    @ColumnInfo(name = "deptCode")
    private String deptCode;

    @NotNull
    @ColumnInfo(name = "courseCode")
    private String courseCode;

    @ColumnInfo(name = "grade")
    private String grade;

    // Getters and Setters

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
