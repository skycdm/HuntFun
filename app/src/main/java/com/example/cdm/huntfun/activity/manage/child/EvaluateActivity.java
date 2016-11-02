package com.example.cdm.huntfun.activity.manage.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Evaluate;
import com.example.cdm.huntfun.pojo.User;
import com.example.cdm.huntfun.util.CommonAdapter;
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
import butterknife.OnClick;

public class EvaluateActivity extends AppCompatActivity {

    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.rl_top)
    RelativeLayout rlTop;
    @InjectView(R.id.line)
    TextView line;
    @InjectView(R.id.lv_evaluates)
    ListView lvEvaluates;

    CommonAdapter<Evaluate> evaluateyAdapter;
    List<Evaluate> evaluates = new ArrayList<>();
    List<Evaluate> newEvaluate = new ArrayList<Evaluate>();

    List<User> userList = new ArrayList<>();
    CommonAdapter<User> userCommonAdapter;

    int pageNo = 1;
    int pageSize = 4;
    int getActivityId;
    String resultPage = "";

    private Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.inject(this);
        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        getActivityId = intent.getIntExtra("activityId", 0);
        RequestParams requestParams = new RequestParams(NetUtil.url + "QueryEvalateServlet");

        requestParams.addQueryStringParameter("activityId", getActivityId + "");
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
                        evaluateyAdapter = new CommonAdapter<Evaluate>(getApplicationContext(), evaluates, R.layout.activity_evaluates_item) {
                            @Override
                            public void convert(ViewHolder viewHolder, Evaluate evaluate, int position) {
                                TextView tv = viewHolder.getViewById(R.id.evaluater_name);
                                tv.setText(evaluate.getUser().getUserName());
                                TextView phone = viewHolder.getViewById(R.id.joiner_phone);
                                phone.setText(evaluate.getUser().getPhone());
                                ImageView head = viewHolder.getViewById(R.id.evaluater_head);
                                ImageOptions imageOptions = new ImageOptions.Builder()
                                        .setSize(DensityUtil.dip2px(50), DensityUtil.dip2px(50))//图片大小
                                        .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                                        .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                                        .setUseMemCache(true)//设置使用缓存
                                        .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
                                        .build();
                                x.image().bind(head, NetUtil.url + evaluate.getUser().getImageUrl(), imageOptions);

                                TextView tv_content = viewHolder.getViewById(R.id.eva_content_detail);
                                tv_content.setText(evaluate.getEvaluateContent());
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                                TextView tv_time = viewHolder.getViewById(R.id.create_time);
                                String creatTime = sdf.format(evaluate.getCreatTime());
                                tv_time.setText(creatTime);
                            }
                        };
                        lvEvaluates.setAdapter(evaluateyAdapter);
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

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
