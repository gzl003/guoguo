package com.xuesen.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xuesen.R;
import com.xuesen.modle.Action;
import com.xuesen.utils.StringUtils;
import com.xuesen.view.BaseDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/13.
 */
public class ActionDialog extends BaseDialog {
    @BindView(R.id.edit_name)
    EditText edit_name;
    @BindView(R.id.edit_ok)
    Button edit_ok;
    private Context mContext;
    private EditokListener editokListener;

    public ActionDialog(Context context) {
        super(context, R.style.Theme_AppCompat_Dialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_dilog_layout);
        setupViews();
    }

    @Override
    protected void setupViews() {
        super.setupViews();

    }

    @OnClick(R.id.edit_ok)
    public void okOnclick(View view) {
        String name = edit_name.getText().toString().trim();
        if (StringUtils.isNotEmpty(name) && editokListener != null) {
            Action action = new Action();
            action.setName(name);
            editokListener.onOKcLick(action);
            dismiss();
        } else {
            Toast.makeText(mContext, "请输入统计内容", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void show() {
        super.show();
        edit_name.setText("");
    }

    public void setEditokListener(EditokListener editokListener) {
        this.editokListener = editokListener;
    }

    public interface EditokListener {
        void onOKcLick(Action action);
    }
}
