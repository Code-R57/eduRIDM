package com.ridm.eduRIDM.screen.timetableedit;

import static com.ridm.eduRIDM.MainActivity.firebaseQueries;
import static com.ridm.eduRIDM.MainActivity.roomRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ridm.eduRIDM.model.firebase.CourseClass;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class AddTimetableViewModel extends ViewModel {
    List<TimeTable> currentCourseList = new ArrayList<>();
    List<QueryDocumentSnapshot> coursesList = new ArrayList<>();
    int numberOfCards = 0;
    List<CourseClass> coursesToEnroll = new ArrayList<>();
    private final MutableLiveData<Boolean> navigateToHomeScreen = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToHomeScreen() {
        return navigateToHomeScreen;
    }

    public void onSubmitClicked() {
        navigateToHomeScreen.setValue(Boolean.TRUE);
    }

    public void onSkipClicked() {
        navigateToHomeScreen.setValue(Boolean.TRUE);
    }

    public void doneReachingHomeScreen() {
        navigateToHomeScreen.setValue(Boolean.FALSE);
    }

    public void getAllCourses() {
        coursesList = firebaseQueries.getCourses();
    }

    public TimeTable firebaseCourseToTimetable(CourseClass firebaseCourse) {
        TimeTable course = new TimeTable();

        course.setDeptCode(firebaseCourse.getDeptCode());
        course.setCourseCode(firebaseCourse.getCourseCode());
        course.setCourseName(firebaseCourse.getCourseName());
        course.setCredits(firebaseCourse.getCredits());

        return course;
    }

    public void enrollCourse(TimeTable course) {
        roomRepository.insertCourse(course);
    }

    public void addCourseToFirebase(String userKey, CourseClass course, String section) {
        firebaseQueries.addCourseToUserTimeTable(userKey, course, section);
    }

    public void insertInitialGrades(List<CurrentGrade> currentGradeList) {
        roomRepository.insertGrades(currentGradeList);
    }
}