package com.xuesen.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xuesen.R;

public class SettingActivity extends BaseActivity {

    @Override
    public void setUpView(Bundle savedInstanceState) {

    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_setting;
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
