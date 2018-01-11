package com.xuesen.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xuesen.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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
//            case R.id.user_p:
//                Toast.makeText(this, "你点击了“用户”按键！", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.write_p:
//                Toast.makeText(this, "你点击了“发布”按键！", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.favo_p:
//                Toast.makeText(this, "你点击了“收藏”按键！", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
