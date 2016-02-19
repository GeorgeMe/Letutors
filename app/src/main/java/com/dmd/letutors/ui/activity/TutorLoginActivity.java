package com.dmd.letutors.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.mobileim.utility.IMPrefsTools;
import com.dmd.letutors.R;
import com.dmd.letutors.presenter.LoginPresenter;
import com.dmd.letutors.presenter.impl.LoginPresenterImpl;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.letutors.view.LoginView;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.utils.CommonUtils;

import butterknife.Bind;

public class TutorLoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @Bind(R.id.tv_login_back)
    TextView tvLoginBack;
    @Bind(R.id.tv_login_register)
    TextView tvLoginRegister;
    @Bind(R.id.et_username)
    AppCompatEditText etUsername;
    @Bind(R.id.et_password)
    AppCompatEditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;

    public static String AUTO_LOGIN_STATE_ACTION = "auto_login_state_action";
    private static final String USER_ID = "userId";
    private static final String PASSWORD = "password";


    private LoginPresenter presenter;

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_login;
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

        mShared =getSharedPreferences("user_info", 0);
        mEditor = mShared.edit();

        findViewById(R.id.tv_login_back).setOnClickListener(this);
        findViewById(R.id.tv_login_register).setOnClickListener(this);

        if (!CommonUtils.isEmpty(IMPrefsTools.getStringPrefs(TutorLoginActivity.this,USER_ID,"")))
            etUsername.setText(IMPrefsTools.getStringPrefs(TutorLoginActivity.this,USER_ID,""));
        if (!CommonUtils.isEmpty(IMPrefsTools.getStringPrefs(TutorLoginActivity.this,PASSWORD,"")))
            etPassword.setText(IMPrefsTools.getStringPrefs(TutorLoginActivity.this,PASSWORD,""));

        findViewById(R.id.btn_login).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);
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
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void setUsernameError() {
        etUsername.setError("用户名错误");
    }

    @Override
    public void setPasswordError() {
        etPassword.setError("密码错误");
    }

    @Override
    public void navigateToHome() {
        mEditor.putBoolean("isLogin",true);
        mEditor.commit();
        saveIdPasswordToLocal(etUsername.getText().toString(),etPassword.getText().toString());
        readyGoThenKill(TutorMainActivity.class);
    }

    @Override
    public void onClick(View v) {
        if (v==btnLogin){
            presenter.validateCredentials(getBaseApplication().getIMKit(),etUsername.getText().toString(),etPassword.getText().toString());
        }else if (v==tvLoginRegister){
            readyGo(TutorRegisterActivity.class);
        }else if (v==tvLoginBack){
            finish();
        }
    }
    /**
     * 保存登录的用户名密码到本地
     *
     * @param userId
     * @param password
     */
    private void saveIdPasswordToLocal(String userId, String password) {
        IMPrefsTools.setStringPrefs(TutorLoginActivity.this, USER_ID, userId);
        IMPrefsTools.setStringPrefs(TutorLoginActivity.this, PASSWORD, password);

    }

}
