package com.xuesen.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuesen.R;
import com.xuesen.adapter.HistCountAdapter;
import com.xuesen.db.ActionCountDao;
import com.xuesen.db.DBManager;
import com.xuesen.helper.Parameter;
import com.xuesen.modle.Action;
import com.xuesen.modle.ActionCount;
import com.xuesen.utils.StringUtils;
import com.xuesen.utils.TimeUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 统计页面
 */
public class CountActivity extends BaseActivity {

    @BindView(R.id.count_btn)
    TextView count_btn;

    @BindView(R.id.hist_action)
    RecyclerView hist_action;

    private ActionCount action;
    private List<ActionCount> actionCounts = new ArrayList<>();
    private String name;
    private String date;
    private HistCountAdapter histCountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        ButterKnife.bind(this);

        name = getIntent().getStringExtra(Parameter.INTENT_NAME);
        date = getIntent().getStringExtra(Parameter.INTENT_DATE);
        if (StringUtils.isNotEmpty(name)) {
            action = new ActionCount();
            action.setName(name);
            action.setDate(TimeUtils.getCurrentTime());
            refashBtn();
        }
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setSubtitle(date);
        setUpView();
    }

    private void setUpView() {
        hist_action.setLayoutManager(new LinearLayoutManager(this));
        histCountAdapter = new HistCountAdapter(this, actionCounts);
        hist_action.setAdapter(histCountAdapter);

    }

    @OnClick(R.id.count_btn)
    public void onCountClisk(View View) {
        ActionCount actionCount = new ActionCount();
        actionCount.setName(name);
        actionCount.setDate(TimeUtils.getCurrentDate());
        actionCount.setStartime(TimeUtils.getCurrentTime());
        actionCount.setDescription(TimeUtils.getCurrentTime() + ":(" + name + ")+1");
        ActionCountDao countDao = DBManager.getInstance(this).getWritableDaoSession().getActionCountDao();
        countDao.insert(actionCount);
        refashBtn();
    }


    private void refashBtn() {
        ActionCountDao countDao = DBManager.getInstance(this).getReadableDaoSession().getActionCountDao();
        QueryBuilder<ActionCount> queryBuilder = countDao.queryBuilder();
        actionCounts = queryBuilder.where(ActionCountDao.Properties.Name.eq(action.getName())).build().list();
        if (actionCounts != null) {
            count_btn.setText(name + "\n" + actionCounts.size());

        } else {
            count_btn.setText(name + "\n" + 0);
        }
        if (histCountAdapter != null) {
            histCountAdapter.setCountList(actionCounts);
        }
    }


    //actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.layout.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //当用户点击Action按钮的时候，系统会调用Activity的onOptionsItemSelected()方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
