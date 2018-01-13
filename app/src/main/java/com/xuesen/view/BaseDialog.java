package com.xuesen.view;

import android.app.Dialog;
import android.content.Context;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/13.
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseDialog(Context context, boolean cancelable,
                         OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected void setupViews() {
        ButterKnife.bind(this);
    }

}