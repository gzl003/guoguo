package com.xuesen.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.xuesen.R;
import com.xuesen.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/13.
 */

public class LoadingDialog extends BaseDialog {

    private CharSequence mMessage;

//    @BindView(R.id.dialog_message)
//    TextView mMessageTextView;

    public LoadingDialog(Context context) {
        super(context, R.style.Theme_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog_loading);
//
//        if (StringUtils.isEmpty((String) mMessage)) {
//            mMessage = getContext().getResources().getString(R.string.dialog_loading_message);
//        }

        setupViews();
    }

    @Override
    protected void setupViews() {
        super.setupViews();

//        mMessageTextView.setText(mMessage);
    }

    /**
     * Set the message displayed in the Dialog.
     */
    public void setMessage(CharSequence message) {
        mMessage = message;
    }

    /**
     * Set the message using the given resource id.
     */
    public void setMessage(int messageId) {
        mMessage = getContext().getResources().getText(messageId);
    }
}
