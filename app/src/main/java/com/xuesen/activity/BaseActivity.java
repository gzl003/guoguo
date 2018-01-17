package com.xuesen.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.xuesen.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  * Created by 智光 on 2018/1/11 16:44
 *  
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());
        mUnbinder = ButterKnife.bind(this);
        /*设置左上角Logo Icon*/
//        actionBar.setLogo(R.drawable.ic_add);//(单独设置没作用)
//        actionBar.setDisplayShowHomeEnabled(true);//是否显示Logo,必须为他setLogo()才起作用
//        actionBar.setDisplayUseLogoEnabled(true);//是否使用Activity的Logo，即setLogo()方法设置的Logo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//是否显示左上角默认的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);//按钮是否可以点击（实测无用，false下依然可以点击--已经设置了该Activity的父级Activity）
        setUpView(savedInstanceState);
    }

    /**
     * 初始化操作
     *
     * @param savedInstanceState
     */
    public abstract void setUpView(Bundle savedInstanceState);

    /**
     * @return 获取布局文件
     */
    public abstract int getContentViewID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    //actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
