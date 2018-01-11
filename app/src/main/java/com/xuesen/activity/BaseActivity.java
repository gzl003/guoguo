package com.xuesen.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 *  * Created by 智光 on 2018/1/11 16:44
 *  
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*设置左上角Logo Icon*/
//        actionBar.setLogo(R.drawable.ic_add);//(单独设置没作用)
//        actionBar.setDisplayShowHomeEnabled(true);//是否显示Logo,必须为他setLogo()才起作用
//        actionBar.setDisplayUseLogoEnabled(true);//是否使用Activity的Logo，即setLogo()方法设置的Logo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//是否显示左上角默认的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);//按钮是否可以点击（实测无用，false下依然可以点击--已经设置了该Activity的父级Activity）

    }


}
