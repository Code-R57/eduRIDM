package com.ridm.eduRIDM.screen.timetableedit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.firebase.CourseClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TimeTableCardAdapter extends ArrayAdapter<CourseClass> {

    private final Context mCtx;
    private final List<QueryDocumentSnapshot> allCourseList;
    private final List<String> deptCodes;
    private final Map<String, List<String>> courseCodes;
    private final Map<String, String> courseName;
    private final Map<String, List<String>> lectureSections;
    private final Map<String, List<String>> tutorialSections;
    private final Map<String, List<String>> labSections;
    private final int lastPosition = -1;
    private final List<CourseClass> coursesToEnroll;
    private final Map<String, Integer> creditMap;

    public TimeTableCardAdapter(Context mCtx, List<QueryDocumentSnapshot> allCourseList, List<CourseClass> coursesToEnroll, int numCards) {
        super(mCtx, R.layout.course_card);
        this.mCtx = mCtx;
        this.allCourseList = allCourseList;

        this.coursesToEnroll = coursesToEnroll;

        Set<String> deptCodesSet = new HashSet<>();
        Map<String, List<String>> courseCodesMap = new HashMap<>();
        Map<String, String> courseNameMap = new HashMap<>();

        this.labSections = new HashMap<>();
        this.tutorialSections = new HashMap<>();
        this.lectureSections = new HashMap<>();
        this.creditMap = new HashMap<>();

        for (QueryDocumentSnapshot course : this.allCourseList) {
            String deptCode = course.getString("DeptCode");
            String courseCode = course.getString("CourseCode");
            String name = course.getString("Course Name");

            deptCodesSet.add(deptCode);
            if (courseCodesMap.get(deptCode) == null) {
                List<String> courseCodeList = new ArrayList<>();
                courseCodeList.add(courseCode);
                courseCodesMap.put(deptCode, courseCodeList);
            } else {
                courseCodesMap.get(deptCode).add(courseCode);
            }

            String key = deptCode + " " + courseCode;

            courseNameMap.put(key, name);
            lectureSections.put(key, (List<String>) course.get("LectureSection"));
            tutorialSections.put(key, (List<String>) course.get("TutorialSection"));
            labSections.put(key, (List<String>) course.get("PracticalSection"));
            creditMap.put(key, Integer.parseInt(course.get("Credits").toString()));
        }

        this.courseName = new HashMap<>(courseNameMap);
        this.courseCodes = new HashMap<>(courseCodesMap);
        this.deptCodes = new ArrayList<>(deptCodesSet);
    }

    @Nullable
    @Override
    public CourseClass getItem(int position) {
        return coursesToEnroll.get(position);
    }

    @Override
    public int getCount() {
        return coursesToEnroll.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CourseClass course = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.course_card, parent, false);

            viewHolder.deptCodeSpinner = (Spinner) convertView.findViewById(R.id.branch_spin);
            viewHolder.courseCodeSpinner = (Spinner) convertView.findViewById(R.id.code_spin);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.course_name_timetable);
            viewHolder.lectureSection = (Spinner) convertView.findViewById(R.id.lec_sel);
            viewHolder.tutorialSection = (Spinner) convertView.findViewById(R.id.tut_sel);
            viewHolder.labSection = (Spinner) convertView.findViewById(R.id.lab_sel);
//            viewHolder.removeCard = convertView.findViewById(R.id.remove_card);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        ArrayAdapter<String> deptCodeSpinnerAdapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item, deptCodes);
        viewHolder.deptCodeSpinner.setAdapter(deptCodeSpinnerAdapter);

//        if(course != null) {
//            viewHolder.deptCodeSpinner.setSelection(deptCodes.indexOf(course.getDeptCode()));
//            viewHolder.courseCodeSpinner.setSelection(courseCodes.get(course.getDeptCode()).indexOf(course.getCourseCode()));
//            viewHolder.courseName.setText(course.getCourseName());
//
//            if(course.getLecture() != null) {
//                viewHolder.lectureSection.setSelection(lectureSections.get(course.getDeptCode() + " " + course.getCourseCode()).indexOf(course.getLecture()));
//            }
//
//            if(course.getTutorial() != null) {
//                viewHolder.tutorialSection.setSelection(tutorialSections.get(course.getDeptCode() + " " + course.getCourseCode()).indexOf(course.getTutorial()));
//            }
//
//            if(course.getLab() != null) {
//                viewHolder.labSection.setSelection(labSections.get(course.getDeptCode() + " " + course.getCourseCode()).indexOf(course.getLab()));
//            }
//        }

        viewHolder.deptCodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDeptCode = viewHolder.deptCodeSpinner.getSelectedItem().toString();
                List<String> courseCodesList = new ArrayList<>(courseCodes.get(selectedDeptCode));
                ArrayAdapter<String> courseCodeSpinnerAdapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item, courseCodesList);
                viewHolder.courseCodeSpinner.setAdapter(courseCodeSpinnerAdapter);

                course.setDeptCode(selectedDeptCode);

                viewHolder.courseCodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedCourseCode = viewHolder.courseCodeSpinner.getSelectedItem().toString();
                        String keyPair = selectedDeptCode + " " + selectedCourseCode;

                        course.setCourseCode(selectedCourseCode);

                        String courseNameDisplay = "No Course Selected";

                        if (courseName.get(keyPair) != null) {
                            courseNameDisplay = courseName.get(keyPair);
                            course.setCourseName(courseNameDisplay);
                            course.setCredits(creditMap.get(keyPair));
                        }

                        viewHolder.courseName.setText(courseNameDisplay);

                        if (lectureSections.get(keyPair) != null) {
                            ArrayAdapter<String> lectureAdapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item, lectureSections.get(keyPair));
                            viewHolder.lectureSection.setAdapter(lectureAdapter);

                            viewHolder.lectureSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    course.setLecture(viewHolder.lectureSection.getSelectedItem().toString());
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                }
                            });
                        } else {
                            viewHolder.lectureSection.setAdapter(null);
                            course.setLecture(null);
                        }

                        if (tutorialSections.get(keyPair) != null) {
                            ArrayAdapter<String> tutorialAdapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item, tutorialSections.get(keyPair));
                            viewHolder.tutorialSection.setAdapter(tutorialAdapter);

                            viewHolder.tutorialSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    course.setTutorial(viewHolder.tutorialSection.getSelectedItem().toString());
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                }
                            });
                        } else {
                            viewHolder.tutorialSection.setAdapter(null);
                            course.setTutorial(null);
                        }

                        if (labSections.get(keyPair) != null) {
                            ArrayAdapter<String> labAdapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item, labSections.get(keyPair));
                            viewHolder.labSection.setAdapter(labAdapter);

                            viewHolder.labSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    course.setLab(viewHolder.labSection.getSelectedItem().toString());
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                }
                            });
                        } else {
                            viewHolder.labSection.setAdapter(null);
                            course.setLab(null);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        Spinner deptCodeSpinner;
        Spinner courseCodeSpinner;
        TextView courseName;
        Spinner lectureSection;
        Spinner tutorialSection;
        Spinner labSection;
//        ImageView removeCard;
    }
}
