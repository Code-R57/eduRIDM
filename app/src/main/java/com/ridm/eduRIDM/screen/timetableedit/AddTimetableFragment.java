package com.ridm.eduRIDM.screen.timetableedit;

import static com.ridm.eduRIDM.MainActivity.firebaseQueries;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentAddTimetableBinding;
import com.ridm.eduRIDM.databinding.FragmentEditTimetableBinding;
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;
import com.ridm.eduRIDM.model.firebase.CourseClass;
import com.ridm.eduRIDM.model.firebase.FirebaseQueries;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;
import com.ridm.eduRIDM.screen.planner.PlannerViewModel;

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

                for(int i=0; i<count; i++) {
                    viewModel.coursesToEnroll.set(i, (CourseClass) binding.courseList.getAdapter().getItem(i));
                }
            }
        });

        viewModel.getNavigateToHomeScreen().observe(getViewLifecycleOwner(), navigateToHomeScreen -> {
            if(navigateToHomeScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addTimetableFragment_to_homeScreenFragment);
                viewModel.doneReachingHomeScreen();
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfCourses = binding.courseList.getAdapter().getCount();

                for(int i=0; i<numberOfCourses; i++) {
                    CourseClass course = (CourseClass) binding.courseList.getAdapter().getItem(i);

                    String course_key = course.getDeptCode() + " " + course.getCourseCode();

                    if(course.getLecture() != null) {
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

                                    toEnroll.setDeptCode(course.getDeptCode());
                                    toEnroll.setCourseCode(course.getCourseCode());
                                    toEnroll.setCourseName(course.getCourseName());
                                    toEnroll.setCredits(course.getCredits());

                                    viewModel.enrollCourse(toEnroll);
                                } else {
                                    Toast.makeText(getContext(), "Unable to get section details.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    if(course.getTutorial() != null) {
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

                                    toEnroll.setDeptCode(course.getDeptCode());
                                    toEnroll.setCourseCode(course.getCourseCode());
                                    toEnroll.setCourseName(course.getCourseName());
                                    toEnroll.setCredits(course.getCredits());

                                    viewModel.enrollCourse(toEnroll);
                                } else {
                                    Toast.makeText(getContext(), "Unable to get section details.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    if(course.getLab() != null) {
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

                                    toEnroll.setDeptCode(course.getDeptCode());
                                    toEnroll.setCourseCode(course.getCourseCode());
                                    toEnroll.setCourseName(course.getCourseName());
                                    toEnroll.setCredits(course.getCredits());

                                    viewModel.enrollCourse(toEnroll);
                                } else {
                                    Toast.makeText(getContext(), "Unable to get section details.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });

        return binding.getRoot();
    }
}
