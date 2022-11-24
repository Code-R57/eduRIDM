package com.ridm.eduRIDM.screen.homescreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;
import com.ridm.eduRIDM.model.room.Eval.Eval;

import java.util.ArrayList;

public class UpcomingEvalsAdapter extends ArrayAdapter<Eval> {

    private ArrayList<Eval> upcomingEvalsList;
    Context mCtx;

    public static class ViewHolder {
        TextView deptCode;
        TextView courseCode;
        TextView courseName;
        TextView date;
        TextView time;
    }

    public UpcomingEvalsAdapter(ArrayList<Eval> upcomingEvalsList, Context mCtx) {
        super(mCtx, R.layout.upcoming_eval_card);
        this.upcomingEvalsList = upcomingEvalsList;
        this.mCtx = mCtx;
    }

    @Nullable
    @Override
    public Eval getItem(int position) {
        return upcomingEvalsList.get(position);
    }

    @Override
    public int getCount() {
        return upcomingEvalsList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Eval eval = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.upcoming_eval_card, parent, false);
            viewHolder.deptCode = (TextView) convertView.findViewById(R.id.upcoming_eval_dept_code);
//            viewHolder.courseCode = (TextView) convertView.findViewById(R.id.upcoming_eval_dept_code);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.upcoming_eval_course_name);
            viewHolder.date = (TextView) convertView.findViewById(R.id.upcoming_eval_date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.upcoming_eval_time);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.deptCode.setText(eval.getDeptCode());
        viewHolder.courseName.setText(eval.getCourseName());
        viewHolder.time.setText(eval.getTime());

        String[] dates = eval.getDate().split("-");
        String date = dates[2] + "/" + dates[1] + "/" + dates[0];
        viewHolder.date.setText(date);

        return convertView;
    }

}
