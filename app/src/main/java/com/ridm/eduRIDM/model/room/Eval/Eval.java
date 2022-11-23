package com.ridm.eduRIDM.model.room.Eval;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Eval")
public class Eval implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int evalID;

    @ColumnInfo(name = "deptCode")
    private String deptCode;

    @ColumnInfo(name = "courseCode")
    private String courseCode;

    @ColumnInfo(name = "courseName")
    private String courseName;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "duration")
    private int duration;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "syllabus")
    private String syllabus;

    @ColumnInfo(name = "nature")
    private String nature;

    // Getters and Setters

    public int getEvalID() {
        return evalID;
    }

    public void setEvalID(int evalID) {
        this.evalID = evalID;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
