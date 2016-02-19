package com.dmd.letutors.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmd.dialog.MaterialDialog;
import com.dmd.letutors.R;
import com.dmd.letutors.ui.activity.base.BaseActivity;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.timepicker.ScreenInfo;
import com.dmd.tutor.timepicker.WheelMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class TutorClassMeetingActivity extends BaseActivity{


    @Bind(R.id.bar_class_meeting_back)
    TextView barClassMeetingBack;
    @Bind(R.id.class_meeting_ke_mu)
    TextView classMeetingKeMu;
    @Bind(R.id.class_meeting_ke_mu_r)
    RelativeLayout classMeetingKeMuR;
    @Bind(R.id.class_meeting_shen_fen)
    TextView classMeetingShenFen;
    @Bind(R.id.class_meeting_shen_fen_r)
    RelativeLayout classMeetingShenFenR;
    @Bind(R.id.class_meeting_xing_bie)
    TextView classMeetingXingBie;
    @Bind(R.id.class_meeting_xing_bie_r)
    RelativeLayout classMeetingXingBieR;
    @Bind(R.id.class_meeting_jia_ge)
    TextView classMeetingJiaGe;
    @Bind(R.id.class_meeting_jia_ge_r)
    RelativeLayout classMeetingJiaGeR;
    @Bind(R.id.class_meeting_shi_jian)
    TextView classMeetingShiJian;
    @Bind(R.id.class_meeting_shi_jian_r)
    RelativeLayout classMeetingShiJianR;
    @Bind(R.id.class_meeting_di_dian)
    TextView classMeetingDiDian;
    @Bind(R.id.class_meeting_di_dian_r)
    RelativeLayout classMeetingDiDianR;
    @Bind(R.id.class_meeting_fang_shi)
    TextView classMeetingFangShi;
    @Bind(R.id.class_meeting_fang_shi_r)
    RelativeLayout classMeetingFangShiR;
    @Bind(R.id.class_meeting_hao_ping)
    TextView classMeetingHaoPing;
    @Bind(R.id.class_meeting_hao_ping_r)
    RelativeLayout classMeetingHaoPingR;
    @Bind(R.id.class_meeting_ok)
    Button classMeetingOk;


    private WheelMain mWheelMain;
    private SimpleDateFormat mFormat;
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tutor_class_meeting;
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
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

    @OnClick({R.id.bar_class_meeting_back, R.id.class_meeting_ke_mu_r, R.id.class_meeting_shen_fen_r, R.id.class_meeting_xing_bie_r, R.id.class_meeting_jia_ge_r, R.id.class_meeting_shi_jian_r, R.id.class_meeting_di_dian_r, R.id.class_meeting_fang_shi_r, R.id.class_meeting_hao_ping_r,R.id.class_meeting_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_class_meeting_back:
                finish();
                break;
            case R.id.class_meeting_ke_mu_r:
                new MaterialDialog.Builder(this)
                        .title(R.string.input)
                        .content(R.string.input_content)
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL)
                        .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                                classMeetingKeMu.setText(input);
                                classMeetingKeMu.setCompoundDrawables(null,null,null,null);
                            }
                        }).show();
                break;
            case R.id.class_meeting_shen_fen_r:
                new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        .items(R.array.shen_fen)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                classMeetingShenFen.setText(text);
                                classMeetingShenFen.setCompoundDrawables(null,null,null,null);
                                return true; // allow selection
                            }
                        })
                        .positiveText(R.string.md_choose_label)
                        .show();
                break;
            case R.id.class_meeting_xing_bie_r:
                new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        .items(R.array.xing_bie)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                classMeetingXingBie.setText(text);
                                classMeetingXingBie.setCompoundDrawables(null,null,null,null);
                                return true; // allow selection
                            }
                        })
                        .positiveText(R.string.md_choose_label)
                        .show();
                break;
            case R.id.class_meeting_jia_ge_r:
                new MaterialDialog.Builder(this)
                        .title(R.string.input)
                        .content(R.string.input_content)
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL)
                        .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                                classMeetingJiaGe.setText(input+"￥");
                                classMeetingJiaGe.setCompoundDrawables(null,null,null,null);
                            }
                        }).show();
                break;
            case R.id.class_meeting_shi_jian_r:
                Date date = new Date();
                classMeetingShiJian.setText(mFormat.format(date));
                LayoutInflater inflater = LayoutInflater.from(TutorClassMeetingActivity.this);
                final View timepickerview = inflater.inflate(R.layout.timepicker, null);
                ScreenInfo screenInfo = new ScreenInfo(TutorClassMeetingActivity.this);
                mWheelMain = new WheelMain(timepickerview, true);
                mWheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(mFormat.parse(classMeetingShiJian.getText().toString()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                mWheelMain.initDateTimePicker(year, month, day, hour, min);
                new AlertDialog.Builder(TutorClassMeetingActivity.this)
                        .setTitle(getString(R.string.choose_time))
                        .setView(timepickerview)
                        .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                classMeetingShiJian.setText(mWheelMain.getTime());
                                classMeetingShiJian.setCompoundDrawables(null,null,null,null);
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
            case R.id.class_meeting_di_dian_r:
                final String input2=classMeetingDiDian.getText().toString();
                new MaterialDialog.Builder(this)
                        .title(R.string.input)
                        .content(R.string.input_content)
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL)
                        .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                                if (input!=null && input!=""){
                                    classMeetingDiDian.setText(input);
                                }else {
                                    classMeetingDiDian.setText(input2);
                                }
                                classMeetingDiDian.setCompoundDrawables(null,null,null,null);
                            }
                        }).show();
                break;
            case R.id.class_meeting_fang_shi_r:
                new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        .items(R.array.fang_shi)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                classMeetingFangShi.setText(text);
                                classMeetingFangShi.setCompoundDrawables(null,null,null,null);
                                return true; // allow selection
                            }
                        })
                        .positiveText(R.string.md_choose_label)
                        .show();
                break;
            case R.id.class_meeting_hao_ping_r:
                new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        .items(R.array.hao_ping)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                classMeetingHaoPing.setText(text);
                                classMeetingHaoPing.setCompoundDrawables(null,null,null,null);
                                return true; // allow selection
                            }
                        })
                        .positiveText(R.string.md_choose_label)
                        .show();
                break;
            case R.id.class_meeting_ok:
                finish();
                //TODO
                break;
        }
    }
}
