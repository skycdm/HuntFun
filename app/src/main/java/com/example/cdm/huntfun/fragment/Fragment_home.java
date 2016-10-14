package com.example.cdm.huntfun.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.application.HVListview;
import com.example.cdm.huntfun.next.Detail;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.ViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_home extends Fragment {

    protected RollPagerView mRollViewPager=null;
    private ListView lv_act;
    private BaseAdapter adapter=null;
    private View view=null;
    protected HVListview hv_list;
    private CommonAdapter<Activity> acAdapter;
    final List<Activity> aList=new ArrayList<Activity>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);
        PictureRoll();
        initView();
        initEvent();
        initData();
        return view;
    }
    public void initView(){
        hv_list = ((HVListview) view.findViewById(R.id.hv_list));
    }
    public void initEvent(){
        hv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), Detail.class);
                intent.putExtra("activity",aList.get(position));
                startActivity(intent);
            }
        });
    }


    public void initData(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"get_activity");
        requestParams.addQueryStringParameter("is_classify",1+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Type type=new TypeToken<List<Activity>>(){}.getType();
                List<Activity> newList=new ArrayList<Activity>();
                newList=gson.fromJson(result,type);
                aList.clear();
                aList.addAll(newList);

                if(acAdapter==null){
                    acAdapter=new CommonAdapter<Activity>(getActivity(),aList,R.layout.hv_list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Activity activity, int position) {
                            ImageView iv_hv_list=viewHolder.getViewById(R.id.iv_hv_list);

                            x.image().bind(iv_hv_list,NetUtil.url+activity.getActivityImgurl());

                            Log.e("ssss",NetUtil.url+activity.getActivityImgurl());

                            TextView textView=viewHolder.getViewById(R.id.tv_hv_list_content);
                            textView.setText(activity.getActivityTheme());
                            TextView textView1=viewHolder.getViewById(R.id.tv_hv_list_money);
                            textView1.setText(activity.getActivityCost()+"￥");
                        }
                    };
                    hv_list.setAdapter(acAdapter);
                }else {
                    acAdapter.notifyDataSetChanged();
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






    public void PictureRoll(){
        mRollViewPager = (RollPagerView)view.findViewById(R.id.roll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

        // Toolbar

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
//        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        toolbar.setTitle("");//设置主标题
        //  toolbar.setSubtitle("Subtitle");//设置子标题


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(getActivity(), "menu_search", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(getActivity(), "menu_notifications ", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(getActivity(), "item_01 ", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(getActivity(), "item_02 ", Toast.LENGTH_SHORT).show();

                }
                return true;
            }

        });
    }
    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.first,
                R.drawable.second,
                R.drawable.three,
                R.drawable.zz,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}