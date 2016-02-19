package com.dmd.letutors.view;

import com.dmd.letutors.bean.Home;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.view.base.BaseView;

/**
 * Created by George on 2015/12/6.
 */
public interface HomeView extends BaseView{

    void navigateToNewsDetail(int position, TeacherListEntity itemData);

    void refreshListData(Home data);

    void addMoreListData(Home data);

}
