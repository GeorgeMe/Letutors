package com.dmd.letutors.presenter.impl;

import android.content.Context;

import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.interactor.TeacherDetailInteractor;
import com.dmd.letutors.interactor.impl.TeacherDetailInteractorImpl;
import com.dmd.letutors.listeners.BaseSingleLoadedListener;
import com.dmd.letutors.presenter.TeacherDetailPresenter;
import com.dmd.letutors.view.TeacherDetailView;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/12/30.
 */
public class TeacherDetailPresenterImpl implements TeacherDetailPresenter,BaseSingleLoadedListener<TeacherListEntity>{
    private Context mContext=null;
    private TeacherDetailView mTeacherDetailView;
    private TeacherDetailInteractor mCommonListInteractor;

    public TeacherDetailPresenterImpl(Context mContext, TeacherDetailView mTeacherDetailView) {
        this.mContext = mContext;
        this.mTeacherDetailView = mTeacherDetailView;
        mCommonListInteractor=new TeacherDetailInteractorImpl(this);
    }

    @Override
    public void getTeacherDetail(int id) {
        mCommonListInteractor.getTeacherDetail(id);
    }

    @Override
    public void onSuccess(TeacherListEntity data) {
        mCommonListInteractor.getTestJson(new Gson().toJson(data).toString());
        mTeacherDetailView.setTeacherDetail(data);
    }

    @Override
    public void onError(String msg) {
        mTeacherDetailView.TeacherDetailshowError(msg);
    }

    @Override
    public void onException(String msg) {
        mTeacherDetailView.TeacherDetailshowException(msg);
    }
}
