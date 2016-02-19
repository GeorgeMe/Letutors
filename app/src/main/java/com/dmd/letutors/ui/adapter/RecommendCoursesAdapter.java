package com.dmd.letutors.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.dmd.letutors.api.ApiConstants;
import com.dmd.letutors.bean.SubjectEntity;
import com.dmd.tutor.ninelayout.NineGridAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
public class RecommendCoursesAdapter extends NineGridAdapter {

        private int ruler;
        //private Context context;
        public RecommendCoursesAdapter(Context context, List<SubjectEntity> list, int ruler) {
            super(context, list,ruler);
            this.ruler=ruler;
            //this.context=context;
        }

        @Override
        public int getCount() {
            return (list == null) ? 0 : list.size();
        }

        @Override
        public String getUrl(int positopn) {
            //TODO url没有获取到
            return getItem(positopn) == null ? null : ((SubjectEntity)getItem(positopn)).getImgUrl();
        }

        @Override
        public Object getItem(int position) {
            return (list == null) ? null : list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int i, View view) {
            ImageView iv = null;
            if (view != null && view instanceof ImageView) {
                iv = (ImageView) view;
            } else {
                iv = new ImageView(context);
            }
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setBackgroundColor(context.getResources().getColor((android.R.color.transparent)));
            String url = getUrl(i);
            Picasso.with(context).load(ApiConstants.Urls.TUTOR_API_URLS+url).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);
            if (!TextUtils.isEmpty(url)) {
                iv.setTag(url);
            }
            return iv;
        }

        @Override
        public int getRuler() {
            return ruler;
        }

}