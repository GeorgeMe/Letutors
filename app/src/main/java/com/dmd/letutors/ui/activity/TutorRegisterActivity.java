package com.dmd.letutors.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;

import butterknife.Bind;

public class TutorRegisterActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.bar_register_back)
    TextView barRegisterBack;
    @Bind(R.id.et_username)
    AppCompatEditText etUsername;
    @Bind(R.id.et_password)
    AppCompatEditText etPassword;
    @Bind(R.id.et_identifying_code)
    AppCompatEditText etIdentifyingCode;
    @Bind(R.id.tv_identifying_code)
    TextView tvIdentifyingCode;
    @Bind(R.id.btn_register)
    Button btnRegister;


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_register;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        barRegisterBack.setOnClickListener(this);
        tvIdentifyingCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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
        return false;
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
    public void onClick(View v) {
        if (v==barRegisterBack){
            finish();
        }else if (v==tvIdentifyingCode){
            etIdentifyingCode.setError("测试用1234");
        }else if (v==btnRegister){

        }
    }
}
