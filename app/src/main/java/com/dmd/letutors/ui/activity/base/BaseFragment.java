package com.dmd.letutors.ui.activity.base;


import com.dmd.letutors.TutorApplication;
import com.dmd.letutors.view.base.BaseView;
import com.dmd.tutor.base.BaseLazyFragment;

/**
 *
 */
public abstract class BaseFragment extends BaseLazyFragment implements BaseView {

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    protected TutorApplication getBaseApplication() {
        return (TutorApplication) getActivity().getApplicationContext();
    }

}
