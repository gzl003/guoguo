package com.xuesen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.xuesen.GuoApplication;
import com.xuesen.R;
import com.xuesen.adapter.ActionListAdapter;
import com.xuesen.db.ActionDao;
import com.xuesen.db.DBManager;
import com.xuesen.dialog.ActionDialog;
import com.xuesen.modle.Action;
import com.xuesen.utils.TimeUtils;
import com.xuesen.utils.ToastUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.remove_btn)
    Button remove;

    private List<Action> actions = new ArrayList<>();
    private ActionListAdapter actionadapter;
    private ActionDialog actionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle(TimeUtils.getCurrentDate());
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_addbaby) {
            ToastUtils.showShort("开发者尽请期待...");
            return true;
        }

        if (id == R.id.action_allhistory) {
            Intent intent = new Intent(MainActivity.this, ChooseDateActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpView() {
        actions.addAll(GuoApplication.getInstance().getSkills());
        actionadapter = new ActionListAdapter(actions, getApplication());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(actionadapter);
        actionDialog = new ActionDialog(this);
        actionDialog.setEditokListener(new ActionDialog.EditokListener() {
            @Override
            public void onOKcLick(Action action) {
                ActionDao actionDao = DBManager.getInstance(MainActivity.this).getReadableDaoSession().getActionDao();
                QueryBuilder<Action> actionQueryBuilder = actionDao.queryBuilder();
                List<Action> actionList = actionQueryBuilder.list();
                if (actionList.contains(action)) {
                    ToastUtils.showShort(action.getName() + "事件已经存在了");
                    return;
                }
                if (actions.size() > 11) {
                    ToastUtils.showShort("事件已经满了");
                    return;
                }
                DBManager.getInstance(MainActivity.this).getWritableDaoSession().getActionDao().insert(action);
                update();
            }
        });
        update();
    }

    @OnClick(R.id.add_btn)
    public void OnTextaddClick(View view) {
        actionDialog.show();
    }

    @OnClick(R.id.remove_btn)
    public void OnTextremoveClick(View view) {
        if (view.getTag() == null) {
            view.setTag(true);
            actionadapter.setEdit(true);
            remove.setText("完成");
        } else {
            if ((boolean) view.getTag()) {
                view.setTag(false);
                remove.setText("删除");
            } else {
                view.setTag(true);
                remove.setText("完成");
            }
            actionadapter.setEdit((boolean) view.getTag());
        }
    }

    private void update() {
        actions.clear();
        ActionDao actionDao = DBManager.getInstance(MainActivity.this).getReadableDaoSession().getActionDao();
        QueryBuilder<Action> actionQueryBuilder = actionDao.queryBuilder();
        actions.addAll(GuoApplication.getInstance().getSkills());
        actions.addAll(actionQueryBuilder.build().list());
        actionadapter.setActions(actions);
    }
}
