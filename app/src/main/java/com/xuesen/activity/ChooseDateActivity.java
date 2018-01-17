package com.xuesen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.xuesen.R;
import com.xuesen.utils.ToastUtils;

import butterknife.BindView;

/**
 * 全部记录
 * 选择日期
 */
public class ChooseDateActivity extends BaseActivity {

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @Override
    public void setUpView(Bundle savedInstanceState) {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ToastUtils.showShort(year + "年" + month + 1 + "月" + dayOfMonth + "日 \n 开发中尽请期待...");
            }
        });
        calendarView.setMaxDate(System.currentTimeMillis());
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_choose_date;
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
