package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.ViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Search_DetailActivity extends AppCompatActivity {
    private CommonAdapter<Activity> search_adapter;
    protected List<Activity> aList=new ArrayList<Activity>();
    protected ListView search_list;

    private Integer pageNo=null;
    private Integer pageSize=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__detail);

        initView();

        initData();

        initEvent();
    }
    public void initView(){
        search_list = ((ListView) findViewById(R.id.search_list));
    }

    public void initEvent(){
        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Search_DetailActivity.this,Detail.class);
                intent.putExtra("activity",aList.get(position));
                startActivity(intent);
            }
        });
    }

    public void initData(){
        Intent intent=getIntent();
        String query=intent.getStringExtra("search_name");
        RequestParams requestParams=new RequestParams(NetUtil.url+"GetActivityByLabel");
        try {
            requestParams.addQueryStringParameter("label",URLEncoder.encode(query,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Type type=new TypeToken<List<Activity>>(){}.getType();
                List<Activity> newList=gson.fromJson(result,type);
                Log.e("ssssss","======================================");
                aList.clear();
                aList.addAll(newList);
                if (search_adapter==null) {
                    search_adapter = new CommonAdapter<Activity>(Search_DetailActivity.this, aList, R.layout.search_list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Activity activity, int position) {
                            ImageView imageView = viewHolder.getViewById(R.id.iv_search_item);
                            x.image().bind(imageView, NetUtil.url + activity.getActivityImgurl());
                            TextView text1 = viewHolder.getViewById(R.id.tv_search_list_theme);
                            text1.setText(activity.getActivityTheme());
                            TextView text2 = viewHolder.getViewById(R.id.tv_search_list_money);
                            text2.setText("￥ "+activity.getActivityCost());

                        }
                    };
                    search_list.setAdapter(search_adapter);
                }else{
                        search_adapter.notifyDataSetChanged();
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
