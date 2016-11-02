package com.example.cdm.huntfun.activity.fragment.sqlitedraft;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.PublishActivity;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.pojo.User;
import com.example.cdm.huntfun.util.CommonAdapter;
import com.example.cdm.huntfun.util.ViewHolder;
import com.mobeta.android.dslv.DragSortListView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SqliteActivity extends AppCompatActivity {

    @InjectView(R.id.top)
    RelativeLayout top;
    @InjectView(R.id.lv_caogao)
    DragSortListView lvCaogao;

    List<Activity> activities = new ArrayList<>();
    Activity activity;

    CommonAdapter<Activity> activityCommonAdapter;

    public static final int CAO_GAO = 1;

    Timestamp beg=null;
    Timestamp end=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.inject(this);

        initEvent();

        lvCaogao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), PublishActivity.class);
                intent.putExtra("activityInfo",activities.get(position));
                startActivityForResult(intent,CAO_GAO);
            }
        });

        lvCaogao.setRemoveListener(new DragSortListView.RemoveListener() {
            @Override
            public void remove(int i) {
                System.out.println("==="+i+activities.get(i).getActivityId());
                int count=1;
                activityCommonAdapter.remove(i);
                ActSqliteHelper dbHelper = new ActSqliteHelper(SqliteActivity.this);
                //得到一个可写的数据库
                SQLiteDatabase db =dbHelper.getReadableDatabase();
                String whereClauses = "rowid=?";
                String [] whereArgs = {String.valueOf(i+count++)};
                //调用delete方法，删除数据
                db.delete("act_table", whereClauses, whereArgs);
            }
        });
    }

    public void querydb() {
        ActSqliteHelper dbHelper = new ActSqliteHelper(SqliteActivity.this);
        //得到一个可写的数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = db.query("act_table", new String[]{"activity_id", "user_id", "activity_label", "activity_theme", "activity_creattime", "activity_begintime", "activity_imgurl", "activity_desc",
                        "activity_care", "activity_cost", "activity_endtime", "activity_address", "activity_max_people_number", "activity_phone", "activity_trip",
                        "is_liudian", "activity_gather"},
                "user_id=?", new String[]{"2"}, null, null, null);
        while (cursor.moveToNext()) {
            String userId = cursor.getString(cursor.getColumnIndex("user_id"));
            String activity_id = cursor.getString(cursor.getColumnIndex("activity_id"));
            String activity_label = cursor.getString(cursor.getColumnIndex("activity_label"));
            String activity_theme = cursor.getString(cursor.getColumnIndex("activity_theme"));
            String activity_begintime = cursor.getString(cursor.getColumnIndex("activity_begintime"));
            String activity_creattime = cursor.getString(cursor.getColumnIndex("activity_creattime"));
            String activity_imgurl = cursor.getString(cursor.getColumnIndex("activity_imgurl"));
            String activity_desc = cursor.getString(cursor.getColumnIndex("activity_desc"));
            String activity_care = cursor.getString(cursor.getColumnIndex("activity_care"));
            String activity_cost = cursor.getString(cursor.getColumnIndex("activity_cost"));
            String activity_endtime = cursor.getString(cursor.getColumnIndex("activity_endtime"));
            String activity_address = cursor.getString(cursor.getColumnIndex("activity_address"));
            String activity_max_people_number = cursor.getString(cursor.getColumnIndex("activity_max_people_number"));
            String activity_phone = cursor.getString(cursor.getColumnIndex("activity_phone"));
            String activity_trip = cursor.getString(cursor.getColumnIndex("activity_trip"));
            String is_liudian = cursor.getString(cursor.getColumnIndex("is_liudian"));
            String activity_gather = cursor.getString(cursor.getColumnIndex("activity_gather"));

            System.out.println("activity_begintime"+activity_begintime);
            System.out.println("activity_endtime"+activity_endtime);
            if (activity_begintime.equals("")){
                activity_begintime="2000-01-01 00:00:00";
                beg=Timestamp.valueOf(activity_begintime);
            }else {
                beg=Timestamp.valueOf(activity_begintime+":00");
            }
            if (activity_endtime.equals("")){
                activity_endtime="2000-01-01 00:00:00";
                end=Timestamp.valueOf(activity_endtime);
            }else {
                end=Timestamp.valueOf(activity_endtime+":00");
            }

            /*String activityLable, String activityTheme, Timestamp activityBeginTime, Timestamp activityEndTime, Timestamp activityEndTimeSign, String activityAddress, String activityDesc,
            String activityCare, String activityImgurl, Double activityCost, Integer activityMaxPeopleNumber, String activityTrip, String gather, String phone, Boolean isLiuDian, User
            user*/

            activity = new Activity(Integer.parseInt(activity_id),activity_label, activity_theme, beg, end, Timestamp.valueOf(activity_creattime+":00"), activity_address, activity_desc, activity_care,
                    activity_imgurl, Double.parseDouble(activity_cost), Integer.parseInt(activity_max_people_number), activity_trip, activity_gather, activity_phone, Boolean.parseBoolean(is_liudian),
                    new User(Integer.parseInt(userId)));

            activities.add(activity);
        }

        //关闭数据库
        db.close();
        System.out.println("activities=============" + activities);
    }

    public void initEvent() {
        querydb();

        activityCommonAdapter = new CommonAdapter<Activity>(getApplicationContext(), activities, R.layout.activity_sqlite_item) {
            @Override
            public void convert(ViewHolder viewHolder, Activity activity, int position) {
                TextView title = viewHolder.getViewById(R.id.title);
                TextView time = viewHolder.getViewById(R.id.time);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String activity_creattime = sdf.format(activity.getActivityCreatTime());
                time.setText(activity_creattime);
            }
        };

        lvCaogao.setAdapter(activityCommonAdapter);

    }
}
