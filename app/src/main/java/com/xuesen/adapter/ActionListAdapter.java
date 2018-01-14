package com.xuesen.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuesen.R;
import com.xuesen.activity.CountActivity;
import com.xuesen.activity.MainActivity;
import com.xuesen.db.ActionDao;
import com.xuesen.db.DBManager;
import com.xuesen.helper.Parameter;
import com.xuesen.modle.Action;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 *  * Created by 智光 on 2018/1/12 12:04
 *  
 */

public class ActionListAdapter extends RecyclerView.Adapter {

    private List<Action> actions;
    private Context mContext;
    private LayoutInflater inflater;

    private boolean edit = false;

    public ActionListAdapter(List<Action> actions, Context context) {
        this.actions = actions;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
        notifyDataSetChanged();
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
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
            final Action action = actions.get(position);
            viewHolder.action_name.setText(action.getName());
            int color = 303090 + position * 120;
            viewHolder.action_name.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + color)));
            viewHolder.action_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CountActivity.class);
                    intent.putExtra(Parameter.INTENT_NAME,action.getName());
                    intent.putExtra(Parameter.INTENT_DATE,action.getDate());
                    mContext.startActivity(intent);
                }
            });
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBManager.getInstance(mContext).getWritableDaoSession().getActionDao().delete(action);
                    actions = DBManager.getInstance(mContext).queryActionList();
                    notifyDataSetChanged();
                }
            });
            if (edit) {
                viewHolder.delete.setVisibility(View.VISIBLE);
            } else {
                viewHolder.delete.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return actions == null ? 0 : actions.size();
    }

    class ActionViewHodler extends RecyclerView.ViewHolder {
        TextView action_name;
        TextView delete;

        public ActionViewHodler(View itemView) {
            super(itemView);
            action_name = itemView.findViewById(R.id.action_name);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
