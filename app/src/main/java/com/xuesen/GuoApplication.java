package com.xuesen;

import android.app.Application;
import android.content.res.Resources;
import android.os.Environment;

import com.facebook.stetho.Stetho;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.xuesen.activity.MainActivity;
import com.xuesen.modle.Action;
import com.xuesen.utils.PackageUtil;

import java.util.ArrayList;
import java.util.List;

import static com.xuesen.helper.Parameter.BUGLY_ID;

/**
 *  * Created by 智光 on 2018/1/17 14:50
 *  
 */
public class GuoApplication extends Application {

    private static GuoApplication instence;
    private List<Action> list = new ArrayList<>();

    public static GuoApplication getInstance() {
        return instence;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instence = this;
        chromeDebuging();
        initBugly();
        createSkill();
    }

    private void createSkill() {
        Resources res = getResources();
        String[] list_skill = res.getStringArray(R.array.list_skill);
        for (int i = 0; i < list_skill.length; i++) {
            Action action = new Action();
            action.setName(list_skill[i]);
            list.add(action);
        }
    }

    /**
     * 获取天生的技能
     *
     * @return
     */
    public List<Action> getSkills() {
        return list;
    }

    private void initBugly() {
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;
        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = false;
        /**
         * 开启关闭热更新能力
         * true 开启 false关闭
         */
        Beta.enableHotfix = false;
        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpdate(),如需增加自动检查时机可以使用Beta.checkUpdate(false,false);
         * 参数1： applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */
        Bugly.init(getApplicationContext(), BUGLY_ID, BuildConfig.DEBUG);
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppVersion(PackageUtil.getVersionName(this));      //App的版本
        CrashReport.setUserId("guoguo");
    }

    private void chromeDebuging() {
        //https://github.com/facebook/stetho
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
