package com.dmd.letutors.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmd.dialog.MaterialDialog;
import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.TutorAboutUsActivity;
import com.dmd.letutors.ui.activity.TutorEvaluationActivity;
import com.dmd.letutors.ui.activity.TutorModifyDataActivity;
import com.dmd.letutors.ui.activity.TutorNeedsActivity;
import com.dmd.letutors.ui.activity.TutorOrderActivity;
import com.dmd.letutors.ui.activity.TutorVouchersActivity;
import com.dmd.letutors.ui.activity.TutorWalletActivity;
import com.dmd.letutors.ui.activity.base.BaseFragment;
import com.dmd.tutor.eventbus.EventCenter;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorMineFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.mine_sign_out_header_img)
    ImageView mineSignOutHeaderImg;
    @Bind(R.id.mine_sign_out_header)
    LinearLayout mineSignOutHeader;
    @Bind(R.id.mine_logout_header_img)
    ImageView mineLogoutHeaderImg;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_job_type)
    TextView mineJobType;
    @Bind(R.id.mine_gender)
    TextView mineGender;
    @Bind(R.id.mine_address)
    TextView mineAddress;
    @Bind(R.id.mine_one_to_one)
    TextView mineOneToOne;
    @Bind(R.id.mine_one_to_many)
    TextView mineOneToMany;
    @Bind(R.id.mine_good_at_subjects)
    TextView mineGoodAtSubjects;
    @Bind(R.id.mine_modify_data)
    TextView mineModifyData;
    @Bind(R.id.mine_logout_header)
    LinearLayout mineLogoutHeader;
    @Bind(R.id.mine_wallet)
    TextView mineWallet;
    @Bind(R.id.mine_order)
    TextView mineOrder;
    @Bind(R.id.mine_evaluation)
    TextView mineEvaluation;
    @Bind(R.id.mine_needs)
    TextView mineNeeds;
    @Bind(R.id.mine_vouchers)
    TextView mineVouchers;
    @Bind(R.id.mine_about_us)
    TextView mineAboutUs;
    @Bind(R.id.mine_switch_account)
    TextView mineSwitchAccount;
    @Bind(R.id.mine_sign_out)
    TextView mineSignOut;

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mineLogoutHeaderImg.setOnClickListener(this);
        mineModifyData.setOnClickListener(this);
        mineWallet.setOnClickListener(this);
        mineOrder.setOnClickListener(this);
        mineEvaluation.setOnClickListener(this);
        mineNeeds.setOnClickListener(this);
        mineAboutUs.setOnClickListener(this);
        mineSwitchAccount.setOnClickListener(this);
        mineSignOut.setOnClickListener(this);
        mineVouchers.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tutor_mine;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v==mineLogoutHeaderImg){
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .content("头像")
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==mineModifyData){
            readyGo(TutorModifyDataActivity.class);
        }else if (v==mineWallet){
            readyGo(TutorWalletActivity.class);
        }else if (v==mineOrder){
            readyGo(TutorOrderActivity.class);
        }else if (v==mineEvaluation){
            readyGo(TutorEvaluationActivity.class);
        }else if (v==mineNeeds){
            readyGo(TutorNeedsActivity.class);
        }else if (v==mineVouchers){
            readyGo(TutorVouchersActivity.class);
        }else if (v==mineAboutUs){
            readyGo(TutorAboutUsActivity.class);
        }else if (v==mineSwitchAccount){
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .content("切换账号")
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==mineSignOut){
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .content("退出登录")
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }
    }
}
