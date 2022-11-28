package com.ridm.eduRIDM.screen.updatecgpa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;

import java.util.List;

public class CgpaListAdapter extends ArrayAdapter<CurrentGrade> {

    private final List<CurrentGrade> currentGradesList;
    private final Context mCtx;
    private final int lastPosition = -1;

    public CgpaListAdapter(List<CurrentGrade> currentGradesList, Context mCtx) {
        super(mCtx, R.layout.grade_card);
        this.currentGradesList = currentGradesList;
        this.mCtx = mCtx;
    }

    @Nullable
    @Override
    public CurrentGrade getItem(int position) {
        return currentGradesList.get(position);
    }

    @Override
    public int getCount() {
        return currentGradesList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CurrentGrade currentGrade = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.grade_card, parent, false);
            viewHolder.deptCode = convertView.findViewById(R.id.dept_code_grade);
            viewHolder.courseCode = convertView.findViewById(R.id.course_code_grade);
            viewHolder.courseName = convertView.findViewById(R.id.grade_course_name);
            viewHolder.gradeSelector = convertView.findViewById(R.id.grade_sel);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.deptCode.setText(currentGrade.getDeptCode());
        viewHolder.courseCode.setText(currentGrade.getCourseCode());
        viewHolder.courseName.setText(currentGrade.getCourseName());

        int spinnerValue = 0;

        switch (currentGrade.getGrade()) {
            case "A":
                spinnerValue = 0;
                break;
            case "A-":
                spinnerValue = 1;
                break;
            case "B":
                spinnerValue = 2;
                break;
            case "B-":
                spinnerValue = 3;
                break;
            case "C":
                spinnerValue = 4;
                break;
            case "C-":
                spinnerValue = 5;
                break;
            case "D":
                spinnerValue = 6;
                break;
            case "E":
                spinnerValue = 7;
                break;
            case "NC":
                spinnerValue = 8;
                break;
            default:
                spinnerValue = 0;
                break;
        }

        viewHolder.gradeSelector.setSelection(spinnerValue);

        viewHolder.gradeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentGrade.setGrade(viewHolder.gradeSelector.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                currentGrade.setGrade("NA");
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView deptCode;
        TextView courseCode;
        TextView courseName;
        Spinner gradeSelector;
    }
}
