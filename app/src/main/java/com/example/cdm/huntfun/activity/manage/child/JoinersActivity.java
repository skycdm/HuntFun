package com.example.cdm.huntfun.activity.manage.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.User;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.ViewHolder;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JoinersActivity extends AppCompatActivity {

    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.rl_top)
    RelativeLayout rlTop;
    @InjectView(R.id.lv_joiners)
    ListView lvJoiners;

    List<User> userList = new ArrayList<>();
    CommonAdapter<User> userCommonAdapter;
    @InjectView(R.id.line)
    TextView line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joiners);
        ButterKnife.inject(this);
        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        userList = intent.getParcelableArrayListExtra("joiners");
        System.out.println("===" + userList);
        if (userList != null) {
            userCommonAdapter = new CommonAdapter<User>(getApplicationContext(), userList, R.layout.activity_joiners_item) {
                @Override
                public void convert(ViewHolder viewHolder, User user, int position) {
                    TextView name = viewHolder.getViewById(R.id.joiner_name);
                    name.setText(user.getUserName());
                    TextView phone = viewHolder.getViewById(R.id.joiner_phone);
                    phone.setText("电话：" + user.getPhone());
                    ImageView head = viewHolder.getViewById(R.id.joiner_head);
                    ImageOptions imageOptions = new ImageOptions.Builder()
                            .setSize(DensityUtil.dip2px(50), DensityUtil.dip2px(50))//图片大小
                            .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                            .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                            .setUseMemCache(true)//设置使用缓存
                            .setFailureDrawableId(R.drawable.activity_fm)//加载失败后默认显示图片
                            .build();
                    x.image().bind(head, NetUtil.url + user.getImageUrl(), imageOptions);
                }
            };
            lvJoiners.setAdapter(userCommonAdapter);
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
