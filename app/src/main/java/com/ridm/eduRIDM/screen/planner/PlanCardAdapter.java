package com.ridm.eduRIDM.screen.planner;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.Plan.Plan;

import java.util.ArrayList;

public class PlanCardAdapter extends RecyclerView.Adapter<PlanCardAdapter.PlanHolder> {

    private Context context;
    private ArrayList<Plan> planList;

    public PlanCardAdapter(Context context, ArrayList<Plan> planList) {
        this.context = context;
        this.planList = planList;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.planner_card, parent, false);
        return new PlanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        Plan plan = planList.get(position);
        holder.setDetails(plan);
    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    class PlanHolder extends RecyclerView.ViewHolder {
        private TextView time_duration, plan_name, priority_info, desc_info;
        private ImageButton attended_button;
        private ImageView more_options;

        PlanHolder(View itemView) {
            super(itemView);
            time_duration = itemView.findViewById(R.id.time_duration);
            plan_name = itemView.findViewById(R.id.plan_name);
            priority_info = itemView.findViewById(R.id.priority_info);
            desc_info = itemView.findViewById(R.id.desc_info);
            attended_button = itemView.findViewById(R.id.attended_button);
            more_options = itemView.findViewById(R.id.more_options);
        }

        void setDetails(Plan plan) {
            String startTime = plan.getStartTime();
            String endTime = plan.getEndTime();

            time_duration.setText(startTime + "-" + endTime);
            plan_name.setText(plan.getTitle());

            int priority = plan.getPriority();
            if (priority == 0) {
                priority_info.setText("Low");
            } else {
                priority_info.setText("High");
            }

            desc_info.setText(plan.getDescription());
            attended_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });

            more_options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }
}
