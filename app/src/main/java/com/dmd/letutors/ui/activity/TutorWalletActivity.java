package com.dmd.letutors.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dmd.dialog.MaterialDialog;
import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;

import butterknife.Bind;

public class TutorWalletActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_wallet_back)
    TextView tvWalletBack;
    @Bind(R.id.wallet_transaction_detail)
    TextView walletTransactionDetail;
    @Bind(R.id.wallet_cumulative_class)
    TextView walletCumulativeClass;
    @Bind(R.id.wallet_earn_money)
    TextView walletEarnMoney;
    @Bind(R.id.wallet_recharge)
    TextView walletRecharge;
    @Bind(R.id.wallet_withdrawals)
    TextView walletWithdrawals;
    @Bind(R.id.wallet_bank_card)
    TextView walletBankCard;
    @Bind(R.id.wallet_vouchers)
    TextView walletVouchers;


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_wallet;
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
        tvWalletBack.setOnClickListener(this);
        walletTransactionDetail.setOnClickListener(this);
        walletCumulativeClass.setOnClickListener(this);
        walletEarnMoney.setOnClickListener(this);
        walletRecharge.setOnClickListener(this);
        walletWithdrawals.setOnClickListener(this);
        walletBankCard.setOnClickListener(this);
        walletVouchers.setOnClickListener(this);
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

        if (v==tvWalletBack){
            finish();
        }else if (v==walletTransactionDetail){
            readyGo(TutorTransactionDetailActivity.class);
        }else if (v==walletCumulativeClass){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==walletEarnMoney){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==walletRecharge){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==walletWithdrawals){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==walletBankCard){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==walletVouchers){
            new MaterialDialog.Builder(this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }
    }
}
