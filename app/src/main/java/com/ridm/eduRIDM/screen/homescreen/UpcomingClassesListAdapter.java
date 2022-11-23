package com.ridm.eduRIDM.screen.homescreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.List;

public class UpcomingClassesListAdapter extends RecyclerView.Adapter<UpcomingClassesListAdapter.UpcomingClassesViewHolder> {

    private final Context mCtx;
    private final List<TimeTable> classList;

    public UpcomingClassesListAdapter(Context mCtx, List<TimeTable> classList) {
        this.mCtx = mCtx;
        this.classList = classList;
    }

    @Override
    public UpcomingClassesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.classes_card, parent, false);
        return new UpcomingClassesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingClassesViewHolder holder, int position) {
        TimeTable lec = classList.get(position);
        holder.todayCourseCode.setText(lec.getCourseCode());
        holder.todayCourseName.setText(lec.getCourseName());
        holder.todayClassTime.setText(lec.getTime());
        holder.todayLecture.setText(lec.getSection());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    class UpcomingClassesViewHolder extends RecyclerView.ViewHolder {

        //Today
        TextView todayCourseCode,todayCourseName,todayLecture,todayClassTime;

        public UpcomingClassesViewHolder(View view) {
            super(view);

            todayCourseCode = view.findViewById(R.id.tt_course_code_text);
            todayCourseName = view.findViewById(R.id.tt_course_name_text);
            todayLecture = view.findViewById(R.id.tt_lecture_text);
            todayClassTime = view.findViewById(R.id.tt_class_time_text);
        }



    }

}