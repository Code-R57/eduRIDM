package com.ridm.eduRIDM.model.room.TimeTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "TimeTable", primaryKeys = {"deptCode", "courseCode", "section"})
public class TimeTable implements Serializable {

    @NotNull
    @ColumnInfo(name = "deptCode")
    private String deptCode;

    @NotNull
    @ColumnInfo(name = "courseCode")
    private String courseCode;

    @NotNull
    @ColumnInfo(name = "section")
    private String section;

    @ColumnInfo(name = "courseName")
    private String courseName;

    @ColumnInfo(name = "days")
    private String days;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "duration")
    private int duration;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
