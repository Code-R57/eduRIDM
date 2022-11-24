package com.ridm.eduRIDM.screen.timetableedit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.List;

public class TimeTableCardAdapter extends ArrayAdapter<TimeTable> {

    private List<TimeTable> courseList;
    private Context mCtx;
    private int numCards;

    public static class ViewHolder {
        Spinner deptCodeSpinner;
        Spinner courseCodeSpinner;
        TextView courseName;
        Spinner lectureSection;
        Spinner tutorialSection;
        Spinner labSection;
        ImageView removeCard;
    }

    @Nullable
    @Override
    public TimeTable getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public int getCount() {
        return Math.max(1, courseList.size());
    }

    public TimeTableCardAdapter(List<TimeTable> courseList, Context mCtx, int numCards) {
        super(mCtx, R.layout.course_card);
        this.courseList = courseList;
        this.mCtx = mCtx;
        this.numCards = Math.max(numCards, 1);
    }

    private int lastPosition = -1;

    public View getView(int position, View convertView, ViewGroup parent) {
        TimeTable course = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.course_card, parent, false);

            viewHolder.deptCodeSpinner = (Spinner) convertView.findViewById(R.id.branch_spin);
            viewHolder.courseCodeSpinner = (Spinner) convertView.findViewById(R.id.code_spin);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.course_name_timetable);
            viewHolder.lectureSection = (Spinner) convertView.findViewById(R.id.lec_sel);
            viewHolder.tutorialSection = (Spinner) convertView.findViewById(R.id.tut_sel);
            viewHolder.labSection = (Spinner) convertView.findViewById(R.id.lab_sel);
            viewHolder.removeCard = (ImageView) convertView.findViewById(R.id.remove_card);

            result = convertView;

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        return convertView;
    }
}
