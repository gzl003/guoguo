package com.xuesen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuesen.R;
import com.xuesen.modle.ActionCount;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */

public class HistCountAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ActionCount> countList;
    private LayoutInflater inflater;

    public void setCountList(List<ActionCount> countList) {
        this.countList = countList;
        notifyDataSetChanged();
    }

    public HistCountAdapter(Context mContext, List<ActionCount> countList) {
        this.mContext = mContext;
        this.countList = countList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountActionHodler(inflater.inflate(R.layout.countlist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ActionCount actionCount = countList.get(position);
        if (holder instanceof CountActionHodler && actionCount != null) {
            CountActionHodler countActionHodler = (CountActionHodler) holder;
            countActionHodler.count_name.setText(actionCount.getName());
            countActionHodler.count_time.setText(actionCount.getDate());
            countActionHodler.count_durc.setText(actionCount.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return countList != null ? countList.size() : 0;
    }

    public class CountActionHodler extends RecyclerView.ViewHolder {

        TextView count_name;
        TextView count_time;
        TextView count_durc;

        public CountActionHodler(View itemView) {
            super(itemView);
            count_name = itemView.findViewById(R.id.count_name);
            count_time = itemView.findViewById(R.id.count_time);
            count_durc = itemView.findViewById(R.id.count_durc);
        }
    }
}
