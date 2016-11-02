package com.example.cdm.huntfun.activity.fragment.sqlitedraft;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cdm on 2016/10/27.
 */
public class ActSqliteHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    public ActSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ActSqliteHelper(Context context) {
        super(context,"act_db", null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table act_table(activity_id int,user_id int,activity_label varchar(20),activity_theme varchar(100),activity_begintime varchar(25),activity_creattime varchar(25),activity_imgurl varchar(50)," +
                "activity_desc varchar(300),activity_care varchar(500),activity_cost varchar(100),activity_endtime varchar(25),activity_address varchar(255)," +
                "activity_max_people_number varchar(10),activity_phone varchar(25),activity_trip varchar(255),is_liudian varchar(10),activity_gather varchar(255))";
        //输出创建数据库的日志信息
        System.out.println("create Database------------->");
        //execSQL函数用于执行SQL语句
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //输出更新数据库的日志信息
        System.out.println("update Database------------->");
    }
}
