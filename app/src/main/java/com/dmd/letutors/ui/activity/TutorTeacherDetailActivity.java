package com.dmd.letutors.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmd.dialog.MaterialDialog;
import com.dmd.letutors.R;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.presenter.impl.TeacherDetailPresenterImpl;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.letutors.view.TeacherDetailView;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.utils.TLog;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class TutorTeacherDetailActivity extends BaseActivity implements TeacherDetailView, View.OnClickListener {

    @Bind(R.id.bar_teacher_detail_back)
    TextView barTeacherDetailBack;
    @Bind(R.id.teacher_img_header)
    ImageView teacherImgHeader;
    @Bind(R.id.teacher_name)
    TextView teacherName;
    @Bind(R.id.teacher_gender)
    TextView teacherGender;
    @Bind(R.id.teacher_haoping)
    TextView teacherHaoping;
    @Bind(R.id.teacher_yueke)
    TextView teacherYueke;
    @Bind(R.id.teacher_guanzhu)
    TextView teacherGuanzhu;
    @Bind(R.id.teacher_jopType)
    TextView teacherJopType;
    @Bind(R.id.teacher_Price)
    TextView teacherPrice;
    @Bind(R.id.teacher_yueke_time)
    TextView teacherYuekeTime;
    @Bind(R.id.teacher_subject)
    TextView teacherSubject;
    @Bind(R.id.teacher_address)
    TextView teacherAddress;
    @Bind(R.id.teacher_teachWay)
    TextView teacherTeachWay;
    @Bind(R.id.teacher_evaluation)
    TextView teacherEvaluation;
    @Bind(R.id.teacher_zhuanye)
    TextView teacherZhuanye;
    @Bind(R.id.teacher_shanchang)
    TextView teacherShanchang;
    @Bind(R.id.btn_msg)
    Button btnMsg;

    private TeacherDetailPresenterImpl mTeacherDetailView;


    @Override
    protected void getBundleExtras(Bundle extras) {
        mTeacherDetailView = new TeacherDetailPresenterImpl(mContext, this);
        mTeacherDetailView.getTeacherDetail(extras.getInt("teacherID"));
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_teacher_detail;
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
        teacherGuanzhu.setOnClickListener(this);
        teacherYueke.setOnClickListener(this);
        barTeacherDetailBack.setOnClickListener(this);
        btnMsg.setOnClickListener(this);
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
    public void setTeacherDetail(TeacherListEntity data) {
        TLog.e(TAG_LOG, data.toString());
        if (data != null) {
            if (teacherImgHeader != null && data.getTeacherPic() != null) {
                Picasso.with(mContext).load(data.getTeacherPic()).into(teacherImgHeader);
            }

            if (teacherName != null && data.getTeacherName() != null) {
                teacherName.setText(data.getTeacherName());
            }

            if (teacherGender != null && data.getGender() != null) {
                teacherGender.setText(data.getGender());
            }

            if (teacherHaoping != null && data.getEvaluation() != null) {
                teacherHaoping.setText(data.getEvaluation());
            }

            if (teacherJopType != null && data.getJobType() != null) {
                teacherJopType.setText(data.getJobType());
            }
            if (teacherPrice != null && data.getMultPrice() != null && data.getSimplePrice() != null) {
                teacherPrice.setText(data.getMultPrice() + "￥--" + data.getSimplePrice() + "￥");
            }
            if (teacherYuekeTime != null) {

            }
            if (teacherSubject != null && data.getSubject() != null) {
                teacherSubject.setText(data.getSubject().getSubjectlevel().getSubjectLevelName() + data.getSubject().getSubjectName());
            }
            if (teacherAddress != null && data.getTeachAddress() != null) {
                teacherAddress.setText(data.getTeachAddress());
            }
            if (teacherTeachWay != null && data.getTeachWay() != null) {
                teacherTeachWay.setText(data.getTeachWay());
            }
            if (teacherEvaluation != null && data.getZijipinjia() != null) {
                teacherEvaluation.setText(data.getZijipinjia());
            }
            if (teacherZhuanye != null && data.getSubject() != null) {
                teacherZhuanye.setText(data.getSubject().getSubjectlevel().getSubjectLevelName() + data.getSubject().getSubjectName());
            }
            if (teacherShanchang != null && data.getSubject() != null) {
                teacherShanchang.setText(data.getSubject().getSubjectlevel().getSubjectLevelName() + data.getSubject().getSubjectName());
            }
        }
    }

    @Override
    public void navigateToHome() {
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == teacherYueke) {
            readyGo(TutorClassMeetingActivity.class);
/*            new MaterialDialog.Builder(TutorTeacherDetailActivity.this)
                    .title(R.string.title)
                    .content(R.string.teacher_yue_ke)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();*/
        } else if (v == teacherGuanzhu) {
            new MaterialDialog.Builder(TutorTeacherDetailActivity.this)
                    .title(R.string.title)
                    .content(R.string.teacher_guan_zhu)
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        } else if (v == barTeacherDetailBack) {
            navigateToHome();
        }else if (v==btnMsg){
            String target = "18223403150";// 消息接收者ID
            Intent intent =getBaseApplication().getIMKit().getChattingActivityIntent(target);
            startActivity(intent);
        }
    }

    @Override
    public void TeacherDetailshowError(String msg) {
        showToast(msg);
    }

    @Override
    public void TeacherDetailshowException(String msg) {
        showToast(msg);
    }

}
