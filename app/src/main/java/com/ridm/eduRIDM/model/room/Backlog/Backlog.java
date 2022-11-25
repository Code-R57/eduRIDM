package com.ridm.eduRIDM.model.room.Backlog;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Backlog", primaryKeys = {"deptCode", "courseCode", "section", "date", "extraClass"})
public class Backlog implements Serializable {

    @NonNull
    @ColumnInfo(name = "deptCode")
    private String deptCode;

    @NonNull
    @ColumnInfo(name = "courseCode")
    private String courseCode;

    @NonNull
    @ColumnInfo(name = "section")
    private String section;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @NonNull
    @ColumnInfo(name = "extraClass")
    private boolean extraClass;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isExtraClass() {
        return extraClass;
    }

    public void setExtraClass(boolean extraClass) {
        this.extraClass = extraClass;
    }
}
