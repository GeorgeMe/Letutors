package com.dmd.letutors.presenter.impl;

import android.content.Context;

import com.dmd.letutors.R;
import com.dmd.letutors.bean.ResponseTeacherListEntity;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.common.Constants;
import com.dmd.letutors.interactor.impl.SeekInteractorImpl;
import com.dmd.letutors.listeners.BaseMultiLoadedListener;
import com.dmd.letutors.presenter.SeekPresenter;
import com.dmd.letutors.view.SeekView;

/**
 * Created by Administrator on 2015/12/22.
 */
public class SeekPresenterIml implements SeekPresenter,BaseMultiLoadedListener<ResponseTeacherListEntity> {
    private Context mContext=null;
    private SeekView mSeekView=null;
    private SeekInteractorImpl mCommonListInteractor = null;

    public SeekPresenterIml(Context mContext, SeekView mSeekView) {
        this.mContext = mContext;
        this.mSeekView = mSeekView;
        mCommonListInteractor = new SeekInteractorImpl(this);
    }

    @Override
    public void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh) {
        mSeekView.hideLoading();
        if (!isSwipeRefresh) {
            mSeekView.showLoading(mContext.getString(R.string.common_loading_message));
        }
        mCommonListInteractor.getCommonListData(requestTag, event_tag, keywords, page);
    }

    @Override
    public void onItemClickListener(int position, TeacherListEntity itemData) {
        mSeekView.navigateToNewsDetail(position, itemData);
    }

    @Override
    public void onSuccess(int event_tag, ResponseTeacherListEntity data) {
        mSeekView.hideLoading();
        if (event_tag == Constants.EVENT_REFRESH_DATA) {
            mSeekView.refreshListData(data);
        } else if (event_tag == Constants.EVENT_LOAD_MORE_DATA) {
            mSeekView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mSeekView.hideLoading();
        mSeekView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mSeekView.hideLoading();
        mSeekView.showException(msg);
    }
}
