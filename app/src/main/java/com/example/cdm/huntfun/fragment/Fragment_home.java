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
import com.example.cdm.huntfun.next.MoreActivity;
import com.example.cdm.huntfun.next.SearchActivity;
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
    private CommonAdapter<Activity> acAdapter1;
    private CommonAdapter<Activity> acAdapter2;
    private List<Activity> aList=new ArrayList<Activity>();
    private List<Activity> aList1=new ArrayList<Activity>();
    private List<Activity> aList2=new ArrayList<Activity>();
    protected ImageView iv_search;
    protected HVListview hv_list1;
    protected HVListview hv_list2;
    private int pageNo=1;
    private int pageSize=6;
    private int is_classify=1;
    private int is_classify1=2;
    private int is_classify2=3;
    protected TextView tv_more1;
    protected TextView tv_more2;
    protected TextView tv_more3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);


        PictureRoll();
        initView();
        initEvent();
        initData();
        initData1();
        initData2();
        return view;
    }

    public void initView(){
        hv_list = ((HVListview) view.findViewById(R.id.hv_list));
        hv_list1 = ((HVListview) view.findViewById(R.id.hv_list1));
        hv_list2 = ((HVListview) view.findViewById(R.id.hv_list2));
        iv_search = ((ImageView) view.findViewById(R.id.iv_search));
        tv_more1 = ((TextView) view.findViewById(R.id.tv_more1));
        tv_more2 = ((TextView) view.findViewById(R.id.tv_more2));
        tv_more3 = ((TextView) view.findViewById(R.id.tv_more3));

    }
    public void initEvent(){
        tv_more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MoreActivity.class);
                intent.putExtra("is_classify",is_classify);
                startActivity(intent);
            }
        });
        tv_more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MoreActivity.class);
                intent.putExtra("is_classify",is_classify1);
                startActivity(intent);
            }
        });
        tv_more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MoreActivity.class);
                intent.putExtra("is_classify",is_classify2);
                startActivity(intent);
            }
        });
        hv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), Detail.class);
                intent.putExtra("activity",aList.get(position));
                startActivity(intent);
            }
        });
        hv_list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), Detail.class);
                intent.putExtra("activity",aList2.get(position));
                startActivity(intent);
            }
        });
        hv_list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), Detail.class);
                intent.putExtra("activity",aList1.get(position));
                startActivity(intent);
            }
        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    public void initData(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"GetActivityByClassfy");
        requestParams.addQueryStringParameter("is_classify",is_classify+"");
        requestParams.addQueryStringParameter("pageNo",pageNo+"");
        requestParams.addQueryStringParameter("pageSize",pageSize+"");
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
                    acAdapter=new CommonAdapter<Activity>(getActivity(),aList, R.layout.hv_list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Activity activity, int position) {
                            ImageView iv_hv_list=viewHolder.getViewById(R.id.iv_hv_list);
                            x.image().bind(iv_hv_list, NetUtil.url+activity.getActivityImgurl());
                            Log.e("ssss", NetUtil.url+activity.getActivityImgurl());
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

    public void initData1(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"GetActivityByClassfy");
        requestParams.addQueryStringParameter("is_classify",is_classify1+"");
        requestParams.addQueryStringParameter("pageNo",pageNo+"");
        requestParams.addQueryStringParameter("pageSize",pageSize+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Type type=new TypeToken<List<Activity>>(){}.getType();
                List<Activity> newList1=new ArrayList<Activity>();
                newList1=gson.fromJson(result,type);
                aList1.clear();
                aList1.addAll(newList1);

                if(acAdapter1==null){
                    acAdapter1=new CommonAdapter<Activity>(getActivity(),aList1, R.layout.hv_list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Activity activity, int position) {
                            ImageView iv_hv_list=viewHolder.getViewById(R.id.iv_hv_list);

                            x.image().bind(iv_hv_list, NetUtil.url+activity.getActivityImgurl());
                            Log.e("ssss", NetUtil.url+activity.getActivityImgurl());
                            TextView textView=viewHolder.getViewById(R.id.tv_hv_list_content);
                            textView.setText(activity.getActivityTheme());
                            TextView textView1=viewHolder.getViewById(R.id.tv_hv_list_money);
                            textView1.setText(activity.getActivityCost()+"￥");
                        }
                    };
                    hv_list1.setAdapter(acAdapter1);
                }else {
                    acAdapter1.notifyDataSetChanged();
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

    public void initData2(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"GetActivityByClassfy");
        requestParams.addQueryStringParameter("is_classify",is_classify2+"");
        requestParams.addQueryStringParameter("pageNo",pageNo+"");
        requestParams.addQueryStringParameter("pageSize",pageSize+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Type type=new TypeToken<List<Activity>>(){}.getType();
                List<Activity> newList2=new ArrayList<Activity>();
                newList2=gson.fromJson(result,type);
                aList2.clear();
                aList2.addAll(newList2);

                if(acAdapter2==null){
                    acAdapter2=new CommonAdapter<Activity>(getActivity(),aList2, R.layout.hv_list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Activity activity, int position) {
                            ImageView iv_hv_list=viewHolder.getViewById(R.id.iv_hv_list);

                            x.image().bind(iv_hv_list, NetUtil.url+activity.getActivityImgurl());
                            Log.e("ssss", NetUtil.url+activity.getActivityImgurl());
                            TextView textView=viewHolder.getViewById(R.id.tv_hv_list_content);
                            textView.setText(activity.getActivityTheme());
                            TextView textView1=viewHolder.getViewById(R.id.tv_hv_list_money);
                            textView1.setText(activity.getActivityCost()+"￥");
                        }
                    };
                    hv_list2.setAdapter(acAdapter2);
                }else {
                    acAdapter2.notifyDataSetChanged();
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



// 图片轮播

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
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.BLUE,Color.WHITE));
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