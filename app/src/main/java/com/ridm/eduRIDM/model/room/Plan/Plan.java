package com.ridm.eduRIDM.model.room.Plan;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Plan")
public class Plan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int planID;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "endTime")
    private String endTime;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "repeat")
    private boolean repeat;

    @ColumnInfo(name = "repeatOn")
    private String repeatOn;

    @ColumnInfo(name = "repeatFor")
    private String repeatFor;

    // Getters and Setters

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public String getRepeatOn() {
        return repeatOn;
    }

    public void setRepeatOn(String repeatOn) {
        this.repeatOn = repeatOn;
    }

    public String getRepeatFor() {
        return repeatFor;
    }

    public void setRepeatFor(String repeatFor) {
        this.repeatFor = repeatFor;
    }
}
