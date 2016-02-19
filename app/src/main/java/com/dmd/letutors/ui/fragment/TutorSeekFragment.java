package com.dmd.letutors.ui.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dmd.baidulbs.LocationManager;
import com.dmd.dialog.MaterialDialog;
import com.dmd.letutors.R;
import com.dmd.letutors.api.ApiConstants;
import com.dmd.letutors.bean.ResponseTeacherListEntity;
import com.dmd.letutors.bean.Subject;
import com.dmd.letutors.bean.Subjectlevel;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.common.Constants;
import com.dmd.letutors.dao.SubjectDao;
import com.dmd.letutors.dao.SubjectlevelDao;
import com.dmd.letutors.presenter.SeekPresenter;
import com.dmd.letutors.presenter.impl.SeekPresenterIml;
import com.dmd.letutors.ui.activity.TutorTeacherDetailActivity;
import com.dmd.letutors.ui.activity.base.BaseFragment;
import com.dmd.letutors.ui.adapter.SeekOneAdapter;
import com.dmd.letutors.ui.adapter.SeekThreeAdapter;
import com.dmd.letutors.ui.adapter.SeekTwoAdapter;
import com.dmd.letutors.view.SeekView;
import com.dmd.letutors.widgets.LoadMoreListView;
import com.dmd.tutor.adapter.ListViewDataAdapter;
import com.dmd.tutor.adapter.ViewHolderBase;
import com.dmd.tutor.adapter.ViewHolderCreator;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.utils.CommonUtils;
import com.dmd.tutor.utils.TLog;
import com.dmd.tutor.widgets.XSwipeRefreshLayout;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.dao.query.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorSeekFragment extends BaseFragment implements SeekView, LoadMoreListView.OnLoadMoreListener, AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{

    @Bind(R.id.bar_seek_back)
    ImageView barSeekBack;

    @Bind(R.id.seek_group_menu_course)
    RadioButton seekGroupMenuCourse;
    @Bind(R.id.seek_group_menu_screening)
    RadioButton seekGroupMenuScreening;
    @Bind(R.id.seek_group_menu_sort)
    RadioButton seekGroupMenuSort;
    @Bind(R.id.seek_group_menu_audition)
    RadioButton seekGroupMenuAudition;

    private int mCurrentPage=0;

    private String keywors="1";
    @Bind(R.id.fragment_seek_list_list_view)
    LoadMoreListView mListView;
    @Bind(R.id.fragment_seek_list_swipe_layout)
    XSwipeRefreshLayout mSwipeRefreshLayout;

    private ListViewDataAdapter<TeacherListEntity> mListViewAdapter;
    private SeekPresenter mSeekPresenter=null;


    private ListView seek_listView_subject, seek_listView_subject_level,seek_listView_sort;
    private int screenWidth;
    private int screenHeight;

    private SeekOneAdapter seekOneAdapter;
    private SeekTwoAdapter seekTwoAdapter;
    private SeekThreeAdapter seekThreeAdapter;

    @Override
    protected void onFirstUserVisible() {
        mCurrentPage = 1;
        mSeekPresenter = new SeekPresenterIml(mContext, this);
        if (NetUtils.isNetworkConnected(mContext)) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, keywors, mCurrentPage, false);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, keywors, mCurrentPage, false);
                }
            });
        }
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    @Override
    protected void initViewsAndEvents() {
        initScreenWidth();//
        seekGroupMenuCourse.setChecked(true);

        mListViewAdapter = new ListViewDataAdapter<TeacherListEntity>(new ViewHolderCreator<TeacherListEntity>() {

            @Override
            public ViewHolderBase<TeacherListEntity> createViewHolder(int position) {
                return new ViewHolderBase<TeacherListEntity>() {
                    ImageView tutor_list_teacher_header_img;
                    TextView tutor_list_teacher_name,
                            tutor_list_teacher_type,
                            tutor_list_teacher_sex,
                            tutor_list_teacher_time,
                            tutor_list_teacher_Level,
                            tutor_list_teacher_content,
                            tutor_list_teacher_one2one,
                            tutor_list_teacher_one2more,
                            tutor_list_teacher_distance;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View view = layoutInflater.inflate(R.layout.tutor_teacher_list_item, null);
                        tutor_list_teacher_header_img = ButterKnife.findById(view, R.id.tutor_list_teacher_header_img);
                        tutor_list_teacher_name = ButterKnife.findById(view, R.id.tutor_list_teacher_name);
                        tutor_list_teacher_type = ButterKnife.findById(view, R.id.tutor_list_teacher_type);
                        tutor_list_teacher_sex = ButterKnife.findById(view, R.id.tutor_list_teacher_sex);
                        tutor_list_teacher_time = ButterKnife.findById(view, R.id.tutor_list_teacher_time);
                        tutor_list_teacher_Level = ButterKnife.findById(view, R.id.tutor_list_teacher_Level);
                        tutor_list_teacher_content = ButterKnife.findById(view, R.id.tutor_list_teacher_content);
                        tutor_list_teacher_one2one = ButterKnife.findById(view, R.id.tutor_list_teacher_one2one);
                        tutor_list_teacher_one2more = ButterKnife.findById(view, R.id.tutor_list_teacher_one2more);
                        tutor_list_teacher_distance = ButterKnife.findById(view, R.id.tutor_list_teacher_distance);
                        return view;
                    }

                    @Override
                    public void showData(int position, TeacherListEntity itemData) {
                        if (itemData != null) {

                            if (!CommonUtils.isEmpty(itemData.getTeacherPic())) {
                                Picasso.with(mContext).load(itemData.getTeacherPic()).into(tutor_list_teacher_header_img);
                            } else {
                                tutor_list_teacher_header_img.setImageResource(R.drawable.img_head_for_empty);
                            }

                            if (!CommonUtils.isEmpty(itemData.getTeacherName())) {
                                tutor_list_teacher_name.setText(itemData.getTeacherName());
                            } else {
                                tutor_list_teacher_name.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getJobType())) {
                                tutor_list_teacher_type.setText("("+itemData.getJobType()+")");
                            } else {
                                tutor_list_teacher_type.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getGender())) {
                                tutor_list_teacher_sex.setText(itemData.getGender());
                            } else {
                                tutor_list_teacher_sex.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getTotalTeachTime())) {
                                tutor_list_teacher_time.setText(itemData.getTotalTeachTime());
                            } else {
                                tutor_list_teacher_time.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getTeacherName())) {
                                tutor_list_teacher_Level.setText("没有认证等级字段");
                            } else {
                                tutor_list_teacher_Level.setText("未认证");
                            }

                            if (!CommonUtils.isEmpty(itemData.getSubject().getSubjectName())) {
                                tutor_list_teacher_content.setText(itemData.getSubject().getSubjectlevel().getSubjectLevelName()+itemData.getSubject().getSubjectName());
                            } else {
                                tutor_list_teacher_content.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getSimplePrice())) {
                                tutor_list_teacher_one2one.setText(itemData.getSimplePrice());
                            } else {
                                tutor_list_teacher_one2one.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getMultPrice())) {
                                tutor_list_teacher_one2more.setText(itemData.getMultPrice());
                            } else {
                                tutor_list_teacher_one2more.setText("");
                            }

                            if (!CommonUtils.isEmpty(itemData.getAtitude())&&!CommonUtils.isEmpty(itemData.getLongitude())) {
                                tutor_list_teacher_distance.setText(LocationManager.getLocation(Double.parseDouble(itemData.getAtitude()),Double.parseDouble(itemData.getLongitude())));
                            } else {
                                tutor_list_teacher_distance.setText("");
                            }

                        }
                    }
                };
            }
        });

        //TODO 数据适配

        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnLoadMoreListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        barSeekBack.setOnClickListener(this);

        seekGroupMenuCourse.setOnClickListener(this);
        seekGroupMenuScreening.setOnClickListener(this);
        seekGroupMenuSort.setOnClickListener(this);
        seekGroupMenuAudition.setOnClickListener(this);



    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tutor_seek;
    }

    @Subscribe
    @Override
    protected void onEventComming(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 1) {

        }
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == barSeekBack) {
            showToast("什么");
        }else if (v==seekGroupMenuCourse){
            if (seekGroupMenuCourse.isChecked()){
                onCreateCoursePopWindow(seekGroupMenuCourse);
            }
        }else if (v==seekGroupMenuScreening){
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .content("筛选")
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }else if (v==seekGroupMenuSort){
            if (seekGroupMenuSort.isChecked()){
                onCreateSortPopWindow(seekGroupMenuSort);
            }
        }else if (v==seekGroupMenuAudition){
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .content("试听")
                    .positiveText(R.string.agree)
                    .negativeText(R.string.disagree)
                    .show();
        }
    }

//=================================================================================================

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, 0+"", mCurrentPage, true);
    }
    @Override
    public void refreshListData(ResponseTeacherListEntity data) {
        if (mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setRefreshing(false);
        if (data != null) {
            if (data.getTeacherList().size() >= 2) {
                if (mListViewAdapter != null) {
                    mListViewAdapter.getDataList().clear();
                    mListViewAdapter.getDataList().addAll(data.getTeacherList());
                    mListViewAdapter.notifyDataSetChanged();
                }
            }
            if (data.getTotalPage() > mCurrentPage)
                mListView.setCanLoadMore(true);
            else
                mListView.setCanLoadMore(false);
        }
    }

    @Override
    public void onLoadMore() {
        mCurrentPage = 1 + mCurrentPage;
        mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA, 0+"", mCurrentPage, true);
    }

    @Override
    public void addMoreListData(ResponseTeacherListEntity data) {
        if (mListView != null)
            mListView.onLoadMoreComplete();
        if (data != null) {
            if (mListViewAdapter != null) {
                mListViewAdapter.getDataList().addAll(data.getTeacherList());
                mListViewAdapter.notifyDataSetChanged();
            }
            if (data.getTotalPage() > mCurrentPage)
                mListView.setCanLoadMore(true);
            else
                mListView.setCanLoadMore(false);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListViewAdapter != null) {
            if (mListViewAdapter.getDataList().size()>0)
                mSeekPresenter.onItemClickListener(position, (TeacherListEntity) mListViewAdapter.getDataList().get(position));
        }
    }

    @Override
    public void navigateToNewsDetail(int position, TeacherListEntity itemData) {
        Bundle bundle=new Bundle();
        bundle.putInt("teacherID",itemData.getId());
        readyGo(TutorTeacherDetailActivity.class,bundle);
    }

    //============================================================================================
    //排序
    public void onCreateSortPopWindow(View view) {
        final PopupWindow popupWindow = new PopupWindow(mContext);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.seek_menu_sort_popwindow, null);
        seek_listView_sort = (ListView) contentView.findViewById(R.id.seek_listView_sort);
        String[] strings=getActivity().getResources().getStringArray(R.array.sort_category_list);
        seekThreeAdapter =new SeekThreeAdapter(mContext,strings);
        seek_listView_sort.setAdapter(seekThreeAdapter);
        seek_listView_sort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keywors=parent.getAdapter().getItem(position).toString();
                //排序
                mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA, keywors, mCurrentPage, true);
                popupWindow.dismiss();
            }
        });
        popupWindow.setWidth(screenWidth);
        popupWindow.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.showAsDropDown(view);

    }
    //课程
    public void onCreateCoursePopWindow(View view) {
        final PopupWindow popupWindow = new PopupWindow(mContext);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.seek_menu_class_popupwindow, null);
        seek_listView_subject = (ListView) contentView.findViewById(R.id.seek_listView_subject);
        seek_listView_subject_level = (ListView) contentView.findViewById(R.id.seek_listView_subject_level);

/*
        List<Subjectlevel> subjectlevels =new ArrayList<Subjectlevel>();
        List<Subject> entities =new ArrayList<Subject>();
        for (int i=0;i<10;i++){
            subjectlevels.add(new Subjectlevel(null,"语文"+i));
            for (int j=0;j<10;j++){
                entities.add(new Subject(null,"http://192.168.1.109:8080/TutorClient/img/subject/home_biol.png",i+"","Y","数学"+i));
            }
        }
        getSubjectlevelDao().insertInTx(subjectlevels);
        getSubjectDao().insertInTx(entities);*/


        Query query = getSubjectlevelDao().queryBuilder()
                .where(SubjectlevelDao.Properties.SubjectLevelName.notEq(""))
                .build();

        List<Subjectlevel> subjects = query.list();

        seekOneAdapter = new SeekOneAdapter(subjects, getActivity());
        seek_listView_subject.setAdapter(seekOneAdapter);
        seek_listView_subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getAdapter() instanceof SeekOneAdapter) {

                    Query query = getSubjectDao().queryBuilder()
                            .where(SubjectDao.Properties.SubjectLevel_id.eq(((Subjectlevel)parent.getAdapter().getItem(position)).getId()))
                            .orderAsc(SubjectDao.Properties.SubjectLevel_id)
                            .build();
                    // 查询结果以 List 返回
                    List<Subject> subjects = query.list();

                    if (seek_listView_subject_level.getVisibility() == View.INVISIBLE) {
                        seek_listView_subject_level.setVisibility(View.VISIBLE);
                    }
                    if (!subjects.isEmpty()){
                        seekTwoAdapter = new SeekTwoAdapter(subjects, getActivity());
                        seek_listView_subject_level.setAdapter(seekTwoAdapter);
                        seekTwoAdapter.notifyDataSetChanged();
                        seek_listView_subject_level.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                keywors=((Subject) parent.getAdapter().getItem(position)).getId()+"";
                                //请求数据
                                mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA,keywors , mCurrentPage, true);
                                popupWindow.dismiss();
                            }
                        });
                    }
                }
            }
        });
        Query query2 = getSubjectDao().queryBuilder()
                .where(SubjectDao.Properties.SubjectLevel_id.eq("2"))
                .orderAsc(SubjectDao.Properties.SubjectLevel_id)
                .build();
        // 查询结果以 List 返回
        List<Subject> subjects2 = query2.list();
        if (seek_listView_subject_level.getVisibility() == View.INVISIBLE) {
            seek_listView_subject_level.setVisibility(View.VISIBLE);
        }
        seekTwoAdapter = new SeekTwoAdapter(subjects2, getActivity());
        seek_listView_subject_level.setAdapter(seekTwoAdapter);
        seek_listView_subject_level.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keywors=((Subject) parent.getAdapter().getItem(position)).getSubjectLevel_id();
                mSeekPresenter.loadListData(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA,keywors, mCurrentPage, true);
                popupWindow.dismiss();
            }
        });

        popupWindow.setWidth(screenWidth);
        popupWindow.setHeight(screenHeight*4);
        popupWindow.setContentView(contentView);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.showAsDropDown(view);
    }
    /**
     * @Title: initScreenWidth
     * @Description: 查看自身的宽高
     * @author lmw
     * @return void 返回类型
     */
    private void initScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels/10;
        screenWidth = dm.widthPixels;
        TLog.v("屏幕宽高", "宽度" + screenWidth + "高度" + screenHeight);
    }

    private SubjectDao getSubjectDao(){
       return getBaseApplication().getDaoSession().getSubjectDao();
    }

    private SubjectlevelDao getSubjectlevelDao(){
        return getBaseApplication().getDaoSession().getSubjectlevelDao();
    }

    private SQLiteDatabase getSQLiteDatabase(){
        return getBaseApplication().getDb();
    }
}
