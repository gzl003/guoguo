package com.xuesen.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.xuesen.R;
import com.xuesen.adapter.HistCountAdapter;
import com.xuesen.db.ActionCountDao;
import com.xuesen.db.ActionCountDao.Properties;
import com.xuesen.db.DBManager;
import com.xuesen.helper.Parameter;
import com.xuesen.modle.ActionCount;
import com.xuesen.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 历史记录
 */
public class HistoryCountActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private String name;
    private String date;
    private List<ActionCount> countList;
    private HistCountAdapter histCountAdapter;

    @Override
    public void setUpView(Bundle savedInstanceState) {
        name = getIntent().getStringExtra(Parameter.INTENT_NAME);
        date = getIntent().getStringExtra(Parameter.INTENT_DATE);
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(date)) {
            getSupportActionBar().setSubtitle(name + "/" + date);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        histCountAdapter = new HistCountAdapter(this, countList);
        recyclerview.setAdapter(histCountAdapter);
        inquireDate();
    }

    /**
     * 查询
     */
    private void inquireDate() {
        //2018-01-17
        ActionCountDao actionCountDao = DBManager.getInstance(this).getReadableDaoSession().getActionCountDao();
        QueryBuilder<ActionCount> queryBuilder = actionCountDao.queryBuilder();
        queryBuilder.where(Properties.Date.eq(date), Properties.Name.eq(name));
        countList = queryBuilder.build().list();
        Collections.reverse(countList);
        histCountAdapter.setCountList(countList);
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_history_count;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
