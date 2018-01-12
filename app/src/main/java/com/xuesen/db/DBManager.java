package com.xuesen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xuesen.modle.Action;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 *  Created by 智光 on 2018/1/12 10:38
 *  这里声明一个数据库管理者单例
 */
public class DBManager {
    private final static String dbName = "guo_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 获取可写的DaoSession
     * 插入对象
     * UserDao userDao = daoSession.getUserDao();
     * userDao.insert(user);
     * 插入集合
     * UserDao userDao = daoSession.getUserDao();
     * userDao.insertInTx(users);
     * 删除对象
     * UserDao userDao = daoSession.getUserDao();
     * userDao.delete(user);
     * 更新一条
     * UserDao userDao = daoSession.getUserDao();
     * userDao.update(user);
     *
     * @return DaoSession 用于更新 插入 修改
     */
    public DaoSession getWritableDaoSession() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        return daoMaster.newSession();
    }

    /**
     * 获取可读的DaoSession
     *
     * @return DaoSession 用于查询
     */
    public DaoSession getReadableDaoSession() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        return daoMaster.newSession();
    }


    /**
     * 查询用户列表
     */
    public List<Action> queryActionList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ActionDao userDao = daoSession.getActionDao();
        QueryBuilder<Action> qb = userDao.queryBuilder();
        return qb.list();
    }
//
//    /**
//     * 查询用户列表
//     */
//    public List<User> queryUserList(int age) {
//        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
//        DaoSession daoSession = daoMaster.newSession();
//        UserDao userDao = daoSession.getUserDao();
//        QueryBuilder<User> qb = userDao.queryBuilder();
//        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
//        List<User> list = qb.list();
//        return list;
//    }
}
