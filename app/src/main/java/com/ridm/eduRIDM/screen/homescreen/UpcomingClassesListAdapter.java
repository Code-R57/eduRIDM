package com.ridm.eduRIDM.screen.homescreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UpcomingClassesListAdapter extends RecyclerView.Adapter<UpcomingClassesListAdapter.UpcomingClassesViewHolder> {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHECKBOX = "checkbox";
    private final Context mCtx;
    private final List<TimeTable> classList;
    private final String date;
    private final String today;
    private final String tomorrow;
    private final HashMap<String, Boolean> backlogMap;
    private boolean checkboxState;

    public UpcomingClassesListAdapter(Context mCtx, List<TimeTable> classList, String date, HashMap<String, Boolean> backlogMap) {
        this.mCtx = mCtx;
        this.classList = classList;
        this.date = date;
        this.backlogMap = backlogMap;

        Date today = new Date();
        this.today = new SimpleDateFormat("yyyy-MM-dd").format(today);
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        this.tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
    }

    @Override
    public UpcomingClassesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.classes_card, parent, false);
        return new UpcomingClassesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingClassesViewHolder holder, int position) {
        TimeTable lec = classList.get(position);
        String courseCode = lec.getDeptCode() + " " + lec.getCourseCode();
        holder.todayCourseCode.setText(courseCode);
        holder.todayCourseName.setText(lec.getCourseName());
        holder.todayClassTime.setText(lec.getTime());
        holder.todayLecture.setText(lec.getSection());

        if (!date.equals(today)) {
            holder.missed.setVisibility(View.GONE);
        } else {
            String key = lec.getCourseName() + " " + lec.getSection();
            if (backlogMap.get(key) != null && backlogMap.get(key) == Boolean.TRUE) {
                holder.missed.setChecked(true);
            }
        }

        holder.missed.setChecked(checkboxState);

        holder.missed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TimeTable tt = lec;

                Backlog backlog = new Backlog();
                backlog.setDeptCode(tt.getDeptCode());
                backlog.setCourseCode(tt.getCourseCode());
                backlog.setDate(date);
                backlog.setSection(tt.getSection());
                backlog.setExtraClass(Boolean.FALSE);
                backlog.setCourseName(tt.getCourseName());

                if (isChecked) {
                    MainActivity.roomRepository.insertBacklog(backlog);
                } else {
                    MainActivity.roomRepository.deleteBacklog(backlog);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    class UpcomingClassesViewHolder extends RecyclerView.ViewHolder {

        //Today
        TextView todayCourseCode, todayCourseName, todayLecture, todayClassTime;
        CheckBox missed, attended, holiday;

        public UpcomingClassesViewHolder(View view) {
            super(view);

            todayCourseCode = view.findViewById(R.id.tt_course_code_text);
            todayCourseName = view.findViewById(R.id.tt_course_name_text);
            todayLecture = view.findViewById(R.id.tt_lecture_text);
            todayClassTime = view.findViewById(R.id.tt_class_time_text);
            missed = view.findViewById(R.id.tt_missed_button);
        }
    }
}
