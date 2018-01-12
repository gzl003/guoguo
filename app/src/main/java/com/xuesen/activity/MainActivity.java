package com.xuesen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xuesen.R;
import com.xuesen.adapter.ActionListAdapter;
import com.xuesen.db.ActionDao;
import com.xuesen.db.DBManager;
import com.xuesen.modle.Action;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * guoguo0302
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<Action> actions = new ArrayList<>();
    private ActionListAdapter actionadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpView() {
        actionadapter = new ActionListAdapter(actions, getApplication());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(actionadapter);

        update();
    }

    @OnClick(R.id.add_btn)
    public void OnTextaddClick(View view) {

        Action action = new Action();
        action.setName("哈哈哈");
        DBManager.getInstance(MainActivity.this).getWritableDaoSession().getActionDao().insert(action);
        update();
    }

    @OnClick(R.id.remove_btn)
    public void OnTextremoveClick(View view) {
        if (view.getTag() == null) {
            view.setTag(true);
        }
        if ((boolean) view.getTag()) {
            view.setTag(false);
        } else {
            view.setTag(true);
        }
        actionadapter.setEdit((boolean) view.getTag());

    }

    private void update() {
        ActionDao actionDao = DBManager.getInstance(MainActivity.this).getReadableDaoSession().getActionDao();
        QueryBuilder<Action> actionQueryBuilder = actionDao.queryBuilder();
//        actionQueryBuilder.where(ActionDao.Properties.Name.gt("")).orderAsc(ActionDao.Properties.Name);
//        List<Action> list = actionQueryBuilder.list();
        actions = actionQueryBuilder.build().list();
        actionadapter.setActions(actions);
    }
}
