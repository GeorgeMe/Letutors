package com.dmd.letutors.presenter.impl;

import android.content.Context;

import com.dmd.letutors.presenter.Presenter;
import com.dmd.letutors.view.MainView;

/**
 * Created by Administrator on 2015/12/14.
 */
public class MainViewImpl implements Presenter {
    private Context mContext=null;
    private MainView mMainView=null;
    public MainViewImpl(Context context,MainView mainView) {
        mContext=context;
        mMainView=mainView;
    }

    @Override
    public void initialized() {
        mMainView.initTabView();
    }
}
