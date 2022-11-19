package com.ridm.eduRIDM.model.room.ExtraClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ExtraClass")
public class ExtraClass implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int extraClassID;

    @ColumnInfo(name = "deptCode")
    private String deptCode;

    @ColumnInfo(name = "courseCode")
    private String courseCode;

    @ColumnInfo(name = "section")
    private String section;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "duration")
    private int duration;

    // Getters and Setters

    public int getExtraClassID() {
        return extraClassID;
    }

    public void setExtraClassID(int extraClassID) {
        this.extraClassID = extraClassID;
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
}
