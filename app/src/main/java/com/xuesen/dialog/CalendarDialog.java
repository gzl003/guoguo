package com.xuesen.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.CalendarView;

import com.xuesen.R;
import com.xuesen.view.BaseDialog;

import butterknife.BindView;

/**
 *  * Created by 智光 on 2018/1/17 16:02
 *  选择日期
 */
public class CalendarDialog extends BaseDialog {

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    private OnSelectedDayLintener selectedDayLintener;

    public CalendarDialog(Context context, OnSelectedDayLintener selectedDayLintener) {
        super(context, R.style.Theme_AppCompat_Light_Dialog_MinWidth);
        this.selectedDayLintener = selectedDayLintener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_dialog_layout);
        setupViews();
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String smonth = "";
                String sdayOfMonth = "";
                if (selectedDayLintener != null) {
                    if (month < 9) {
                        smonth = "0" + (month + 1);
                    } else {
                        smonth = "" + (month + 1);
                    }
                    if (dayOfMonth < 10) {
                        sdayOfMonth = "0" + dayOfMonth;
                    } else {
                        sdayOfMonth = "" + dayOfMonth;
                    }
                    selectedDayLintener.onSelectedDay(year, smonth, sdayOfMonth);
                    dismiss();
                }
            }
        });
        calendarView.setMaxDate(System.currentTimeMillis());
    }

    public interface OnSelectedDayLintener {
        void onSelectedDay(int year, String month, String dayOfMonth);
    }
}
