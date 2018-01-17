package com.xuesen.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.xuesen.GuoApplication;

/**
 * Created by Administrator on 2018/1/13.
 */
public class ToastUtils {
    // Toast
    private static Toast toast;

    /**
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(GuoApplication.getInstance(), message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * @param message
     */
    public static void showShort(int message) {
        if (null == toast) {
            toast = Toast.makeText(GuoApplication.getInstance(), message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(GuoApplication.getInstance(), message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * @param message
     */
    public static void showLong(int message) {
        if (null == toast) {
            toast = Toast.makeText(GuoApplication.getInstance(), message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(GuoApplication.getInstance(), message, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(GuoApplication.getInstance(), message, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
