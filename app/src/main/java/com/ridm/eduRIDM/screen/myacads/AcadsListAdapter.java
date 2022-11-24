package com.ridm.eduRIDM.screen.myacads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.HashMap;
import java.util.List;

public class AcadsListAdapter extends RecyclerView.Adapter<AcadsListAdapter.AcadsViewHolder> {

    private final Context mCtx;
    private final List<TimeTable> courseList;
    private final List<Eval> evalList;
    private final String currentSelection;
    private final HashMap<String, List<Backlog>> backlogListMap;

    public AcadsListAdapter(Context mCtx, List<TimeTable> courseList, List<Eval> evalList, String currentSelection, HashMap<String, List<Backlog>> backlogListMap) {
        this.mCtx = mCtx;
        this.courseList = courseList;
        this.evalList = evalList;
        this.currentSelection = currentSelection;
        this.backlogListMap = backlogListMap;
    }

    @Override
    public AcadsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (currentSelection.equals("Evals")) {
            view = LayoutInflater.from(mCtx).inflate(R.layout.evaluatives_card, parent, false);
        } else {
            view = LayoutInflater.from(mCtx).inflate(R.layout.backlog_card, parent, false);
        }

        return new AcadsViewHolder(view, currentSelection);
    }

    @Override
    public void onBindViewHolder(@NonNull AcadsViewHolder holder, int position) {
        if (currentSelection.equals("Evals")) {
            Eval eval = evalList.get(position);
            holder.evalDeptCode.setText(eval.getDeptCode());
            holder.evalCourseCode.setText(eval.getCourseCode());
            holder.evalCourseName.setText(eval.getCourseName());
            holder.time.setText(eval.getTime());
            holder.date.setText(eval.getDate());
            holder.duration.setText(String.valueOf(eval.getDuration()));
            holder.nature.setText(eval.getNature());
            holder.typeText.setText(eval.getType());
            holder.syllabusDesc.setText(eval.getSyllabus());
        } else {
            TimeTable course = courseList.get(position);
            holder.backlogDeptCode.setText(course.getDeptCode());
            holder.backlogCourseCode.setText(course.getCourseCode());
            holder.backlogCourseName.setText(course.getCourseName());

            BacklogListAdapter listAdapter = new BacklogListAdapter(backlogListMap.get(courseList.get(position).getCourseName()), mCtx.getApplicationContext());

            holder.backlogListView.setAdapter(listAdapter);
        }
    }

    @Override
    public int getItemCount() {
        if (currentSelection.equals("Evals")) {
            return evalList.size();
        } else {
            return courseList.size();
        }
    }

    class AcadsViewHolder extends RecyclerView.ViewHolder {
        // Evals Card
        TextView evalDeptCode, evalCourseCode, evalCourseName, time, date, typeText, syllabusDesc, duration, nature;

        // Backlog Card
        TextView backlogDeptCode, backlogCourseCode, backlogCourseName;
        ListView backlogListView;

        public AcadsViewHolder(View view, String currentSelection) {
            super(view);

            if (currentSelection.equals("Evals")) {
                evalDeptCode = view.findViewById(R.id.eval_dept_code);
                evalCourseCode = view.findViewById(R.id.course_code_eval_card);
                evalCourseName = view.findViewById(R.id.course_name);
                time = view.findViewById(R.id.time);
                date = view.findViewById(R.id.date);
                typeText = view.findViewById(R.id.type_text);
                syllabusDesc = view.findViewById(R.id.syllabus_description);
                nature = view.findViewById(R.id.nature_value);
                duration = view.findViewById(R.id.duration_value);
            } else {
                backlogDeptCode = view.findViewById(R.id.textView_course_id);
                backlogCourseCode = view.findViewById(R.id.textView_course_code);
                backlogCourseName = view.findViewById(R.id.textView_course_name);
                backlogListView = view.findViewById(R.id.backlog_list);
            }
        }
    }
}
