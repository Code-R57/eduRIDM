package com.ridm.eduRIDM.model.room.CurrentGrade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "CurrentGrade", primaryKeys = {"deptCode", "courseCode"})
public class CurrentGrade implements Serializable {

    @NotNull
    @ColumnInfo(name = "deptCode")
    private String deptCode;

    @NotNull
    @ColumnInfo(name = "courseCode")
    private String courseCode;

    @ColumnInfo(name = "courseName")
    private String courseName;

    @ColumnInfo(name = "grade")
    private String grade;

    @ColumnInfo(name = "credits")
    private int credits;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
