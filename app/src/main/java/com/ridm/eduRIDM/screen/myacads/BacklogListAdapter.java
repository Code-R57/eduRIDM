package com.ridm.eduRIDM.screen.myacads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;

import java.util.ArrayList;
import java.util.List;

public class BacklogListAdapter extends ArrayAdapter<Backlog> implements View.OnLongClickListener {

    private List<Backlog> backlogList;
    private Context mCtx;

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public static class ViewHolder {
        TextView backlogType;
        TextView backlogDate;
        TextView extraClass;
        CheckBox backlogDone;
    }

    public BacklogListAdapter(List<Backlog> backlogList, Context mCtx) {
        super(mCtx, R.layout.backlog_list_item);
        this.backlogList = backlogList;
        this.mCtx = mCtx;
    }

    @Nullable
    @Override
    public Backlog getItem(int position) {
        return backlogList.get(position);
    }

    @Override
    public int getCount() {
        return backlogList.size();
    }

    private int lastPosition = -1;

    public View getView(int position, View convertView, ViewGroup parent) {
        Backlog backlog = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.backlog_list_item, parent, false);
            viewHolder.backlogType = (TextView) convertView.findViewById(R.id.textView_backlog_type);
            viewHolder.backlogDate = (TextView) convertView.findViewById(R.id.textView_date_backlog);
            viewHolder.extraClass = (TextView) convertView.findViewById(R.id.textView_extra_class);
            viewHolder.backlogDone = (CheckBox) convertView.findViewById(R.id.checkBox);

            result = convertView;

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.backlogType.setText(backlog.getSection());
        viewHolder.backlogDate.setText(backlog.getDate());

        if(!backlog.isExtraClass()) {
            viewHolder.extraClass.setVisibility(View.GONE);
        }

        return convertView;
    }
}
