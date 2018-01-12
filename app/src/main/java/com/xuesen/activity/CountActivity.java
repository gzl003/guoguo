package com.xuesen.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuesen.R;
import com.xuesen.helper.Parameter;
import com.xuesen.modle.Action;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 统计页面
 */
public class CountActivity extends BaseActivity {

    @BindView(R.id.count_btn)
    TextView count_btn;

    private Action action;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//是否显示左上角默认的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);//按钮是否可以点击（实测无用，false下
        name = getIntent().getStringExtra(Parameter.INTENT_NAME);
//        if(){
//
//        }
        count_btn.setText(name);
    }

    @OnClick(R.id.count_btn)
    public void onCountClisk(View View) {
        action = new Action();
        Toast.makeText(CountActivity.this,"添加一次",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
