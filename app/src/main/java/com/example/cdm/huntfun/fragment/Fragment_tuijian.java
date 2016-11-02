package com.example.cdm.huntfun.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.PublishActivity;
import com.example.cdm.huntfun.activity.manage.ManageActivity;
import com.example.cdm.huntfun.activity.ownview.ListenerForScrolView;
import com.example.cdm.huntfun.pojo.WeatherInfo;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.ListViewForScrollView;
import com.example.cdm.huntfun.util.ViewHolder;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_tuijian extends Fragment {


    @InjectView(R.id.iv_bank)
    ImageView ivBank;
    @InjectView(R.id.ttop)
    RelativeLayout ttop;
    @InjectView(R.id.now_we)
    TextView nowWe;
    @InjectView(R.id.we_desc)
    TextView weDesc;
    @InjectView(R.id.we_city)
    TextView weCity;
    @InjectView(R.id.all_weather)
    ImageView allWeather;
    @InjectView(R.id.we_range)
    TextView weRange;
    @InjectView(R.id.top)
    RelativeLayout top;
    @InjectView(R.id.line)
    TextView line;
    @InjectView(R.id.wind)
    TextView wind;
    @InjectView(R.id.humidity)
    TextView humidity;
    @InjectView(R.id.top_detail)
    RelativeLayout topDetail;
    @InjectView(R.id.sss)
    RelativeLayout sss;
    @InjectView(R.id.line1)
    TextView line1;
    @InjectView(R.id.lv)
    ListViewForScrollView lv;
    @InjectView(R.id.btn_more)
    ImageView btnMore;
    @InjectView(R.id.btn_less)
    ImageView btnLess;
    @InjectView(R.id.la)
    RelativeLayout la;
    @InjectView(R.id.iv_publish)
    ImageView ivPublish;
    @InjectView(R.id.t1)
    TextView t1;
    @InjectView(R.id.rel_publish)
    RelativeLayout relPublish;
    @InjectView(R.id.iv_manage)
    ImageView ivManage;
    @InjectView(R.id.t2)
    TextView t2;
    @InjectView(R.id.rel_manage)
    RelativeLayout relManage;
    @InjectView(R.id.scrollview)
    ScrollView scrollview;


    List<WeatherInfo.Futuer> futuerList = new ArrayList<>();
    private HomeAdapter mAdapter;
    CommonAdapter<WeatherInfo.Futuer> futuerCommonAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuijian, null);

        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getActivity().getSystemService(serviceName); // 查找到服务信息
        //locationManager.setTestProviderEnabled("gps", true);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300000, 8, mLocationListener01);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 300000, 8, mLocationListener01);

        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_bank, R.id.btn_more, R.id.btn_less, R.id.rel_publish, R.id.rel_manage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bank:
                break;
            case R.id.btn_more:
                lv.setVisibility(View.VISIBLE);
                btnMore.setVisibility(View.GONE);
                btnLess.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_less:
                lv.setVisibility(View.GONE);
                btnMore.setVisibility(View.VISIBLE);
                btnLess.setVisibility(View.GONE);
                break;
            case R.id.rel_publish:
                Intent intent=new Intent(getActivity(),PublishActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_manage:
                Intent intentMan=new Intent(getActivity(), ManageActivity.class);
                startActivity(intentMan);
                break;
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.further_day_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.week.setText(futuerList.get(position).week);
            holder.week.setText(futuerList.get(position).week);
            iconToday(holder.icon, futuerList.get(position).weather);
        }

        @Override
        public int getItemCount() {
            return futuerList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView week;
            private final TextView timp;
            private final ImageView icon;

            public MyViewHolder(View view) {
                super(view);
                week = (TextView) view.findViewById(R.id.daily_time);
                timp = (TextView) view.findViewById(R.id.daily_tmphigh);
                icon = (ImageView) view.findViewById(R.id.daily_weather);
            }
        }
    }

    private Location updateToNewLocation(Location location) {
        System.out.println("--------zhixing--2--------");
        String latLongString;
        double lat = 0;
        double lng = 0;

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            latLongString = "纬度:" + lat + "\n经度:" + lng;
            System.out.println("经度：" + lng + "纬度：" + lat);
            //getData(location);
        } else {
            latLongString = "无法获取地理信息，请稍后...";
        }
        if (lat != 0) {
            System.out.println("--------反馈信息----------" + String.valueOf(lat));
        }

        Toast.makeText(getActivity(), latLongString, Toast.LENGTH_SHORT).show();

        return location;

    }

    public void getData(Location location) {
        RequestParams requestParams = new RequestParams("http://v.juhe.cn/weather/geo?format=2&key=967a3459aecf73c544203d1469115990");
        requestParams.addBodyParameter("lon", location.getLongitude() + "");
        requestParams.addBodyParameter("lat", location.getLatitude() + "");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("onSuccess==" + result);
                Gson gson = new Gson();
                WeatherInfo weatherInfo = gson.fromJson(result, WeatherInfo.class);
                System.out.println("weatherInfo" + weatherInfo);
                if (futuerList == null) {
                    futuerList.addAll(weatherInfo.result.future);
                    futuerList.remove(0);
                } else {
                    futuerList.clear();
                    futuerList.addAll(weatherInfo.result.future);
                    futuerList.remove(0);
                }
                System.out.println("futuerList0000" + weatherInfo.result.future);
                System.out.println("futuerList" + futuerList);

                WeatherInfo.Sk sk = weatherInfo.result.sk;
                System.out.println("sk" + sk);
                WeatherInfo.Today today = weatherInfo.result.today;
                System.out.println("today" + today);
                nowWe.setText(sk.temp + "°");
                weDesc.setText(today.weather);
                weCity.setText(today.city);
                weRange.setText(today.temperature);
                wind.setText("风力：" + today.wind);
                humidity.setText("湿度：" + sk.humidity);
                iconToday(allWeather, today.weather);
                ininData();

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

    public void iconToday(ImageView allWeather, String weather) {
        if (weather.equals("晴")) {
            allWeather.setImageResource(R.mipmap.sun_small);
        } else if (weather.equals("多云转晴") || weather.equals("晴转多云")) {
            allWeather.setImageResource(R.mipmap.suncloudy_small);
        } else if (weather.equals("多云")) {
            allWeather.setImageResource(R.mipmap.cloudy_small);
        } else if (weather.equals("阴")) {
            allWeather.setImageResource(R.mipmap.cloudy_small);
        } else if (weather.contains("风")) {
            allWeather.setImageResource(R.mipmap.wind_small);
        } else if (weather.contains("雷阵雨")) {
            allWeather.setImageResource(R.mipmap.thunder_small);
        } else if (weather.contains("阵雨")) {
            allWeather.setImageResource(R.mipmap.shower_small);
        } else if (weather.contains("雨")) {
            allWeather.setImageResource(R.mipmap.rain_small);
        } else if (weather.contains("雪")) {
            allWeather.setImageResource(R.mipmap.snow_small);
        }
    }

    public void ininData() {
        futuerCommonAdapter = new CommonAdapter<WeatherInfo.Futuer>(getActivity(), futuerList, R.layout.further_day_item) {
            @Override
            public void convert(ViewHolder viewHolder, WeatherInfo.Futuer futuer, int position) {
                TextView week = viewHolder.getViewById(R.id.daily_time);
                TextView timp = viewHolder.getViewById(R.id.daily_tmphigh);
                week.setText(futuer.week);
                timp.setText(futuer.temperature);

                ImageView icon = viewHolder.getViewById(R.id.daily_weather);
                iconToday(icon, futuer.weather);
            }
        };
        lv.setAdapter(futuerCommonAdapter);
    }


    public final LocationListener mLocationListener01 = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateToNewLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateToNewLocation(null);
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
}