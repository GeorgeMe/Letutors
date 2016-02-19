package com.dmd.letutors.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseFragment;
import com.dmd.tutor.eventbus.EventCenter;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorMessageFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.message_menu_group)
    RadioGroup messageMenuGroup;

    @Bind(R.id.message_group_menu_message)
    RadioButton messageGroupMenuMessage;
    @Bind(R.id.message_group_menu_recent_contacts)
    RadioButton messageGroupMenuRecentContacts;
    @Bind(R.id.message_group_menu_attention)
    RadioButton messageGroupMenuAttention;

    @Override
    protected void onFirstUserVisible() {
        messageGroupMenuMessage.setChecked(true);
        messageGroupMenuRecentContacts.setChecked(false);
        messageGroupMenuAttention.setChecked(false);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_message_view, getBaseApplication().getIMKit().getConversationFragment());
        transaction.commit();
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
        messageMenuGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tutor_message;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId==R.id.message_group_menu_message){//消息
            messageGroupMenuMessage.setChecked(true);
            messageGroupMenuRecentContacts.setChecked(false);
            messageGroupMenuAttention.setChecked(false);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragment_message_view, getBaseApplication().getIMKit().getConversationFragment());
            transaction.replace(R.id.fragment_message_view, getBaseApplication().getIMKit().getConversationFragment());
            transaction.commit();

        }else if (checkedId==R.id.message_group_menu_recent_contacts){//联系人
            messageGroupMenuMessage.setChecked(false);
            messageGroupMenuRecentContacts.setChecked(true);
            messageGroupMenuAttention.setChecked(false);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragment_message_view, getBaseApplication().getIMKit());
            //transaction.replace(R.id.fragment_message_view, new ContactsFragment());
            transaction.commit();
        }else if (checkedId==R.id.message_group_menu_attention){//关注
            messageGroupMenuMessage.setChecked(false);
            messageGroupMenuRecentContacts.setChecked(false);
            messageGroupMenuAttention.setChecked(true);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_message_view, getBaseApplication().getIMKit().getConversationFragment());
            transaction.commit();
        }
    }

}
