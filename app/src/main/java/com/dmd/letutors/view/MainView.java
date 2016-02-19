package com.dmd.letutors.view;

import android.view.View;

/**
 * Created by George on 2015/12/2.
 */
public interface MainView {

    void initTabView();
    View getIndicatorView(String tab);
    void setHomeText(boolean isSelected);
    void setMessageText(boolean isSelected);
    void setSeekText(boolean isSelected);
    void setMineText(boolean isSelected);

}
