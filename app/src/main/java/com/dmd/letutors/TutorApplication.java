package com.dmd.letutors;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.aop.AdviceBinder;
import com.alibaba.mobileim.aop.PointCutEnum;
import com.dmd.baidulbs.LocationManager;
import com.dmd.letutors.dao.DaoMaster;
import com.dmd.letutors.dao.DaoSession;
import com.dmd.letutors.im.uicustom.ConversationListUICustom;
import com.dmd.letutors.utils.VolleyHelper;
import com.dmd.tutor.base.BaseAppManager;
import com.umeng.openim.OpenIMAgent;

/**
 * Created by George on 2015/12/6.
 */
public class TutorApplication extends Application {

    private YWIMKit mIMKit;

    public SQLiteDatabase db;
    public DaoMaster daoMaster;
    public DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        //设备码
        String device_id = getDeviceId(getApplicationContext());

        SharedPreferences shared;
        SharedPreferences.Editor editor;

        shared = this.getSharedPreferences("user_info", 0);
        editor = shared.edit();
        editor.putString("device_uuid", device_id);
        editor.commit();

        //定位信息
        LocationManager locationManager = new LocationManager(this);
        locationManager.refreshLocation();
        VolleyHelper.getInstance().init(this);

        //会话列表UI相关
        AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_UI_POINTCUT, ConversationListUICustom.class);
        OpenIMAgent im = OpenIMAgent.getInstance(this);
        im.init();
        mIMKit = YWAPI.getIMKitInstance();

        initGreenDAO();
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    public void exitApp() {
        BaseAppManager.getInstance().clear();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public YWIMKit getIMKit(){
        return mIMKit;
    }

    private void initGreenDAO(){
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "tutor_db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    //获取缓存的userId
    public int getCacheUserId() {
        SharedPreferences shared;
        SharedPreferences.Editor editor;

        shared = this.getSharedPreferences("user_info", 0);
        editor = shared.edit();
        int userId = shared.getInt("uid", 0);
        return userId;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        return deviceId;
    }
}
