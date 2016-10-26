package com.example.cdm.huntfun.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdm.huntfun.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_jinxing extends Fragment {

    List<Integer> orders = new ArrayList<>();
    BaseAdapter adapter;
    private ListView Pay;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jinxing,null);
        initView(view);

        initDate();
        return view;
    }
    private void initDate() {
        dapater();
        Pay.setAdapter(adapter);
    }

    private void initView(View view) {
        Pay = ((ListView) view.findViewById(R.id.cart_listview));//待支付listview
    }

    private void dapater() {
        adapter = new BaseAdapter() {
            private RelativeLayout ispayBt;
            private TextView tv_quxiao;
            private Button btn_item_left;
            private Button btn_item_right;
            private TextView order_num;
            private TextView order_money;
            private TextView order_time;

            @Override
            public int getCount() {
                return 10;
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
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = View.inflate(getActivity(), R.layout.frag_allorders_item,null);
                //关闭交易
                tv_quxiao = ((TextView) view.findViewById(R.id.frag_allorders_item_trade));
                tv_quxiao.setVisibility(View.GONE);
                //订单时间
                order_time = ((TextView) view.findViewById(R.id.frag_allorders_item_time));
                //实付价钱
                order_money = ((TextView) view.findViewById(R.id.frag_allorders_item_money));
                //购买人数
                order_num = ((TextView) view.findViewById(R.id.frag_allorders_item_buynum));
                //取消
                btn_item_right = ((Button) view.findViewById(R.id.btn_item_right));
                btn_item_right.setVisibility(View.GONE);
                //确定
                btn_item_left = ((Button) view.findViewById(R.id.btn_item_left));
                btn_item_left.setVisibility(View.GONE);
                ispayBt = ((RelativeLayout) view.findViewById(R.id.ispayBt));
                ispayBt.setVisibility(View.GONE);
                return view;
            }
        };
    }
}