package com.xuesen.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuesen.R;
import com.xuesen.modle.Action;

import java.util.List;

/**
 *  * Created by 智光 on 2018/1/12 12:04
 *  
 */

public class ActionListAdapter extends RecyclerView.Adapter {

    private List<Action> actions;
    private Context context;
    private LayoutInflater inflater;

    public ActionListAdapter(List<Action> actions, Context context) {
        this.actions = actions;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActionViewHodler(inflater.inflate(R.layout.action_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ActionViewHodler) {
            ActionViewHodler viewHolder = (ActionViewHodler) holder;
            Action action = actions.get(position);
            viewHolder.action_name.setText(action.getName());
            int color = 303090 + position * 120;
            viewHolder.action_name.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + color)));

        }
    }

    @Override
    public int getItemCount() {
        return actions == null ? 0 : actions.size();
    }

    class ActionViewHodler extends RecyclerView.ViewHolder {
        TextView action_name;

        public ActionViewHodler(View itemView) {
            super(itemView);
            action_name = itemView.findViewById(R.id.action_name);
        }
    }
}
