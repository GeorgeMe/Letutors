package com.dmd.letutors.interactor.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dmd.letutors.R;
import com.dmd.letutors.interactor.SplashInteractor;

import java.util.Calendar;

public class SplashInteractorImpl implements SplashInteractor {

    @Override
    public Animation getBackgroundImageAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.tutor_splash);
    }

    @Override
    public int getBackgroundImageResID() {
        int resId=0;
        Calendar calendar = Calendar.getInstance();
/*        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            resId = R.drawable.morning;
        } else if (hour > 12 && hour <= 18) {
            resId = R.drawable.afternoon;
        } else {
            resId = R.drawable.night;
        }*/
        return resId;
    }

    @Override
    public String getVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(context.getResources().getString(R.string.splash_version), versionName);
    }

    @Override
    public String getCopyright(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }
}
