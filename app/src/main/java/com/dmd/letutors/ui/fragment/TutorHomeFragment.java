package com.dmd.letutors.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.dmd.baidulbs.LocationManager;
import com.dmd.letutors.R;
import com.dmd.letutors.api.ApiConstants;
import com.dmd.letutors.bean.Home;
import com.dmd.letutors.bean.SubjectEntity;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.common.Constants;
import com.dmd.letutors.presenter.HomePresenter;
import com.dmd.letutors.presenter.impl.HomePresenterImpl;
import com.dmd.letutors.ui.activity.TutorClassMeetingActivity;
import com.dmd.letutors.ui.activity.TutorTeacherDetailActivity;
import com.dmd.letutors.ui.activity.base.BaseFragment;
import com.dmd.letutors.ui.adapter.CarouselAdapter;
import com.dmd.letutors.ui.adapter.RecommendCoursesAdapter;
import com.dmd.letutors.view.HomeView;
import com.dmd.letutors.widgets.LoadMoreListView;
import com.dmd.tutor.adapter.ListViewDataAdapter;
import com.dmd.tutor.adapter.ViewHolderBase;
import com.dmd.tutor.adapter.ViewHolderCreator;
import com.dmd.tutor.eventbus.EventCenter;
import com.dmd.tutor.netstatus.NetUtils;
import com.dmd.tutor.ninelayout.NineGridlayout;
import com.dmd.tutor.rollviewpager.RollPagerView;
import com.dmd.tutor.utils.BusHelper;
import com.dmd.tutor.utils.CommonUtils;
import com.dmd.tutor.utils.TLog;
import com.dmd.tutor.widgets.XSwipeRefreshLayout;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorHomeFragment extends BaseFragment implements HomeView, LoadMoreListView.OnLoadMoreListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener,NineGridlayout.OnItemClickListerner {

    @Bind(R.id.bar_home_address)
    TextView barHomeAddress;
    @Bind(R.id.bar_home_demand)
    TextView barHomeDemand;

    private int mCurrentPage=0;
    private String keywors="1";
    private View mHeaderView;
    private RollPagerView mRollPagerView;
    private NineGridlayout mNineGridlayout;
    @Bind(R.id.fragment_home_list_view)
    LoadMoreListView mListView;
    @Bind(R.id.fragment_home_list_swipe_layout)
    XSwipeRefreshLayout mSwipeRefreshLayout;
    private ListViewDataAdapter<TeacherListEntity> mListViewAdapter;

    private List<SubjectEntity> subjectList;
    private HomePresenter mHomePresenter=null;

    private LocationClient mLocationClient = null;
    private BDLocationListener locationListener = new TutorBDLocationListener();
    @Override
    protected void onFirstUserVisible() {
        mCurrentPage = 1;
        mHomePresenter = new HomePresenterImpl(mContext, this);
        if (NetUtils.isNetworkConnected(mContext)) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomePresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, keywors, mCurrentPage, false);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHomePresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, keywors, mCurrentPage, false);
                }
            });
        }
        //TODO 百度定位
        mLocationClient = new LocationClient(mContext);     //声明LocationClient类
        mLocationClient.registerLocationListener(locationListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
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
        mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.tutor_home_list_header, null);
        mRollPagerView = (RollPagerView) ButterKnife.findById(mHeaderView, R.id.fragment_home_list_header_roll_view_pager);
        mNineGridlayout = (NineGridlayout) ButterKnife.findById(mHeaderView, R.id.fragment_home_list_header_nine_grid_layout);
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

        if (mListView.getHeaderViewsCount() == 0)
            mListView.addHeaderView(mHeaderView);
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnLoadMoreListener(this);
        mNineGridlayout.setOnItemClickListerner(this);

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);


        barHomeDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(TutorClassMeetingActivity.class);
/*                new MaterialDialog.Builder(getActivity())
                        .title(R.string.title)
                        .content(R.string.bar_home_demand)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .show();*/
            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tutor_home;
    }

    @Subscribe
    @Override
    protected void onEventComming(EventCenter eventCenter) {
        //showToast("科目ID："+eventCenter.getData());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    /**
     * 刷新首页数据
     * @param data
     */
    @Override
    public void refreshListData(Home data) {
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
            if (data.getAdvertisementList().size() >= 2) {
                mRollPagerView.setAdapter(new CarouselAdapter(mContext, data.getAdvertisementList()));
            }
            if (data.getSubjectList().size() >= 2) {
                subjectList=new ArrayList<SubjectEntity>();
                subjectList.addAll(data.getSubjectList());
                mNineGridlayout.setAdapter(new RecommendCoursesAdapter(mContext, data.getSubjectList(), 5));
            }
            if (data.getTotalPage() > mCurrentPage)
                mListView.setCanLoadMore(true);
            else
                mListView.setCanLoadMore(false);
        }
    }

    /**
     * 添加数据到适配器中
     * @param data
     */
    @Override
    public void addMoreListData(Home data) {
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

    /**
     * 点击老师
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListViewAdapter != null) {
            int j = position + -1;
            if (j >= 0 && j < mListViewAdapter.getDataList().size())
                mHomePresenter.onItemClickListener(j, (TeacherListEntity) mListViewAdapter.getDataList().get(j));
        }
    }

    /**
     * 加载更多请求
     */
    @Override
    public void onLoadMore() {
        mCurrentPage = 1 + mCurrentPage;
        mHomePresenter.loadListData(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA, 0+"", mCurrentPage, true);
    }

    /**
     * 刷新请求
     */
    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        mHomePresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, 0+"", mCurrentPage, true);
    }

    /**
     * 老师详情页跳转  TutorTeacherDetail
     * @param position
     * @param itemData
     */
    @Override
    public void navigateToNewsDetail(int position, TeacherListEntity itemData) {
        Bundle bundle=new Bundle();
        bundle.putInt("teacherID",itemData.getId());
        readyGo(TutorTeacherDetailActivity.class,bundle);
/*        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.realtabcontent,  new TutorTeacherDetail());
        transaction.addToBackStack("home");
        transaction.commit();*/
    }

    /**
     * 跳转至筛选界面  根据科目
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        BusHelper.post(new EventCenter(1,subjectList.get(position)));
    }

    /**
     * 初始化定位SDK
     */
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span*60);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 定位监听器
     */
    public class TutorBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            switch (location.getLocType()){
                case 61:// 61 ： GPS定位结果，GPS定位成功。
                    barHomeAddress.setText("  "+location.getCity()+location.getDistrict());
                    mHomePresenter.requestBDLocation("userId",location.getLatitude(),location.getLongitude());
                    break;
                case 62:
                    showToast("无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。");
                    break;
                case 63:
                    showToast("网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。");
                    break;
                case 65:
                    showToast("定位缓存的结果。");
                    break;
                case 66://66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
                    break;
                case 67://67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
                    break;
                case 68://68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
                    break;
                case 161:// 161： 网络定位结果，网络定位定位成功。
                    if (barHomeAddress!=null&&location.getCity()!=null&&location.getDistrict()!=null){
                        barHomeAddress.setText("  "+location.getCity()+location.getDistrict());
                        mHomePresenter.requestBDLocation("userId",location.getLatitude(),location.getLongitude());
                    }
                    break;
                case 162:
                    showToast("请求串密文解析失败。");
                    break;
                case 167:
                    showToast("服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。");
                    break;
                case 502:
                    showToast("key参数错误，请按照说明文档重新申请KEY。");
                    break;
                case 505:
                    showToast("key不存在或者非法，请按照说明文档重新申请KEY。");
                    break;
                case 601:
                    showToast("key服务被开发者自己禁用，请按照说明文档重新申请KEY。");
                    break;
                case 602://602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
                    showToast("key mcode不匹配");
                    break;
                default://501～700：key验证失败，请按照说明文档重新申请KEY。
                    showToast("定位失败");
                    break;

            }

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());

            TLog.i("BaiduLocationApi", sb.toString());
        }
    }
}
