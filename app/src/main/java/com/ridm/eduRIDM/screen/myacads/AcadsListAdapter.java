package com.ridm.eduRIDM.screen.myacads;

import static com.ridm.eduRIDM.MainActivity.roomRepository;

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
import com.ridm.eduRIDM.model.room.TimeTable.DistinctClasses;

import java.util.HashMap;
import java.util.List;

public class AcadsListAdapter extends RecyclerView.Adapter<AcadsListAdapter.AcadsViewHolder> {

    private final Context mCtx;
    private final List<DistinctClasses> courseList;
    private final List<Eval> evalList;
    private final String currentSelection;
    private final HashMap<String, List<Backlog>> backlogListMap;
    private final MyAcadsFragment.OnItemDeleteClickListener listener;

    public AcadsListAdapter(Context mCtx, List<DistinctClasses> courseList, List<Eval> evalList, String currentSelection, HashMap<String, List<Backlog>> backlogListMap, MyAcadsFragment.OnItemDeleteClickListener listener) {
        this.mCtx = mCtx;
        this.courseList = courseList;
        this.evalList = evalList;
        this.currentSelection = currentSelection;
        this.backlogListMap = backlogListMap;
        this.listener = listener;
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
            holder.evalCourseName.setText(eval.getCourseName());
            holder.time.setText(eval.getTime());

            String[] dates = eval.getDate().split("-");
            String date = dates[2] + "/" + dates[1] + "/" + dates[0];
            holder.date.setText(date);

            holder.nature.setText(eval.getNature());
            holder.typeText.setText(eval.getType());
            holder.syllabusDesc.setText(eval.getSyllabus());
        } else {
            DistinctClasses course = courseList.get(position);
            holder.backlogDeptCode.setText(course.getDeptCode());
            holder.backlogCourseCode.setText(course.getCourseCode());
            holder.backlogCourseName.setText(course.getCourseName());

            BacklogListAdapter listAdapter = new BacklogListAdapter(backlogListMap.get(course.getCourseName()), mCtx.getApplicationContext(), new OnItemClickListener() {
                @Override
                public void onBacklogItemClick(Backlog backlog) {
                    roomRepository.deleteBacklog(backlog);
                    backlogListMap.get(course.getCourseName()).remove(backlog);

                    listener.onBacklogItemDeleted();
                    notifyDataSetChanged();
                }
            });

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
        TextView evalDeptCode, evalCourseCode, evalCourseName, time, date, typeText, syllabusDesc, nature;

        // Backlog Card
        TextView backlogDeptCode, backlogCourseCode, backlogCourseName;
        ListView backlogListView;

        public AcadsViewHolder(View view, String currentSelection) {
            super(view);

            if (currentSelection.equals("Evals")) {
                evalCourseName = view.findViewById(R.id.course_name);
                time = view.findViewById(R.id.time);
                date = view.findViewById(R.id.date);
                typeText = view.findViewById(R.id.type_text);
                syllabusDesc = view.findViewById(R.id.syllabus_description);
                nature = view.findViewById(R.id.nature_value);
            } else {
                backlogDeptCode = view.findViewById(R.id.textView_course_id);
                backlogCourseCode = view.findViewById(R.id.textView_course_code);
                backlogCourseName = view.findViewById(R.id.textView_course_name);
                backlogListView = view.findViewById(R.id.backlog_list);
            }
        }
    }

    public interface OnItemClickListener {
        void onBacklogItemClick(Backlog backlog);
    }
}
