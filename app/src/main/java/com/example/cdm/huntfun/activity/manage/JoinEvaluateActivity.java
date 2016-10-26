package com.example.cdm.huntfun.activity.manage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.pojo.Evaluate;
import com.example.cdm.huntfun.pojo.User;
import com.example.cdm.huntfun.util.NetUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JoinEvaluateActivity extends AppCompatActivity {

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
    @InjectView(R.id.scrollview)
    ScrollView scrollview;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.edt_evaluate)
    EditText edtEvaluate;
    @InjectView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @InjectView(R.id.bottom)
    RelativeLayout bottom;
    @InjectView(R.id.iv_back2)
    ImageView ivBack2;
    @InjectView(R.id.edt_evaluate2)
    TextView edtEvaluate2;
    @InjectView(R.id.tv_evaluate2)
    TextView tvEvaluate2;
    @InjectView(R.id.bottom2)
    RelativeLayout bottom2;

    Activity activity;

    String evaluateContent = null;
    Integer activityId;
    Evaluate evaluate;

    Set<String> ev = new HashSet<String>();
    @InjectView(R.id.join_num)
    TextView joinNum;
    @InjectView(R.id.user_phone)
    TextView userPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_evaluate);
        ButterKnife.inject(this);

        getData();
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {

                    bottom.setVisibility(View.GONE);

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    bottom.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        initEvent();
    }

    public void initEvent() {
        SharedPreferences share = getSharedPreferences("evaluate", Context.MODE_PRIVATE);
        //ev.add("false,null");
        Set<String> resultSet = share.getStringSet(activity.getActivityId() + "", ev);
        if (!resultSet.toString().equals("[]")) {
            System.out.println(activity.getActivityId() + "===" + resultSet);
            String[] results = resultSet.toString().split(",", 2);
            System.out.println(results[0] + "+++" + results[1]);
            //String id=String.valueOf(activity.getActivityId());
            if (("[" + activity.getActivityId()).equals(results[0])) {
                System.out.println("asdasda");
                String result = results[1];
                if (result.charAt(result.length() - 1) == ']') {
                    edtEvaluate.setText(results[1].substring(0, results[1].length() - 1));
                } else {
                    edtEvaluate.setText(result);
                }
                tvEvaluate.setText("已评论");
                tvEvaluate.setEnabled(false);
                edtEvaluate.setFocusable(false);
            }
        }

    }

    public void getData() {
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
            joinNum.setText(activity.getJoiner().size() + "人");
        }
    }

    public void initData() {
        activityId = activity.getActivityId();
        evaluateContent = edtEvaluate.getText().toString();
        evaluate = new Evaluate(new Activity(activity.getActivityId()), new User(2), new Timestamp(System.currentTimeMillis()), evaluateContent);
        if (evaluate != null) {
            RequestParams requestParams = new RequestParams(NetUtil.url + "InsertEvalateServlet");
            Gson gson = new Gson();
            String evaluateInfo = gson.toJson(evaluate);
            requestParams.addBodyParameter("evaluateInfo", evaluateInfo);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("onsucess");
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

    @OnClick(R.id.tv_evaluate)
    public void onClick() {
        System.out.println("btn");
        ev.clear();
        //initData();
        ev.add(activity.getActivityId() + "," + edtEvaluate.getText().toString());
        SharedPreferences sharedPreferences = getSharedPreferences("evaluate", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        //editor.putString("evaluateContent", evaluateContent);
        editor.putStringSet(activity.getActivityId() + "", ev);
        //editor.putInt("activityId", activityId);
        editor.commit();//提交修改
    }
}
