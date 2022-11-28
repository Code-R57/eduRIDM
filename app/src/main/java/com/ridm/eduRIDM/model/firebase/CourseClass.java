package com.ridm.eduRIDM.model.firebase;

public class CourseClass {
    String courseName;
    String deptCode;
    String courseCode;
    int credits;
    String lecture;
    String tutorial;
    String lab;
    SectionDetail lectureDetail;
    SectionDetail tutorialDetail;
    SectionDetail labDetail;

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public SectionDetail getLabDetail() {
        return labDetail;
    }

    public void setLabDetail(SectionDetail labDetail) {
        this.labDetail = labDetail;
    }

    public SectionDetail getLectureDetail() {
        return lectureDetail;
    }

    public void setLectureDetail(SectionDetail lectureDetail) {
        this.lectureDetail = lectureDetail;
    }

    public SectionDetail getTutorialDetail() {
        return tutorialDetail;
    }

    public void setTutorialDetail(SectionDetail tutorialDetail) {
        this.tutorialDetail = tutorialDetail;
    }

    public static class SectionDetail {
        String days;
        int duration;
        String time;

        public SectionDetail(String days, int duration, String time) {
            this.days = days;
            this.duration = duration;
            this.time = time;
        }

        // Getters and Setters

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }
    }
}
