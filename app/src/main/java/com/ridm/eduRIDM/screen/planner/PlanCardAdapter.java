package com.ridm.eduRIDM.screen.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.Plan.Plan;

import java.util.List;

public class PlanCardAdapter extends RecyclerView.Adapter<PlanCardAdapter.PlanViewHolder> {

    private final Context context;
    private final List<Plan> planList;
    private final String today;
    private final String date;

    public PlanCardAdapter(Context context, List<Plan> planList, String today, String date) {
        this.context = context;
        this.planList = planList;
        this.today = today;
        this.date = date;
    }

    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.planner_card, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        Plan plan = planList.get(position);
        holder.planName.setText(plan.getTitle());
        holder.descInfo.setText(plan.getDescription());
        String duration = plan.getStartTime() + " - " + plan.getEndTime();
        holder.timeDuration.setText(duration);

        String priority = "Low";

        if (plan.getPriority() == 1) {
            priority = "High";
        }

        holder.priorityInfo.setText(priority);

        if (!date.equals(today)) {
            holder.attendedButton.setVisibility(View.GONE);
        }

        holder.attendedButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.attendedButton.setChecked(false);
                MainActivity.roomRepository.deletePlan(plan);
                planList.remove(plan);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    class PlanViewHolder extends RecyclerView.ViewHolder {

        TextView timeDuration, planName, priorityInfo, descInfo;
        CheckBox attendedButton;
        ImageView moreOptions;

        PlanViewHolder(View itemView) {
            super(itemView);

            timeDuration = (TextView) itemView.findViewById(R.id.time_duration);
            planName = (TextView) itemView.findViewById(R.id.plan_name);
            priorityInfo = (TextView) itemView.findViewById(R.id.priority_info_value);
            descInfo = (TextView) itemView.findViewById(R.id.desc_info);
            attendedButton = (CheckBox) itemView.findViewById(R.id.attended_button);
//            moreOptions = (ImageView) itemView.findViewById(R.id.more_options);
        }
    }
}
