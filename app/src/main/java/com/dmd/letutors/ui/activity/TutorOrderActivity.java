package com.dmd.letutors.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dmd.dialog.MaterialDialog;
import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.letutors.widgets.LoadMoreListView;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.widgets.XSwipeRefreshLayout;

import butterknife.Bind;

public class TutorOrderActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    @Bind(R.id.bar_my_order_back)
    TextView barMyOrderBack;
    @Bind(R.id.my_order_group_menu_incomplete)
    RadioButton myOrderGroupMenuIncomplete;
    @Bind(R.id.my_order_group_menu_recent_completed)
    RadioButton myOrderGroupMenuRecentCompleted;
    @Bind(R.id.my_order_menu_group)
    RadioGroup myOrderMenuGroup;
    @Bind(R.id.fragment_my_order_list_list_view)
    LoadMoreListView fragmentMyOrderListListView;
    @Bind(R.id.fragment_my_order_list_swipe_layout)
    XSwipeRefreshLayout fragmentMyOrderListSwipeLayout;


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_order;
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
        myOrderMenuGroup.setOnCheckedChangeListener(this);
        barMyOrderBack.setOnClickListener(this);
        myOrderGroupMenuIncomplete.setChecked(true);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId==R.id.my_order_group_menu_recent_completed){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (checkedId==R.id.my_order_group_menu_incomplete){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v==barMyOrderBack){
            finish();
        }
    }
}
