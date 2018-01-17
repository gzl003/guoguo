package com.xuesen.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static android.content.Context.ACTIVITY_SERVICE;

public final class PackageUtil {

    /**
     * File name in an APK for the Android manifest.
     */
    private static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";

    private PackageUtil() {
    }

    /**
     * Get version code for this application.
     *
     * @param context The context to use.  Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @return the version code or -1 if package not found
     */
    public static int getVersionCode(Context context) {
        int versionCode;
        try {
            versionCode = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            versionCode = -1;
        }

        return versionCode;
    }

    /**
     * Get version code for the application package name.
     *
     * @param context     The context to use.  Usually your {@link android.app.Application}
     *                    or {@link android.app.Activity} object.
     * @param packageName application package name
     * @return the version code or -1 if package not found
     */
    public static int getVersionCode(Context context, String packageName) {
        int versionCode;
        try {
            versionCode = context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (Exception e) {
            versionCode = -1;
        }

        return versionCode;
    }

    /**
     * Get version name for this application.
     *
     * @param context The context to use.  Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @return the version name or empty string if package not found
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            return versionName;
        }
        return versionName;
    }

    public static String getVersionName(Context context, String packageName) {
        String versionName = null;
        try {
            versionName = context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            versionName = StringUtils.EMPTY_STRING;
        }

        return versionName;
    }

    public static String getInstalledPackageApkFilePath(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            android.content.pm.PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi.applicationInfo.sourceDir;
        } catch (Exception e) {
            // Ignore the exception
        }
        return null;
    }

    public static String getSignatures(String apkFilePath) throws CertificateEncodingException {
        String signatures = null;

        byte[] readBuffer = new byte[8192];

        try {
            JarFile jarFile = new JarFile(apkFilePath);

            Certificate[] certs = null;

            // we'll just use the AndroidManifest.xml
            // to retrieve its signatures, not validating all of the
            // files.
            JarEntry jarEntry = jarFile.getJarEntry(ANDROID_MANIFEST_FILENAME);
            certs = loadCertificates(jarFile, jarEntry, readBuffer);
            if (certs == null) {
//                KLog.e("File " + apkFilePath
//                        + " has no certificates at entry "
//                        + jarEntry.getName() + "; ignoring!");
                jarFile.close();
                return null;
            }

//            KLog.d("File " + apkFilePath + ": entry=" + jarEntry
//                    + " certs=" + certs.length);

            StringBuffer buffer = new StringBuffer();
            final int N = certs.length;
            for (int i = 0; i < N; i++) {
//                KLog.d("Public key: "
//                        + certs[i].getPublicKey().getEncoded() + " "
//                        + certs[i].getPublicKey());
                //buffer.append(MD5.md5Hex(certs[i].getPublicKey().getEncoded()));
                buffer.append(MD5.md5Hex(certs[i].getEncoded()));
            }
            signatures = buffer.toString();
            jarFile.close();
        } catch (IOException e) {
            Log.e("Exception reading " + apkFilePath, e.toString());
        }

        return signatures;
    }

    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je,
                                                  byte[] readBuffer) {
        try {
            // We must read the stream for the JarEntry to retrieve
            // its certificates.
            InputStream is = new BufferedInputStream(jarFile.getInputStream(je));
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
                // not using
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException e) {
            Log.e("Exception reading " + je.getName() + " in "
                    + jarFile.getName(), e.toString());
        } catch (RuntimeException e) {
            Log.e("Exception reading " + je.getName() + " in "
                    + jarFile.getName(), e.toString());
        }
        return null;
    }

    private static String mChannel;

    public static String getChannel(Context context) {
        if (mChannel == null) {
            mChannel = getMetaData(context, "AB_CHANNEL");
        }

        return mChannel;
    }

    /**
     * 读取AndroidManifest文件中设置的meta-data值
     *
     * @param context
     * @param keyName
     * @return
     */
    public static String getMetaData(Context context, String keyName) {
        try {
            ApplicationInfo appi = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            Bundle bundle = appi.metaData;
            Object value = bundle.get(keyName);

            return (String) value;
        } catch (Exception ex) {
            // Si el meta-data no existe retorno null
        }
        return null;
    }

    /**
     * 判断某个应用是否已经安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return true 安装了  false 是未安装
     */
    public static boolean isPkgInstalled(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        android.content.pm.ApplicationInfo info = null;
        try {
            info = context.getPackageManager().getApplicationInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 检查是否安装了支付宝
     *
     * @param context
     * @return
     */
    public static boolean checkAliPayInstalled(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    /**
     * 启动到应用商店app详情界面
     * <p>
     * com.android.vending     Google Play
     * com.tencent.android.qqdownloader    应用宝
     * com.qihoo.appstore   360手机助手
     * com.baidu.appsearch 百度手机助
     * com.xiaomi.market  小米应用商店
     * com.wandoujia.phoenix2 豌豆荚
     * com.huawei.appmarket  华为应用市场
     * com.taobao.appcenter  淘宝手机助手
     * com.hiapk.marketpho 安卓市场
     * cn.goapk.market 安智市场
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void openAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (StringUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!StringUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开某个App
     *
     * @param context 上下文
     * @param appPkg  跳转应用的包名
     */
    public static void openApp(Context context, String appPkg, String page) {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(appPkg, page);//com.tencent.mm.ui.LauncherUI   com.sina.weibo.MainTabActivity
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        context.startActivity(intent);
    }

    /**
     * 判断某一个类是否存在任务栈里面
     *
     * @return true false
     */
    public static boolean isExsitMianActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) {
            // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    /**
     * 进行逻辑处理
     */
    public static void dealWithIntent(Context context, Class<?> cls) {
        if (isExsitMianActivity(context, cls)) {//存在这个类
        } else {//不存在这个类
            openApp(context, "com.huanxi.video", "com.huanxi.video.ui.activity.MainActivity");
        }
    }
}
