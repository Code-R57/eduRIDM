package com.ridm.eduRIDM.screen.updatecgpa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;

import java.util.ArrayList;

public class CgpaListAdapter extends ArrayAdapter<CurrentGrade>{

    private ArrayList<CurrentGrade> currentGradesList;
    Context mCtx;

    public static class ViewHolder {
        TextView deptCode;
        TextView courseCode;
        TextView courseName;
        Spinner gradeSelector;
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

    public CgpaListAdapter(ArrayList<CurrentGrade> currentGradesList, Context mCtx) {
        super(mCtx, R.layout.grade_card);
        this.currentGradesList = currentGradesList;
        this.mCtx = mCtx;
    }

    private int lastPosition = -1;

    public View getView(int position, View convertView, ViewGroup parent) {
        CurrentGrade currentGrade = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.grade_card, parent, false);
            viewHolder.courseCode = (TextView) convertView.findViewById(R.id.dept_code);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.grade_course_name);
            viewHolder.gradeSelector = (Spinner) convertView.findViewById(R.id.grade_sel);

            result = convertView;

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.deptCode.setText(currentGrade.getDeptCode());
        viewHolder.courseCode.setText(currentGrade.getCourseCode());
        viewHolder.courseName.setText(currentGrade.getCourseName());

        return convertView;
    }
}
