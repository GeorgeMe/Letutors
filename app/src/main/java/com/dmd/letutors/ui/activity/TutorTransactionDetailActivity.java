package com.dmd.letutors.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.letutors.widgets.LoadMoreListView;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.widgets.XSwipeRefreshLayout;

import butterknife.Bind;

public class TutorTransactionDetailActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_transaction_detail_back)
    TextView tvTransactionDetailBack;
    @Bind(R.id.transaction_detail_list_list_view)
    LoadMoreListView transactionDetailListListView;
    @Bind(R.id.transaction_detail_list_swipe_layout)
    XSwipeRefreshLayout transactionDetailListSwipeLayout;


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_transaction_detail;
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
        tvTransactionDetailBack.setOnClickListener(this);
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
        if (v==tvTransactionDetailBack){
            finish();
        }
    }
}
