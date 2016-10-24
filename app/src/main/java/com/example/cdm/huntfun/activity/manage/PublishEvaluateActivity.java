package com.example.cdm.huntfun.activity.manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.pojo.Evaluate;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.ListViewForScrollView;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.ViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PublishEvaluateActivity extends AppCompatActivity {

    @InjectView(R.id.tv_shu)
    TextView tvShu;
    @InjectView(R.id.tv_lable)
    TextView tvLable;
    @InjectView(R.id.tv_theme)
    TextView tvTheme;
    @InjectView(R.id.top)
    RelativeLayout top;
    @InjectView(R.id.line_below_top)
    TextView lineBelowTop;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_time_detail)
    TextView tvTimeDetail;
    @InjectView(R.id.tv_cost)
    TextView tvCost;
    @InjectView(R.id.tv_cost_detail)
    TextView tvCostDetail;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.tv_num_detail)
    TextView tvNumDetail;
    @InjectView(R.id.rl_1)
    RelativeLayout rl1;
    @InjectView(R.id.iv_redqi)
    ImageView ivRedqi;
    @InjectView(R.id.tv_jihe)
    TextView tvJihe;
    @InjectView(R.id.tv_gather)
    TextView tvGather;
    @InjectView(R.id.iv_didian)
    ImageView ivDidian;
    @InjectView(R.id.tv_didian)
    TextView tvDidian;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.ll_2)
    LinearLayout ll2;
    @InjectView(R.id.act_fm)
    ImageView actFm;
    @InjectView(R.id.tv_desc)
    TextView tvDesc;
    @InjectView(R.id.tv_detail)
    TextView tvDetail;
    @InjectView(R.id.tv_care)
    TextView tvCare;
    @InjectView(R.id.tv_care_detail)
    TextView tvCareDetail;
    @InjectView(R.id.rl_3)
    RelativeLayout rl3;
    @InjectView(R.id.head)
    ImageView head;
    @InjectView(R.id.user_name)
    TextView userName;
    @InjectView(R.id.rl_4)
    RelativeLayout rl4;
    @InjectView(R.id.d1)
    ImageView d1;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.tv_trip)
    TextView tvTrip;
    @InjectView(R.id.bottom)
    RelativeLayout bottom;
    @InjectView(R.id.tv_exit)
    TextView tvExit;
    @InjectView(R.id.rl_eva)
    RelativeLayout rlEva;
    @InjectView(R.id.line_eva)
    TextView lineEva;
    @InjectView(R.id.lv_eva)
    ListViewForScrollView lvEva;
    @InjectView(R.id.scrollview)
    ScrollView scrollview;

    int pageNo = 1;
    int pageSize = 4;


    String resultPage="";
    private Boolean flag=true;

    CommonAdapter<Evaluate> evaluateyAdapter;
    List<Evaluate> evaluates=new ArrayList<>();
    List<Evaluate> newEvaluate=new ArrayList<Evaluate>();

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_evaluate);
        ButterKnife.inject(this);
        scrollview.smoothScrollTo(0,0);
        initData();
        getData();
    }

    public void initData() {
        Intent intent = getIntent();
        activity = intent.getParcelableExtra("activityInfo");
        System.out.println("initData+===" + activity);
        if (activity != null) {
            tvLable.setText(activity.getActivityLable());
            tvTheme.setText(activity.getActivityTheme());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
            tvTimeDetail.setText(sdf.format(activity.getActivityBeginTime()) + "——" + sdf.format(activity.getActivityEndTime()));
            String cost = String.valueOf(activity.getActivityCost());
            tvCostDetail.setText(cost.equals("0") ? "免费" : cost);
            String maxPeople = String.valueOf(activity.getActivityMaxPeopleNumber());
            tvNumDetail.setText(maxPeople.equals("0") ? "不限" : maxPeople);
            tvGather.setText(activity.getGather());
            tvAddress.setText(activity.getActivityAddress());

            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setSize(DensityUtil.dip2px(360), DensityUtil.dip2px(180))//图片大小
                    .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                    .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                    .setUseMemCache(true)//设置使用缓存
                    .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
                    .build();
            x.image().bind(actFm, NetUtil.url + activity.getActivityImgurl(), imageOptions);

            tvDetail.setText(activity.getActivityDesc());

            tvCare.setText(activity.getActivityCare());
        }
    }

    public void getData() {
        RequestParams requestParams = new RequestParams(NetUtil.url + "QueryEvalateServlet");

        requestParams.addQueryStringParameter("activityId", activity.getActivityId() + "");
        requestParams.addQueryStringParameter("pageNo", pageNo + "");
        requestParams.addQueryStringParameter("pageSize", pageSize + "");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("onSucess"+result.trim());
                if (result.trim().equals("false")){
                    resultPage=result.trim();
                    System.out.println("已加载全部数据:"+resultPage);
                }
                if (!result.trim().equals("false")) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Evaluate>>() {
                    }.getType();
                    //List<Activity> newActivity=new ArrayList<Activity>();
                    newEvaluate = gson.fromJson(result, type);
                    if (flag) {
                        evaluates.addAll(newEvaluate);
                    } else {
                        evaluates.clear();
                        evaluates.addAll(newEvaluate);
                    }

                    if (evaluateyAdapter == null) {

                        System.out.println("ok");
                        evaluateyAdapter=new CommonAdapter<Evaluate>(getApplicationContext(),evaluates,R.layout.evaluate_item) {
                            @Override
                            public void convert(ViewHolder viewHolder, Evaluate evaluate, int position) {
                                TextView tv = viewHolder.getViewById(R.id.tv_name);
                                tv.setText(evaluate.getUser().getUserName());
                                TextView tv_content = viewHolder.getViewById(R.id.tv_content);
                                tv_content.setText(evaluate.getEvaluateContent());

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                                TextView tv_time = viewHolder.getViewById(R.id.tv_time);
                                String creatTime = sdf.format(evaluate.getCreatTime());
                                tv_time.setText(creatTime);
                            }
                        };
                        lvEva.setAdapter(evaluateyAdapter);
                    } else {
                        evaluateyAdapter.notifyDataSetChanged();
                    }
                }
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
}
