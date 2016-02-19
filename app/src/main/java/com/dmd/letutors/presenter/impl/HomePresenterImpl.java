package com.dmd.letutors.presenter.impl;

import android.content.Context;

import com.dmd.letutors.R;
import com.dmd.letutors.bean.Home;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.common.Constants;
import com.dmd.letutors.interactor.impl.HomeInteracterImpl;
import com.dmd.letutors.listeners.BaseMultiLoadedListener;
import com.dmd.letutors.presenter.HomePresenter;
import com.dmd.letutors.view.HomeView;

/**
 * Created by Administrator on 2015/12/15.
 */
public class HomePresenterImpl implements HomePresenter,BaseMultiLoadedListener<Home> {

    private Context mContext=null;
    private HomeView mHomeView=null;
    private HomeInteracterImpl mCommonListInteractor = null;

    public HomePresenterImpl(Context mContext,HomeView mHomeView){
        this.mContext=mContext;
        this.mHomeView=mHomeView;
        mCommonListInteractor=new HomeInteracterImpl(this);
    }
    @Override
    public void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh) {
        mHomeView.hideLoading();
        if (!isSwipeRefresh) {
            mHomeView.showLoading(mContext.getString(R.string.common_loading_message));
        }
        mCommonListInteractor.getCommonListData(requestTag, event_tag, keywords, page);
    }

    @Override
    public void onItemClickListener(int position, TeacherListEntity itemData) {
        mHomeView.navigateToNewsDetail(position, itemData);
    }

    @Override
    public void onSuccess(int event_tag, Home data) {
        mHomeView.hideLoading();
        if (event_tag == Constants.EVENT_REFRESH_DATA) {
            mHomeView.refreshListData(data);
        } else if (event_tag == Constants.EVENT_LOAD_MORE_DATA) {
            mHomeView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mHomeView.hideLoading();
        mHomeView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mHomeView.hideLoading();
        mHomeView.showError(msg);
    }

    @Override
    public void requestBDLocation(String userId,double latitude, double longitude) {
       // mCommonListInteractor.requestBDLocation(userId,latitude,longitude);
    }
}
