package com.example.cdm.huntfun.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.MineActivity.RefreshableView;
import com.example.cdm.huntfun.pojo.Constent;
import com.example.cdm.huntfun.pojo.Flage;
import com.example.cdm.huntfun.pojo.Sport_key;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/** 我收藏的活动
 * Created by lian on 2016/9/19.
 */
public class Fragment_sport extends Fragment  implements AbsListView.OnScrollListener {
    final List<Sport_key.Sport> sportList = new ArrayList<Sport_key.Sport>();

    final List<Sport_key.Sport> sports = new ArrayList<>();//预约
    int page=1;
    int sign=1;//设置刷新时不触发点击事件标志
    private ListView my_sport;
    BaseAdapter adapter;
    Sport_key sport;
    private RefreshableView refresh;
    private int lastIndex;
    private Button bt;
    private ProgressBar pg;
    // 设置一个最大的数据条数，超过即不再加载
    Handler handler1,handler2;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sport,null);
        my_sport = ((ListView) view.findViewById(R.id.my_sport));
        refresh = ((RefreshableView) view.findViewById(R.id.refreshable_view));

        View moreView = inflater.inflate(R.layout.moredata, null);
        bt = (Button) moreView.findViewById(R.id.bt_load);//加载更多数据
        pg = (ProgressBar) moreView.findViewById(R.id.pg);//下拉加载更多数据

        click();

        initDate(moreView);//开始加载界面数据

        return view;
    }

    private void initDate(View moreView) {
        setadapter();
        my_sport.addFooterView(moreView);
        my_sport.setAdapter(adapter);
        geconnection(page);
    }

    private void click() {
        //添加下拉刷新事件

        my_sport.setOnScrollListener(this);


        //上拉刷新

        refresh.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                sign=0;
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        for (int i=0;i<sportList.size();i++){
//                            sportList.removeAll(sportList);
//                        }
                        sportList.clear();
                        page = 1;
                        setadapter();
                        geconnection(page);
                        my_sport.setAdapter(adapter);
                        refresh.finishRefreshing();
                        sign=1;
                    }
                },3000);
            }
        },0);


        //加载更多数据
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"加载数据",Toast.LENGTH_LONG).show();
                pg.setVisibility(View.VISIBLE);// 将进度条可见
                bt.setVisibility(View.GONE);// 按钮不可见

                handler1 = new Handler();

                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreDate();// 加载更多数据
                        bt.setVisibility(View.VISIBLE);
                        pg.setVisibility(View.GONE);
//                        adapter.notifyDataSetChanged();
                    }
                },2000);

            }
        });
        //点击获取item的数据

//        my_sport.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            private TextView sportId;
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (sign != 0) {
//                    sportId = ((TextView) view.findViewById(R.id.sportId));
//
//                    final String spotid = sportId.getText().toString();
//
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
//                    dialog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            delect(spotid);
//                        }
//                    });
//                    dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    dialog.show();
//                }
//                return false;
//            }
//        });

    }
    private void delect(String spotid) {
        Flage.FLAG = 0;
        RequestParams rp = new RequestParams(Constent.URL+"AppSerlet/delectCollection?sportId="+spotid+"&flag="+ Flage.FLAG);
        x.http().get(rp, new Callback.CacheCallback<String>() {

            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                sportList.clear();
                page = 1;
                setadapter();
                geconnection(page);
                my_sport.setAdapter(adapter);
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

    private void setadapter() {
        adapter = new BaseAdapter() {
            private Button change;
            private Button delect;
            private TextView miaoshu;
            private TextView money;
            private TextView sportId;
            private ImageView img;
            private TextView sportName;
            private TextView Uname;
            private TextView timeEnd;
            private TextView timeBegin;
            private TextView place;
            private TextView num;

            @Override
            public int getCount() {
                return sportList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                View view1 =View.inflate(getActivity(), R.layout.listview_sport_layout,null);

                Uname = ((TextView) view1.findViewById(R.id.Uname));
                sportName = ((TextView) view1.findViewById(R.id.sportName));
                timeEnd = ((TextView) view1.findViewById(R.id.timeEnd));
                timeBegin = ((TextView) view1.findViewById(R.id.timeBegin));
                place = ((TextView) view1.findViewById(R.id.place));
                num = ((TextView) view1.findViewById(R.id.num));
                img = ((ImageView) view1.findViewById(R.id.listview_image));
                sportId = ((TextView) view1.findViewById(R.id.sportId));
                money = ((TextView) view1.findViewById(R.id.money));
                miaoshu = ((TextView) view1.findViewById(R.id.miaoshu));
                miaoshu.setVisibility(View.GONE);
                delect = ((Button) view1.findViewById(R.id.delect));
                change = ((Button) view1.findViewById(R.id.change));
                change.setText("删除");
                delect.setText("预约");

                final Sport_key.Sport sport = sportList.get(position);
                String imgs=sport.img;
                x.image().bind(img, Constent.URL+"ww/imgs/" + imgs + "");
//                thread(img,imgs);
                try {
                    Uname.setText("发布人："+ URLDecoder.decode(sport.uname,"utf-8"));
                    sportName.setText("活动名称："+URLDecoder.decode(sport.sportName,"utf-8"));
                    place.setText("地点："+URLDecoder.decode(sport.place,"utf-8"));
//                    miaoshu.setText(URLDecoder.decode(sport.theme,"utf-8"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                sportId.setText(sport.id+"");

                timeBegin.setText("开始时间："+sport.timeBegin);
                timeEnd.setText("结束时间："+sport.timeEnd);
                num.setText("人数："+sport.num);
                money.setText("￥"+sport.money+"");

                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delect(String.valueOf(sport.id));
                    }
                });
                //预约
                delect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),sport.id+"",Toast.LENGTH_SHORT).show();
                        System.out.println(sport.id+"====");
                         System.out.println("==============");
                        sports.clear();
                        sports.add(sport);
                        Gson gson = new Gson();
                        String sport = gson.toJson(sports);

                        System.out.println(sport+"====sport");
                    }
                });

                return view1;
            }
        };
    }

    private void loadMoreDate() {
        page = page+1;
        geconnection(page);
    }

    private void geconnection(int page) {

        RequestParams params = new RequestParams(Constent.URL+"AppSerlet/collection?page="+page+"");

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                sport = gson.fromJson(result,Sport_key.class);
                sportList.addAll(sport.sport);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    private void thread(final ImageView imageView, final String imgs) {
        final Bitmap[] bitmap = {null};
        final Bitmap[] finalBitmap = {bitmap[0]};
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        imageView.setImageBitmap(finalBitmap[0]);
                        break;
                    case 2:
                        break;
                }

            }
        };
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Constent.URL+"ww/imgs/" + imgs + "");
                    InputStream inputStream = url.openStream();
                    finalBitmap[0] = BitmapFactory.decodeStream(inputStream);
                    handler.sendEmptyMessage(1);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastIndex == sportList.size()+1) {
//            Toast.makeText(getActivity(), sportList.size() + "", Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),"加载数据",Toast.LENGTH_LONG).show();
//             当滑到底部时自动加载
            pg.setVisibility(View.VISIBLE);
            bt.setVisibility(View.GONE);
            handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadMoreDate();
                    bt.setVisibility(View.VISIBLE);
                    pg.setVisibility(View.GONE);
                }
            }, 3000);
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        lastIndex = firstVisibleItem+visibleItemCount;

    }
}