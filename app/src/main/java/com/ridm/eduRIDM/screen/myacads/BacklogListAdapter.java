package com.ridm.eduRIDM.screen.myacads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.screen.myacads.AcadsListAdapter.OnItemClickListener;

import java.util.List;

public class BacklogListAdapter extends ArrayAdapter<Backlog> {

    private final List<Backlog> backlogList;
    private final Context mCtx;
    private final OnItemClickListener listener;
    private final int lastPosition = -1;

    public BacklogListAdapter(List<Backlog> backlogList, Context mCtx, OnItemClickListener listener) {
        super(mCtx, R.layout.backlog_list_item);
        this.backlogList = backlogList;
        this.mCtx = mCtx;
        this.listener = listener;
    }

    @Nullable
    @Override
    public Backlog getItem(int position) {
        return backlogList.get(position);
    }

    @Override
    public int getCount() {
        if (backlogList == null) {
            return 0;
        }
        return backlogList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Backlog backlog = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.backlog_list_item, parent, false);
            viewHolder.backlogType = (TextView) convertView.findViewById(R.id.textView_backlog_type);
            viewHolder.backlogDate = (TextView) convertView.findViewById(R.id.textView_date_backlog);
            viewHolder.extraClass = (TextView) convertView.findViewById(R.id.textView_extra_class);
            viewHolder.backlogDone = (ImageButton) convertView.findViewById(R.id.checkBox);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.backlogType.setText(backlog.getSection());
        viewHolder.backlogDate.setText(backlog.getDate());

        if (!backlog.isExtraClass()) {
            viewHolder.extraClass.setVisibility(View.GONE);
        }

        viewHolder.backlogDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBacklogItemClick(backlog);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView backlogType;
        TextView backlogDate;
        TextView extraClass;
        ImageButton backlogDone;
    }
}

