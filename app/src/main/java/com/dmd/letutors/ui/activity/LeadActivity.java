package com.dmd.letutors.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.dmd.letutors.R;
import com.dmd.letutors.ui.adapter.LeadAdapter;
import com.dmd.tutor.viewpagerindicator.CirclePageIndicator;

public class LeadActivity extends Activity {
    private ViewPager leadViewPager;
    private CirclePageIndicator indicator;


    private int[] imageBg = {R.drawable.b_1, R.drawable.b_2, R.drawable.b_3};

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        mShared = getSharedPreferences("user_info", 0);
        mEditor = mShared.edit();

        leadViewPager = (ViewPager) findViewById(R.id.lead_viewPager);
        indicator = (CirclePageIndicator)findViewById(R.id.indicator);

        leadViewPager.setAdapter(new LeadAdapter(this, imageBg));
        indicator.setViewPager(leadViewPager,0);


        leadViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                indicator.setCurrentItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
    }
}
