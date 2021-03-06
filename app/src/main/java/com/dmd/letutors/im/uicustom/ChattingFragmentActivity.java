package com.dmd.letutors.im.uicustom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.alibaba.mobileim.channel.util.YWLog;
import com.dmd.letutors.R;
import com.dmd.letutors.TutorApplication;
import com.dmd.tutor.utils.TLog;

public class ChattingFragmentActivity  extends FragmentActivity {

    private static final String TAG = "ChattingActivity";
    public static final String TARGET_ID = "targetId";

    private Fragment mCurrentFrontFragment;
    private TutorApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_fragment);
        application=(TutorApplication) getApplication();
        createFragment();
        YWLog.i(TAG, "onCreate");
    }

    private void createFragment(){
        Intent intent = getIntent();
        String targetId = intent.getStringExtra(TARGET_ID);
        mCurrentFrontFragment = application.getIMKit().getChattingFragment(targetId);
        if (targetId==null){
            TLog.e("targetId","----null");
        }
        if (mCurrentFrontFragment==null){
            TLog.e("mCurrentFrontFragment","----null");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.wx_chat_container, mCurrentFrontFragment).commit();
    }


    /**
     * 必须实现该方法，且该方法的实现必须跟以下示例代码完全一致！
     * 因为拍照和选择照片的时候会回调该方法，如果没有按以下方式覆写该方法会导致拍照和选择照片时应用crash
     * @param arg0
     * @param arg1
     * @param arg2
     */
    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (mCurrentFrontFragment != null) {
            mCurrentFrontFragment.onActivityResult(arg0, arg1, arg2);
        }
    }
}
