package com.xuesen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xuesen.R;
import com.xuesen.adapter.CountAdapter;
import com.xuesen.db.ActionCountDao;
import com.xuesen.db.DBManager;
import com.xuesen.dialog.CalendarDialog;
import com.xuesen.helper.Parameter;
import com.xuesen.modle.ActionCount;
import com.xuesen.utils.StringUtils;
import com.xuesen.utils.TimeUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 统计页面
 */
public class CountActivity extends BaseActivity implements CalendarDialog.OnSelectedDayLintener {

    @BindView(R.id.count_btn)
    TextView count_btn;

    @BindView(R.id.hist_action)
    RecyclerView hist_action;

    private ActionCount action;
    private List<ActionCount> actionCounts = new ArrayList<>();
    private String name;
    private String date;
    private CountAdapter countAdapter;
    private CalendarDialog calendarDialog;

    @Override
    public void setUpView(Bundle savedInstanceState) {
        name = getIntent().getStringExtra(Parameter.INTENT_NAME);
        date = TimeUtils.getCurrentDate();
        if (StringUtils.isNotEmpty(name)) {
            action = new ActionCount();
            action.setName(name);
            action.setDate(TimeUtils.getCurrentTime());
            refashBtn();
        }
        getSupportActionBar().setTitle(name + " - 记录");
        getSupportActionBar().setSubtitle(date);
        setUpView();
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_count;
    }

    private void setUpView() {
        hist_action.setLayoutManager(new LinearLayoutManager(this));
        countAdapter = new CountAdapter(this, actionCounts);
        hist_action.setAdapter(countAdapter);
        calendarDialog = new CalendarDialog(this, this);
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
        if (countAdapter != null) {
            countAdapter.setCountList(actionCounts);
        }
    }


    //actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_count, menu);
        return true;
    }

    //当用户点击Action按钮的时候，系统会调用Activity的onOptionsItemSelected()方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.count_history:
                if (calendarDialog != null) {
                    calendarDialog.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 选择日期的回调
     *
     * @param year       年
     * @param month      月
     * @param dayOfMonth 日
     */
    @Override
    public void onSelectedDay(int year, String month, String dayOfMonth) {
        Intent intent = new Intent(CountActivity.this, HistoryCountActivity.class);
        intent.putExtra(Parameter.INTENT_DATE, year + "-" + month + "-" + dayOfMonth);
        intent.putExtra(Parameter.INTENT_NAME, name);
        startActivity(intent);
    }
}
