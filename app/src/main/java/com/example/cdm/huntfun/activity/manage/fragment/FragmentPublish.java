package com.example.cdm.huntfun.activity.manage.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.manage.PublishEvaluateActivity;
import com.example.cdm.huntfun.activity.manage.PublishExitActivity;
import com.example.cdm.huntfun.fragment.BaseFragment;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.ViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdm on 2016/10/19.
 */
public class FragmentPublish extends BaseFragment {

    int userId=2;
    int orderFlag=0;
    int pageNo=1;
    int pageSize=4;
    int clazz=2;//获取发布的活动标志

    CommonAdapter<Activity> activityAdapter;
    List<Activity> activities=new ArrayList<>();
    List<Activity> newActivity=new ArrayList<Activity>();
    List<String> popContents=new ArrayList<String>();

    private PullToRefreshListView lvJoinAct;
    private TextView pop;


    private int lastItem;
    private Boolean flag=true;
    private ProgressBar progressbar;

    String resultPage="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish, null);
        lvJoinAct = ((PullToRefreshListView) view.findViewById(R.id.lv_join_act));
        pop = ((TextView) view.findViewById(R.id.id_prod_list_sort_right));

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });

        progressbar = ((ProgressBar) view.findViewById(R.id.progressbar));

        lvJoinAct.setMode(PullToRefreshBase.Mode.PULL_FROM_END);

        lvJoinAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer state=activities.get(position-1).getStateId();
                if (state==1){
                    Intent intent=new Intent(getActivity(),PublishExitActivity.class);
                    System.out.println("onclick==="+position);
                    intent.putExtra("activityInfo",activities.get(position-1));
                    startActivityForResult(intent,1);
                }else {
                    Intent intent = new Intent(getActivity(), PublishEvaluateActivity.class);
                    System.out.println("onclick===" + position);
                    intent.putExtra("activityInfo", activities.get(position - 1));
                    startActivityForResult(intent, 1);
                    //startActivity(intent);
                }
            }
        });

        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {
        lvJoinAct.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!resultPage.equals("false")) {
                    pageNo++;
                    System.out.println("==========" + pageNo);
                    getData();
                    lvJoinAct.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            lvJoinAct.onRefreshComplete();
                        }
                    }, 1000);
                }
                if (resultPage.equals("false")||newActivity.size()<4) {
                    Toast.makeText(getActivity(),"已加载全部数据",Toast.LENGTH_SHORT).show();
                    lvJoinAct.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            lvJoinAct.onRefreshComplete();
                        }
                    }, 1000);
                }
            }
        });

    }

    @Override
    public void initData() {
        getData();
        popContents.add("未进行");
        popContents.add("进行中");
        popContents.add("已结束");
    }

    public void getData(){
        progressbar.setVisibility(View.VISIBLE);
        String url= NetUtil.url+"QueryActivityServlet";//访问网络的url
        RequestParams requestParams=new RequestParams(url);
        requestParams.addQueryStringParameter("userId",String.valueOf(userId));
        requestParams.addQueryStringParameter("orderFlag",orderFlag+"");//排序标记
        requestParams.addQueryStringParameter("pageNo",pageNo+"");
        requestParams.addQueryStringParameter("pageSize",pageSize+"");
        requestParams.addQueryStringParameter("clazz",clazz+"");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("success"+result.trim());
                //json转换成List<Product>
                if (result.trim().equals("false")){
                    resultPage=result.trim();
                    System.out.println("已加载全部数据:"+resultPage);
                }
                if (!result.trim().equals("false")) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Activity>>() {
                    }.getType();
                    //List<Activity> newActivity=new ArrayList<Activity>();
                    newActivity = gson.fromJson(result, type);
                    if (flag) {
                        activities.addAll(newActivity);
                    } else {
                        activities.clear();
                        activities.addAll(newActivity);
                    }

                    if (activityAdapter == null) {
                        activityAdapter = new CommonAdapter<Activity>(getActivity(), activities, R.layout.fragment_join_item) {
                            @Override
                            public void convert(ViewHolder viewHolder, Activity activity, int position) {
                                TextView tv = viewHolder.getViewById(R.id.activity_theme);
                                tv.setText(activity.getActivityTheme());

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                                TextView tv_time = viewHolder.getViewById(R.id.tv_time);
                                String begStr = sdf.format(activity.getActivityBeginTime());
                                String endStr = sdf.format(activity.getActivityEndTime());
                                tv_time.setText("时间：" + begStr + "-" + endStr);

                                TextView tv_address = viewHolder.getViewById(R.id.tv_address);
                                tv_address.setText("地点：" + activity.getActivityAddress());

                                ImageView act_fm = viewHolder.getViewById(R.id.act_fm);
                                ImageOptions imageOptions = new ImageOptions.Builder()
                                        .setSize(DensityUtil.dip2px(360), DensityUtil.dip2px(180))//图片大小
                                        .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                                        .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                                        .setUseMemCache(true)//设置使用缓存
                                        .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
                                        .build();
                                x.image().bind(act_fm, NetUtil.url + activity.getActivityImgurl(), imageOptions);

                                TextView tv_state = viewHolder.getViewById(R.id.tv_state);
                                String state = String.valueOf(activity.getStateId());
                                if (state.equals("1")) {
                                    tv_state.setText("活动未开始");
                                    tv_state.setBackgroundResource(R.color.activity_state_no);
                                }
                                if (state.equals("2")) {
                                    tv_state.setText("活动进行中");
                                    tv_state.setBackgroundResource(R.color.activity_state_run);
                                }
                                if (state.equals("3")) {
                                    tv_state.setText("活动已结束");
                                    tv_state.setBackgroundResource(R.color.activity_state_end);
                                }
                            }
                        };
                        lvJoinAct.setAdapter(activityAdapter);
                    } else {
                        activityAdapter.notifyDataSetChanged();
                    }
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void  initPopupWindow(View v){
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.lv_activity_state,null);
        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,300);
        //listview设置数据源
        ListView lv = ((ListView) view.findViewById(R.id.lv_activity_state));
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),R.layout.lv_activity_state_item,popContents);
        lv.setAdapter(arrayAdapter);

        //popupWindow外面点击，popupWindow消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //显示在v的下面
        popupWindow.showAsDropDown(v);

        //listview的item点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭PopupWindow
                popupWindow.dismiss();
                //排序
                if (position==0){
                    orderFlag=1;
                    flag=false;
                    pageNo=1;
                }else if (position==1){
                    orderFlag=2;
                    flag=false;
                    pageNo=1;
                }else if (position==2){
                    orderFlag=3;
                    flag=false;
                    pageNo=1;
                }
                getData();
            }
        });
    }
}
