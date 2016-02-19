package com.dmd.letutors.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.IYWConversationUnreadChangeListener;
import com.alibaba.mobileim.conversation.YWConversation;
import com.dmd.letutors.R;
import com.dmd.letutors.presenter.impl.MainViewImpl;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.letutors.ui.fragment.TutorHomeFragment;
import com.dmd.letutors.ui.fragment.TutorMessageFragment;
import com.dmd.letutors.ui.fragment.TutorMineFragment;
import com.dmd.letutors.ui.fragment.TutorSeekFragment;
import com.dmd.letutors.view.MainView;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.utils.TLog;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */

public class TutorMainActivity extends BaseActivity implements MainView,TabHost.OnTabChangeListener {

    @Bind(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private static long DOUBLE_CLICK_TIME = 0L;
    public static final String LOGIN_SUCCESS = "loginSuccess";

    public static final String TAB_HOME = "home";
    public static final String TAB_MESSAGE = "message";
    public static final String TAB_SEEK = "seek";
    public static final String TAB_MINE = "mine";

    private TextView mHomeTab;
    private TextView mMessageTab;
    private TextView mSeekTab;
    private TextView mMineTab;
    private TextView mUnread;


    private Drawable mHomePressed;
    private Drawable mHomeNormal;
    private Drawable mMessagePressed;
    private Drawable mMessageNormal;

    private Drawable mSeekPressed;
    private Drawable mSeekNormal;
    private Drawable mMinePressed;
    private Drawable mMineNormal;

    private IYWConversationService mConversationService;
    private IYWConversationUnreadChangeListener mConversationUnreadChangeListener;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private MainViewImpl mainView;


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_main;
    }

    @Subscribe
    @Override
    protected void onEventComming(EventCenter eventCenter) {
        if (eventCenter.getEventCode()==1){
            tabhost.onTabChanged(TAB_SEEK);
            tabhost.setCurrentTabByTag(TAB_SEEK);
        }else  if (eventCenter.getEventCode()==2){
            tabhost.onTabChanged(TAB_HOME);
            tabhost.setCurrentTabByTag(TAB_HOME);
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(this,R.id.realtabcontent);
    }

    @Override
    protected void initViewsAndEvents() {
        mainView=new MainViewImpl(this,this);
        mainView.initialized();

        mConversationService = getBaseApplication().getIMKit().getConversationService();
        initConversationServiceAndListener();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    public void initTabView() {
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);

        View indicator = getIndicatorView(TAB_HOME);
        tabhost.addTab(tabhost.newTabSpec(TAB_HOME).setIndicator(indicator),TutorHomeFragment.class, null);

        indicator = getIndicatorView(TAB_MESSAGE);
        tabhost.addTab(tabhost.newTabSpec(TAB_MESSAGE).setIndicator(indicator), TutorMessageFragment.class, null);

        indicator = getIndicatorView(TAB_SEEK);
        tabhost.addTab(tabhost.newTabSpec(TAB_SEEK).setIndicator(indicator), TutorSeekFragment.class, null);

        indicator = getIndicatorView(TAB_MINE);
        tabhost.addTab(tabhost.newTabSpec(TAB_MINE).setIndicator(indicator), TutorMineFragment.class, null);

        mUnread = (TextView) findViewById(R.id.unread);

        tabhost.setOnTabChangedListener(this);
        this.onTabChanged(TAB_HOME);

    }

    @Override
    public View getIndicatorView(String tab) {
        View tabView = View.inflate(this, R.layout.tutor_tab_item, null);
        TextView indicator = (TextView) tabView.findViewById(R.id.tab_text);
        Drawable drawable;

        if (tab.equals(TAB_HOME)) {
            indicator.setText("主页");
            drawable = getResources().getDrawable(R.drawable.nav_home_normal);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            indicator.setCompoundDrawables(null, drawable, null, null);
            mHomeTab = indicator;
        } else if (tab.equals(TAB_MESSAGE)) {
            indicator.setText("消息");
            drawable = getResources().getDrawable(R.drawable.nav_message_normal);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            indicator.setCompoundDrawables(null, drawable, null, null);
            mMessageTab = indicator;
        } else if (tab.equals(TAB_SEEK)) {
            indicator.setText("找老师");
            drawable = getResources().getDrawable(R.drawable.nav_seek_normal);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            indicator.setCompoundDrawables(null, drawable, null, null);
            mSeekTab = indicator;
        } else if (tab.equals(TAB_MINE)) {
            indicator.setText("我的");
            drawable = getResources().getDrawable(R.drawable.nav_message_normal);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            indicator.setCompoundDrawables(null, drawable, null, null);
            mMineTab = indicator;
        }
        return tabView;
    }

    @Override
    public void setHomeText(boolean isSelected) {
        Drawable drawable = null;
        if (isSelected) {
            mHomeTab.setTextColor(getResources().getColor(R.color.tab_pressed_color));
            if (mHomePressed == null) {
                mHomePressed = getResources().getDrawable(R.drawable.nav_home_pressed);
            }
            drawable = mHomePressed;
        } else {
            mHomeTab.setTextColor(getResources().getColor(R.color.tab_normal_color));
            if (mHomeNormal == null) {
                mHomeNormal = getResources().getDrawable(R.drawable.nav_home_normal);
            }
            drawable = mHomeNormal;
        }
        if (null != drawable) {// 此处出现过NP问题，加保护
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            mHomeTab.setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    public void setMessageText(boolean isSelected) {
        Drawable drawable = null;
        if (isSelected) {
            mMessageTab.setTextColor(getResources().getColor(R.color.tab_pressed_color));
            if (mMessagePressed == null) {
                mMessagePressed = getResources().getDrawable(R.drawable.nav_message_pressed);
            }
            drawable = mMessagePressed;
        } else {
            mMessageTab.setTextColor(getResources().getColor(R.color.tab_normal_color));
            if (mMessageNormal == null) {
                mMessageNormal = getResources().getDrawable(R.drawable.nav_message_normal);
            }
            drawable = mMessageNormal;
        }
        if (null != drawable) {// 此处出现过NP问题，加保护
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            mMessageTab.setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    public void setSeekText(boolean isSelected) {
        Drawable drawable = null;
        if (isSelected) {
            mSeekTab.setTextColor(getResources().getColor(R.color.tab_pressed_color));
            if (mSeekPressed == null) {
                mSeekPressed = getResources().getDrawable(R.drawable.nav_seek_pressed);
            }
            drawable = mSeekPressed;
        } else {
            mSeekTab.setTextColor(getResources().getColor(R.color.tab_normal_color));
            if (mSeekNormal == null) {
                mSeekNormal = getResources().getDrawable(R.drawable.nav_seek_normal);
            }
            drawable = mSeekNormal;
        }
        if (null != drawable) {// 此处出现过NP问题，加保护
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            mSeekTab.setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    public void setMineText(boolean isSelected) {
        Drawable drawable = null;
        if (isSelected) {
            mMineTab.setTextColor(getResources().getColor(R.color.tab_pressed_color));
            if (mMinePressed == null) {
                mMinePressed = getResources().getDrawable(R.drawable.nav_message_pressed);
            }
            drawable = mMinePressed;
        } else {
            mMineTab.setTextColor(getResources().getColor(R.color.tab_normal_color));
            if (mMineNormal == null) {
                mMineNormal = getResources().getDrawable(R.drawable.nav_message_normal);
            }
            drawable = mMineNormal;
        }
        if (null != drawable) {// 此处出现过NP问题，加保护
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            mMineTab.setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        if (TAB_HOME.equals(tabId)) {
            setHomeText(true);
            setMessageText(false);
            setSeekText(false);
            setMineText(false);
            TLog.e(TAG_LOG,"TAB_HOME");
        }else if (TAB_MESSAGE.equals(tabId)) {
            setHomeText(false);
            setMessageText(true);
            setSeekText(false);
            setMineText(false);
        }else if (TAB_SEEK.equals(tabId)) {
            setHomeText(false);
            setMessageText(false);
            setSeekText(true);
            setMineText(false);
        }else if (TAB_MINE.equals(tabId)) {
            setHomeText(false);
            setMessageText(false);
            setSeekText(false);
            setMineText(true);
        }
    }

    private void initConversationServiceAndListener() {
        mConversationUnreadChangeListener = new IYWConversationUnreadChangeListener() {

            @Override
            public void onUnreadChange() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mConversationService = getBaseApplication().getIMKit().getConversationService();
                        int unReadCount = mConversationService.getAllUnreadCount();
                        List<YWConversation> k= mConversationService.getConversationList();
                        if (unReadCount > 0) {
                            mUnread.setVisibility(View.VISIBLE);
                            if (unReadCount < 100) {
                                mUnread.setText(unReadCount + "");
                            } else {
                                mUnread.setText("99+");
                            }
                        } else {
                            mUnread.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        mConversationService = getBaseApplication().getIMKit().getConversationService();

        //resume时需要检查全局未读消息数并做处理，因为离开此界面时删除了全局消息监听器
        mConversationUnreadChangeListener.onUnreadChange();

        //在Tab栏增加会话未读消息变化的全局监听器
        mConversationService.addTotalUnreadChangeListener(mConversationUnreadChangeListener);
        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra(LOGIN_SUCCESS) != null){
            this.onTabChanged(TAB_MESSAGE);
            getIntent().removeExtra(LOGIN_SUCCESS);
        }
        YWLog.i(TAG_LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在Tab栏删除会话未读消息变化的全局监听器
        mConversationService.removeTotalUnreadChangeListener(mConversationUnreadChangeListener);
        YWLog.i(TAG_LOG, "onPause");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                showToast(getString(R.string.double_click_exit));
                DOUBLE_CLICK_TIME = System.currentTimeMillis();
            } else {
                getBaseApplication().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
