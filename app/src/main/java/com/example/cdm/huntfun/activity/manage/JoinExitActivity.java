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
import com.example.cdm.huntfun.util.NetUtil;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JoinExitActivity extends AppCompatActivity {

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
    @InjectView(R.id.scrollview)
    ScrollView scrollview;

    Activity activity;
    @InjectView(R.id.user_phone)
    TextView userPhone;
    @InjectView(R.id.join_num)
    TextView joinNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_exit);
        ButterKnife.inject(this);
        initData();
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

            userName.setText(activity.getUser().getUserName());
            userPhone.setText("电话：" + activity.getUser().getPhone());
            tvTrip.setText(activity.getActivityTrip());
            joinNum.setText(activity.getJoiner().size()+"人");

        }
    }

    @OnClick(R.id.tv_exit)
    public void onClick() {
        System.out.println("qqqqqqqqqqqqq");
        RequestParams requestParams = new RequestParams(NetUtil.url + "DeleteJoinServlet");

        requestParams.addQueryStringParameter("activityId", activity.getActivityId() + "");
        requestParams.addQueryStringParameter("userId", 4 + "");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("onsucsss" + result.trim());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println("error" + ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                System.out.println("finished");
            }
        });
    }
}
