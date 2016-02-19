package com.dmd.letutors.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseFragment;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.utils.BusHelper;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorTeacherDetail extends BaseFragment implements View.OnClickListener{


    @Bind(R.id.bar_teacher_detail_back)
    TextView barTeacherDetailBack;

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
        barTeacherDetailBack.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tutor_teacher_detail;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v==barTeacherDetailBack){
            BusHelper.post(new EventCenter(2,null));
/*            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.realtabcontent,  new TutorHomeFragment());
            transaction.addToBackStack("home");
            transaction.commit();*/
        }
    }
}
