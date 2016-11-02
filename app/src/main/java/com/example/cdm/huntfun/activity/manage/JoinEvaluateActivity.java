package com.example.cdm.huntfun.activity.manage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.manage.child.EvaluateActivity;
import com.example.cdm.huntfun.activity.manage.child.JoinersActivity;
import com.example.cdm.huntfun.activity.ownview.ListenerForScrolView;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.pojo.Evaluate;
import com.example.cdm.huntfun.pojo.User;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JoinEvaluateActivity extends AppCompatActivity {

    Activity activity;

    String evaluateContent = null;
    Integer activityId;
    Evaluate evaluate;

    Set<String> ev = new HashSet<String>();
    @InjectView(R.id.tv_shu)
    TextView tvShu;
    @InjectView(R.id.tv_lable)
    TextView tvLable;
    @InjectView(R.id.tv_theme)
    TextView tvTheme;
    @InjectView(R.id.tv_creattime)
    TextView tvCreattime;
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
    @InjectView(R.id.gv_joiner)
    GridView gvJoiner;
    @InjectView(R.id.join_num)
    TextView joinNum;
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
    @InjectView(R.id.user_phone)
    TextView userPhone;
    @InjectView(R.id.rl_4)
    RelativeLayout rl4;
    @InjectView(R.id.d1)
    ImageView d1;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.tv_trip)
    TextView tvTrip;
    @InjectView(R.id.eval)
    RelativeLayout eval;
    @InjectView(R.id.tv_exit)
    TextView tvExit;
    @InjectView(R.id.eva_more)
    ImageView evaMore;
    @InjectView(R.id.rl_eva)
    RelativeLayout rlEva;
    @InjectView(R.id.lv_eva)
    ListViewForScrollView lvEva;
    @InjectView(R.id.scrollview)
    ListenerForScrolView scrollview;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.fb_dt)
    TextView fbDt;
    @InjectView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @InjectView(R.id.bottom)
    RelativeLayout bottom;
    @InjectView(R.id.edt_eva)
    EditText edtEva;
    @InjectView(R.id.tv_evaluate_detail)
    TextView tvEvaluateDetail;
    @InjectView(R.id.bottom1)
    RelativeLayout bottom1;


    private int mLastScrollY;
    private int mScrollThreshold = 3;

    Boolean seeFlag=false;

    List<User> userList=new ArrayList<>();
    CommonAdapter<User> userCommonAdapter;

    CommonAdapter<Evaluate> evaluateyAdapter;
    List<Evaluate> evaluates = new ArrayList<>();
    List<Evaluate> newEvaluate = new ArrayList<Evaluate>();
    int pageNo = 1;
    int pageSize = 4;

    private Boolean flag = true;

    String resultPage = "";

    ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(50), DensityUtil.dip2px(50))//图片大小
            .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
            .setUseMemCache(true)//设置使用缓存
            .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_evaluate);
        ButterKnife.inject(this);
        scrollview.smoothScrollTo(0, 0);

        getData();

        scrollview.setOnScrollChangedListener(new ListenerForScrolView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                System.out.println("onScrollChanged1");
                boolean isSignificantDelta = Math.abs(t - mLastScrollY) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (t > mLastScrollY) {
                        bottom.setVisibility(View.GONE);
                        bottom1.setVisibility(View.GONE);
                    } else {
                        bottom.setVisibility(View.VISIBLE);
                        if (seeFlag) {
                            bottom1.setVisibility(View.VISIBLE);
                        }
                    }
                }
                mLastScrollY = t;
            }
        });

        initEvent();
        queryEva();
    }

    @OnClick({R.id.join_num, R.id.eva_more, R.id.iv_back, R.id.fb_dt, R.id.tv_evaluate, R.id.tv_evaluate_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.join_num:
                Intent intent=new Intent(getApplicationContext(), JoinersActivity.class);
                intent.putParcelableArrayListExtra("joiners", (ArrayList<? extends Parcelable>) userList);
                startActivity(intent);
                break;
            case R.id.eva_more:
                Intent intentEva=new Intent(getApplicationContext(), EvaluateActivity.class);
                intentEva.putExtra("activityId",activity.getActivityId());
                startActivity(intentEva);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.fb_dt:
                break;
            case R.id.tv_evaluate:
                bottom1.setVisibility(View.VISIBLE);
                seeFlag=true;
                break;
            case R.id.tv_evaluate_detail:
                System.out.println("btn");
                ev.clear();
                initData();
                ev.add(activity.getActivityId() + "," + edtEva.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("evaluate", Context.MODE_PRIVATE); //私有数据
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                //editor.putString("evaluateContent", evaluateContent);
                editor.putStringSet(activity.getActivityId() + "", ev);
                //editor.putInt("activityId", activityId);
                editor.commit();//提交修改
                Toast.makeText(getApplicationContext(),"发表成功！",Toast.LENGTH_SHORT).show();
                bottom1.setVisibility(View.GONE);
                seeFlag=false;
                break;
        }
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
                    edtEva.setText(results[1].substring(0, results[1].length() - 1));
                } else {
                    edtEva.setText(result);
                }
                tvEvaluate.setText("继续评论");
            }
        }

    }

    public void getData() {
        Intent intent = getIntent();
        activity = intent.getParcelableExtra("activityInfo");
        System.out.println("initData+===" + activity);
        if (activity != null) {
            activityId=activity.getActivityId();
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

            ImageOptions imageOptions1 = new ImageOptions.Builder()
                    .setSize(DensityUtil.dip2px(360), DensityUtil.dip2px(180))//图片大小
                    .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                    .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                    .setUseMemCache(true)//设置使用缓存
                    .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
                    .build();
            x.image().bind(actFm, NetUtil.url + activity.getActivityImgurl(), imageOptions1);

            tvDetail.setText(activity.getActivityDesc());

            tvCareDetail.setText(activity.getActivityCare());

            userName.setText(activity.getUser().getUserName());
            userPhone.setText("电话：" + activity.getUser().getPhone());
            tvTrip.setText(activity.getActivityTrip());
            joinNum.setText(activity.getJoiner().size() + "人");

            userList.addAll(activity.getJoiner());
            userCommonAdapter=new CommonAdapter<User>(getApplicationContext(),userList,R.layout.joiner_gv_item) {
                @Override
                public void convert(ViewHolder viewHolder, User user, int position)  {
                    ImageView head=viewHolder.getViewById(R.id.user_head);
                    x.image().bind(head,NetUtil.url+userList.get(position).getImageUrl(),imageOptions);
                }
            };
            gvJoiner.setAdapter(userCommonAdapter);
            x.image().bind(head,NetUtil.url+activity.getUser().getImageUrl(),imageOptions);
        }
    }

    public void initData() {
        activityId = activity.getActivityId();
        evaluateContent = edtEva.getText().toString();
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

    public void queryEva(){
        RequestParams requestParams = new RequestParams(NetUtil.url + "QueryEvalateServlet");

        requestParams.addQueryStringParameter("activityId", activity.getActivityId() + "");
        requestParams.addQueryStringParameter("pageNo", pageNo + "");
        requestParams.addQueryStringParameter("pageSize", pageSize + "");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("onSucess" + result.trim());
                if (result.trim().equals("false")) {
                    resultPage = result.trim();
                    System.out.println("已加载全部数据:" + resultPage);
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
                        evaluateyAdapter = new CommonAdapter<Evaluate>(getApplicationContext(), evaluates, R.layout.evaluate_item) {
                            @Override
                            public void convert(ViewHolder viewHolder, Evaluate evaluate, int position) {
                                TextView tv = viewHolder.getViewById(R.id.tv_name);
                                tv.setText(evaluate.getUser().getUserName());
                                ImageView head=viewHolder.getViewById(R.id.iv_head);
                                ImageOptions imageOptions = new ImageOptions.Builder()
                                        .setSize(DensityUtil.dip2px(50), DensityUtil.dip2px(50))//图片大小
                                        .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                                        .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                                        .setUseMemCache(true)//设置使用缓存
                                        .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
                                        .build();
                                x.image().bind(head, NetUtil.url + evaluate.getUser().getImageUrl(), imageOptions);

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
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
