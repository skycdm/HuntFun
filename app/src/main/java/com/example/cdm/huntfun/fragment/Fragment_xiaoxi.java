package com.example.cdm.huntfun.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.AddDynamicActivity;
import com.example.cdm.huntfun.application.Dianzan;
import com.example.cdm.huntfun.application.Myapplication;
import com.example.cdm.huntfun.pojo.Dynamic;
import com.example.cdm.huntfun.pojo.Good;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.ViewHolder;
import com.example.cdm.huntfun.view.NineGridTestLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_xiaoxi extends Fragment {
  List<Dynamic> dynamicList = new ArrayList<Dynamic>();
   private DynamicAdapter commonAdapter;
   ListView myList;
   private ImageView add;
   SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Good good;
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getActivity().getSharedPreferences(Dianzan.DIANZAN_SHARED_PREFS, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xiaoxi,null);
        myList = (ListView) view.findViewById(R.id.dynamicList);
        add = (ImageView) view.findViewById(R.id.addImageView);
        return view;
    }
   @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initEvent();

    }
 private void initEvent() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDynamicActivity.class);
                startActivity(intent);
            }
        });

    }
  private void initData() {
        getAllDynamicList();
    }
 private void getAllDynamicList() {
        RequestParams requestParams = new RequestParams(NetUtil.str + "dynamicservlet");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Dynamic>>() {
                }.getType();
                List<Dynamic> newDynamic = gson.fromJson(result, type);
                dynamicList.clear();
                dynamicList.addAll(newDynamic);
                if (commonAdapter == null) {
                    commonAdapter = new DynamicAdapter(getActivity(), dynamicList, R.layout.dynamic_layout);
                    myList.setAdapter(commonAdapter);

                }else{
                    commonAdapter.notifyDataSetChanged();
                   }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();
                Log.i("activity",ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
       public  class DynamicAdapter extends CommonAdapter<Dynamic> {



           public DynamicAdapter(Context context, List<Dynamic> lists, int layoutId) {
               super(context, lists, layoutId);

           }

           @Override
           public void convert(ViewHolder viewHolder, Dynamic dynamic, int position) {

               TextView tv1 = viewHolder.getViewById(R.id.name);
               tv1.setText(dynamic.getUser().getUserName());
               TextView tv2 = viewHolder.getViewById(R.id.content);
               tv2.setText(dynamic.getContent());
               TextView tv3 = viewHolder.getViewById(R.id.time);
               String sdf = new SimpleDateFormat("HH:mm").format(dynamic.getPublishTime());
               tv3.setText(sdf);

               ImageView photoImage = viewHolder.getViewById(R.id.photoImage);
               String photo = dynamic.getUser().getPhoto();
               NineGridTestLayout layout=viewHolder.getViewById(R.id.layout_nine_grid);
              if(dynamic.getPic()!=null) {
                   String[] s = dynamic.getPic().split(",");

                   List<String> urlList = new ArrayList<String>();
                   if (s.length > 0) {
                       int length = s.length;
                       for (int i = 0; i < length; i++) {

                           urlList.add(NetUtil.strImage + s[i]);
                       }
                       layout.setIsShowAll(dynamic.isShowAll);
                       layout.setUrlList(urlList);
                   }
               }
         x.image().bind(photoImage, NetUtil.strImage + photo);
               ImageView imageGood = viewHolder.getViewById(R.id.good);
               TextView tv4=viewHolder.getViewById(R.id.who);
               imageGood.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       if(sharedPreferences.contains(position+"")){
                           ((ImageView)v).setImageResource(R.drawable.before);
                           cancelDianzan(dynamic.getDynamicId());

                           editor.remove(position+"");
                           Myapplication myApplication=(Myapplication)getActivity().getApplication();
                           String name=myApplication.getUserInfo().getUserName();

                           tv4.setText("");

                       }else{

                           editor.putInt(position+"",(Integer)(((ImageView)v).getTag()));

                           Bitmap bm = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.after);
                           ((ImageView) v).setImageBitmap(bm);
                           int dynamicId=dynamic.getDynamicId();
                           Log.e("click",dynamicId+"");
                           dianzan(dynamicId);
                           Myapplication myApplication=(Myapplication)getActivity().getApplication();
                           String name=myApplication.getUserInfo().getUserName();
                           tv4.setText(name);
                           System.out.println(dynamicId);
                       }
                       editor.commit();
                   }
               });
               imageGood.setTag(position);
             if(sharedPreferences.contains(position+"")){
                  imageGood.setImageResource(R.drawable.after);
               }else{
                   imageGood.setImageResource(R.drawable.before);
               }


           }
           }
 public void dianzan(int dynamicId){

     Myapplication myApplication=(Myapplication)getActivity().getApplication();
        good=new Good(myApplication.getUserInfo(),dynamicId,new Timestamp(System.currentTimeMillis()));
        Gson gson=new Gson();
        String zan=gson.toJson(good);
        RequestParams request=new RequestParams(NetUtil.str+"zanservlet");
        request.addQueryStringParameter("zan",zan);
        x.http().get(request, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
                System.out.println();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
             Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void cancelDianzan(int dynamicId){
        RequestParams requestParams=new RequestParams(NetUtil.str+"cancelzanservlet");
        requestParams.addQueryStringParameter("dynamicId",dynamicId+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();
            Log.i("aaa",ex.toString());
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