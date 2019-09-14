package com.xuesen.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tencent.bugly.beta.Beta;
import com.xuesen.R;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @Override
    public void setUpView(Bundle savedInstanceState) {

    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.checkUpdate_btn)
    public void onUpdateClick(View view) {
        /***** 检查更新 *****/
        Beta.checkUpgrade();
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
