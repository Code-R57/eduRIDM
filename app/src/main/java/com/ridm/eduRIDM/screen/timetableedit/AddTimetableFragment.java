package com.ridm.eduRIDM.screen.timetableedit;

import static com.ridm.eduRIDM.MainActivity.account;
import static com.ridm.eduRIDM.MainActivity.firebaseQueries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentAddTimetableBinding;
import com.ridm.eduRIDM.model.firebase.CourseClass;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class AddTimetableFragment extends Fragment {

    AddTimetableViewModel viewModel;
    FragmentAddTimetableBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AddTimetableViewModel.class);

        viewModel.getAllCourses();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_timetable, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        binding.addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.numberOfCards += 1;
                viewModel.coursesToEnroll.add(new CourseClass());
                TimeTableCardAdapter adapter = new TimeTableCardAdapter(requireContext(), viewModel.coursesList, viewModel.coursesToEnroll, viewModel.numberOfCards);
                binding.courseList.setAdapter(adapter);

                int count = binding.courseList.getAdapter().getCount();

                for (int i = 0; i < count; i++) {
                    viewModel.coursesToEnroll.set(i, (CourseClass) binding.courseList.getAdapter().getItem(i));
                }
            }
        });

        viewModel.getNavigateToHomeScreen().observe(getViewLifecycleOwner(), navigateToHomeScreen -> {
            if (navigateToHomeScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addTimetableFragment_to_homeScreenFragment);
                viewModel.doneReachingHomeScreen();
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.courseList.getAdapter() != null) {
                    int numberOfCourses = binding.courseList.getAdapter().getCount();
                    List<CourseClass> courseList = new ArrayList<>();
                    List<CurrentGrade> currentGradeList = new ArrayList<>();

                    for (int i = 0; i < numberOfCourses; i++) {
                        courseList.add((CourseClass) binding.courseList.getAdapter().getItem(i));
                        CourseClass course = courseList.get(i);
                        String course_key = course.getDeptCode() + " " + course.getCourseCode();

                        CurrentGrade currentGrade = new CurrentGrade();

                        currentGrade.setDeptCode(course.getDeptCode());
                        currentGrade.setCourseCode(course.getCourseCode());
                        currentGrade.setCourseName(course.getCourseName());
                        currentGrade.setGrade("NA");
                        currentGrade.setCredits(course.getCredits());

                        currentGradeList.add(currentGrade);

                        if (course.getLecture() != null) {
                            TimeTable toEnroll = viewModel.firebaseCourseToTimetable(course);

                            DocumentReference documentReference = firebaseQueries.database.collection("courses").document(course_key)
                                    .collection("Sections").document(course.getLecture());

                            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot sectionDetails = task.getResult();
                                        String days = sectionDetails.getString("Days");
                                        int duration = Integer.parseInt(sectionDetails.get("Duration").toString());
                                        String time = sectionDetails.getString("Time");

                                        CourseClass.SectionDetail detail = new CourseClass.SectionDetail(days, duration, time);

                                        course.setLectureDetail(detail);

                                        toEnroll.setSection(course.getLecture());
                                        toEnroll.setDays(course.getLectureDetail().getDays());
                                        toEnroll.setTime(course.getLectureDetail().getTime());
                                        toEnroll.setDuration(course.getLectureDetail().getDuration());

                                        viewModel.enrollCourse(toEnroll);

                                        viewModel.addCourseToFirebase(account.getEmail(), course, "Lecture");
                                    } else {
                                        Toast.makeText(getContext(), "Unable to get section details.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        if (course.getTutorial() != null) {
                            TimeTable toEnroll = viewModel.firebaseCourseToTimetable(course);

                            DocumentReference documentReference = firebaseQueries.database.collection("courses").document(course_key)
                                    .collection("Sections").document(course.getTutorial());

                            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot sectionDetails = task.getResult();
                                        String days = sectionDetails.getString("Days");
                                        int duration = Integer.parseInt(sectionDetails.get("Duration").toString());
                                        String time = sectionDetails.getString("Time");

                                        CourseClass.SectionDetail detail = new CourseClass.SectionDetail(days, duration, time);

                                        course.setTutorialDetail(detail);

                                        toEnroll.setSection(course.getTutorial());
                                        toEnroll.setDays(course.getTutorialDetail().getDays());
                                        toEnroll.setTime(course.getTutorialDetail().getTime());
                                        toEnroll.setDuration(course.getTutorialDetail().getDuration());

                                        viewModel.enrollCourse(toEnroll);

                                        viewModel.addCourseToFirebase(account.getEmail(), course, "Tutorial");
                                    } else {
                                        Toast.makeText(getContext(), "Unable to get section details.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        if (course.getLab() != null) {
                            TimeTable toEnroll = viewModel.firebaseCourseToTimetable(course);

                            DocumentReference documentReference = firebaseQueries.database.collection("courses").document(course_key)
                                    .collection("Sections").document(course.getLab());

                            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot sectionDetails = task.getResult();
                                        String days = sectionDetails.getString("Days");
                                        int duration = Integer.parseInt(sectionDetails.get("Duration").toString());
                                        String time = sectionDetails.getString("Time");

                                        CourseClass.SectionDetail detail = new CourseClass.SectionDetail(days, duration, time);

                                        course.setLabDetail(detail);

                                        toEnroll.setSection(course.getLab());
                                        toEnroll.setDays(course.getLabDetail().getDays());
                                        toEnroll.setTime(course.getLabDetail().getTime());
                                        toEnroll.setDuration(course.getLabDetail().getDuration());

                                        viewModel.enrollCourse(toEnroll);

                                        viewModel.addCourseToFirebase(account.getEmail(), course, "Lab");
                                    } else {
                                        Toast.makeText(getContext(), "Unable to get section details.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    if (numberOfCourses > 0) {
                        viewModel.insertInitialGrades(currentGradeList);
                        viewModel.onSubmitClicked();
                    } else {
                        Toast.makeText(requireContext(), "No Course Selected", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "No Course Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}
