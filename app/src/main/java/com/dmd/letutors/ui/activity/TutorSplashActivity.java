package com.dmd.letutors.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.dmd.letutors.R;

public class TutorSplashActivity extends Activity {
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View startView = View.inflate(this, R.layout.activity_tutor_splash, null);
        setContentView(startView);
        mShared =getSharedPreferences("user_info", 0);
        mEditor = mShared.edit();
        //渐变
        AlphaAnimation aa = new AlphaAnimation(1f, 1.0f);
        aa.setDuration(2500);
        startView.setAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {

                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                        // TODO Auto-generated method stub
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {
                                        // TODO Auto-generated method stub
                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        // TODO Auto-generated method stub
                                        redirectto();
                                    }
                                }
        );
    }

    private void redirectto() {
        boolean isFirstRunLead = mShared.getBoolean("isFirstRunLead", true);
        if (isFirstRunLead) {
            Intent intent = new Intent(this, LeadActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
            finish();
        } else {
            if (mShared.getBoolean("isLogin", false)) {
                Intent intent = new Intent(this, TutorMainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
            } else {
                //mEditor.putBoolean("isFirstRunLead", true);
                mEditor.putBoolean("isLogin", false);
                mEditor.commit();
/*                mEditor.putString("user", "");
                mEditor.putInt("uid", 0);
                mEditor.putString("sid", "");
                mEditor.commit();
                SESSION.getInstance().uid = mShared.getInt("uid", 0);
                SESSION.getInstance().sid = mShared.getString("sid", "");*/

                Intent intent = new Intent(this, TutorLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
            }
        }

    }
}
