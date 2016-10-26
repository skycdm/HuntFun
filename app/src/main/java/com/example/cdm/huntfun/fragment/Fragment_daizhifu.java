package com.example.cdm.huntfun.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Sport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_daizhifu extends Fragment {

    List<Sport> list = new ArrayList<>();//保存选中项的数据
    Type type;
    double totalPrice=0;
    double eachPrice=0;
    int selectedNumber=0;
    double totalNumber=0;
    List<Sport> sport = new ArrayList<>();//获取数据库中的数据
    Map<Integer,Boolean> map = new HashMap<>();

    BaseAdapter adapter;
    private ListView unPay;
    private CheckBox checkAll;
    private TextView cart_chinese_heji;
    private Button cart_jiesuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daizhifu,null);
          for (int i=0;i<20;i++){

        }

        initView(view);

        getDate();

        return view;
    }

    private void getDate() {

//        for (int i=0;i<20;i++){
//            sport.get(i).setMoney(i+"");
//            sport.get(i).setNum(i+"");
//        }

        //初始化，所有的没选中
        for(int i=0;i<20;i++){
            map.put(i,false);
        }
        initDate(sport);
    }

    private void initDate(List<Sport> sports) {
        dapater(sports);

        click();
    }

    private void click() {

        //全选
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //初始化map：默认所有checkbox选中状态
                if(isChecked){
                    for(int i=0;i<20;i++){
                        map.put(i,true);
                    }
                    list.clear();
                    totalNumber=0;
                    selectedNumber=20;
//                    selectedNumber=sport.size();
                    totalPrice=0;
                    eachPrice=0;
//                    for(int i=0;i<sport.size();i++) {
//                        list.add(sport.get(i));
//                        totalNumber+=Integer.parseInt(sport.get(i).getMoney());
//                        eachPrice = Integer.parseInt(sport.get(i).getMoney())*Integer.parseInt(sport.get(i).getNum());
                        totalPrice+=eachPrice;
//                    }
                    //改变合计tv的值
                    cart_chinese_heji.setText("￥"+totalPrice);
                }
                dapater(sport);
            }
        });
    }

    private void initView(View view) {
        unPay = ((ListView) view.findViewById(R.id.unPay));//待支付listview
        checkAll = ((CheckBox) view.findViewById(R.id.checkall));
        cart_chinese_heji = ((TextView) view.findViewById(R.id.cart_chinese_heji));//合计
        cart_jiesuan = ((Button) view.findViewById(R.id.cart_jiesuan));//结算

    }

    private void dapater(final List<Sport> sports) {

        adapter = new BaseAdapter() {
            private TextView tv_quxiao;
            private TextView order_num;
            private TextView order_money;
            private TextView order_time;
            private Button btn_item_left;
            private Button btn_item_right;
            private CheckBox check;

            @Override
            public int getCount() {
                return 20;
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

                final View view = View.inflate(getActivity(), R.layout.frag_allorders_item,null);
                //关闭交易
                tv_quxiao = ((TextView) view.findViewById(R.id.frag_allorders_item_trade));
                tv_quxiao.setVisibility(View.GONE);
                //订单时间
                order_time = ((TextView) view.findViewById(R.id.frag_allorders_item_time));
                //实付价钱
                order_money = ((TextView) view.findViewById(R.id.frag_allorders_item_money));
                order_money.setText("￥"+position+"");
                //购买人数
                order_num = ((TextView) view.findViewById(R.id.frag_allorders_item_buynum));

                //取消
                btn_item_right = ((Button) view.findViewById(R.id.btn_item_right));
                //确定
                btn_item_left = ((Button) view.findViewById(R.id.btn_item_left));

                btn_item_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quxiao(((TextView)(view.findViewById(R.id.frag_allorders_item_money))).getText().toString());
                        btn_item_left.setClickable(false);
                    }
                });

                check = ((CheckBox) view.findViewById(R.id.check));

                check.setTag(position);
                check.setChecked(map.get(position));

                check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked&&position==(int)buttonView.getTag()&&!checkAll.isChecked()){
                            map.put((int)buttonView.getTag(),true);
//                            list.add(sports.get(position));
//                            totalNumber+= Integer.parseInt(sports.get(position).getNum());

                            selectedNumber++;
                            eachPrice=0;
                            //获取选中项的总价钱
//                            eachPrice=Integer.parseInt(sports.get(position).getNum())* Integer.parseInt(sports.get(position).getMoney());

                            totalPrice+=eachPrice;
                            cart_chinese_heji.setText("￥"+totalPrice);

                            if(selectedNumber ==20){
                                checkAll.setChecked(true);
                            }

                        }else if(!isChecked&&position==(int)buttonView.getTag()) {
                            selectedNumber--;
                            map.put((int) buttonView.getTag(), false);
//                            list.remove(sports.get(position));
//                            totalNumber -= Integer.parseInt(sports.get(position).getNum());
//                            eachPrice = Integer.parseInt(sports.get(position).getNum()) * Integer.parseInt(sports.get(position).getMoney());

                            //改变合计tv的值
                            totalPrice -= eachPrice;
                            cart_chinese_heji.setText("￥" + totalPrice);

                            if (checkAll.isChecked()) {
                                checkAll.setChecked(false);
                            }
                        }
                    }
                });

                return view;
            }
        };
        unPay.setAdapter(adapter);
    }

    private void quxiao(String ss) {
        System.out.println(ss+"==========");

    }

}