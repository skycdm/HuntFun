package com.example.cdm.huntfun.activity.fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cdm.huntfun.DatePickActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.fragment.child.CostActivity;
import com.example.cdm.huntfun.activity.fragment.child.GatherActivity;
import com.example.cdm.huntfun.activity.fragment.child.PeopleNumberActivity;
import com.example.cdm.huntfun.activity.fragment.child.PhoneActivity;
import com.example.cdm.huntfun.activity.fragment.child.TripActivity;
import com.example.cdm.huntfun.activity.fragment.sqlitedraft.ActSqliteHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by cdm on 2016/10/12.
 */
public class FragmentCaogao extends Fragment {
    @InjectView(R.id.tv_activity_theme)
    TextView tvActivityTheme;
    @InjectView(R.id.edt_activity_theme)
    EditText edtActivityTheme;
    @InjectView(R.id.rl_theme)
    RelativeLayout rlTheme;
    @InjectView(R.id.tv_1)
    View tv1;
    @InjectView(R.id.tv_activity_title)
    TextView tvActivityTitle;
    @InjectView(R.id.edt_activity_title)
    EditText edtActivityTitle;
    @InjectView(R.id.rl_title)
    RelativeLayout rlTitle;
    @InjectView(R.id.tv_2)
    View tv2;
    @InjectView(R.id.tv_activity_begtime)
    TextView tvActivityBegtime;
    @InjectView(R.id.repair_begtime_et)
    EditText repairBegtimeEt;
    @InjectView(R.id.iv_in)
    ImageView ivIn;
    @InjectView(R.id.rl_begtime)
    RelativeLayout rlBegtime;
    @InjectView(R.id.tv_3)
    View tv3;
    @InjectView(R.id.tv_activity_endtime)
    TextView tvActivityEndtime;
    @InjectView(R.id.repair_endtime_et)
    EditText repairEndtimeEt;
    @InjectView(R.id.iv_in2)
    ImageView ivIn2;
    @InjectView(R.id.rl_endtime)
    RelativeLayout rlEndtime;
    @InjectView(R.id.tv_4)
    View tv4;
    @InjectView(R.id.tv_activity_address)
    TextView tvActivityAddress;
    @InjectView(R.id.edt_activity_address)
    EditText edtActivityAddress;
    @InjectView(R.id.iv_in3)
    ImageView ivIn3;
    @InjectView(R.id.rl_address)
    RelativeLayout rlAddress;
    @InjectView(R.id.tv_fm)
    TextView tvFm;
    @InjectView(R.id.iv_fm)
    ImageView ivFm;
    @InjectView(R.id.rl_fm)
    RelativeLayout rlFm;
    @InjectView(R.id.tv_detail)
    TextView tvDetail;
    @InjectView(R.id.edt_detail)
    EditText edtDetail;
    @InjectView(R.id.rl_detail)
    RelativeLayout rlDetail;
    @InjectView(R.id.tv_care)
    TextView tvCare;
    @InjectView(R.id.edt_care)
    EditText edtCare;
    @InjectView(R.id.rl_care)
    RelativeLayout rlCare;
    @InjectView(R.id.iv_jh)
    ImageView ivJh;
    @InjectView(R.id.tv_jihe)
    TextView tvJihe;
    @InjectView(R.id.t1)
    TextView t1;
    @InjectView(R.id.tvv1)
    TextView tvv1;
    @InjectView(R.id.iv_rs)
    ImageView ivRs;
    @InjectView(R.id.tv_renshu)
    TextView tvRenshu;
    @InjectView(R.id.t4)
    TextView t4;
    @InjectView(R.id.iv_xc)
    ImageView ivXc;
    @InjectView(R.id.tv_xingcheng)
    TextView tvXingcheng;
    @InjectView(R.id.t2)
    TextView t2;
    @InjectView(R.id.tvv2)
    TextView tvv2;
    @InjectView(R.id.iv_dh)
    ImageView ivDh;
    @InjectView(R.id.tv_dianhua)
    TextView tvDianhua;
    @InjectView(R.id.t5)
    TextView t5;
    @InjectView(R.id.iv_fy)
    ImageView ivFy;
    @InjectView(R.id.tv_feiyong)
    TextView tvFeiyong;
    @InjectView(R.id.t3)
    TextView t3;
    @InjectView(R.id.tvv3)
    TextView tvv3;
    @InjectView(R.id.iv_ld)
    ImageView ivLd;
    @InjectView(R.id.tv_liudian)
    TextView tvLiudian;
    @InjectView(R.id.t6)
    ToggleButton t6;

    //相机拍摄照片和视频的标准目录
    private File file;
    private Uri imageUri;
    String items[] = {"相册选择", "拍照"};
    public static final int SELECT_PIC = 11;

    String picturePath =null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_caogao, null);
        ButterKnife.inject(this, view);

        createDb();

        //判断sd卡是否存在，存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //目录，文件名Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            file = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
            imageUri = Uri.fromFile(file);
        }

        return view;
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 91:
                String gather = data.getStringExtra("gather");
                t1.setText(gather);
                break;
            case 93:
                String cost = data.getStringExtra("cost");
                t3.setText(cost);
                break;
            case 94:
                String num = data.getStringExtra("num");
                t4.setText(num);
                break;
            case 95:
                String phone = data.getStringExtra("phone");
                t5.setText(phone);
                break;
            case 92:
                String trip = data.getStringExtra("trip");
                t2.setText(trip);
                break;
            case SELECT_PIC:
                //相册选择
                try {
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    System.out.println("url1=====" + selectedImage);
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    System.out.println("url2=====" + filePathColumn[0]);
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    System.out.println("columnIndex=="+columnIndex);
                    picturePath = cursor.getString(columnIndex);  //获取照片路径
                    System.out.println("url3=====" + picturePath);
                    cursor.close();
                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                    //ivFm.setImageBitmap(scaleBitmap(bitmap));
                    showImage(scaleBitmap(bitmap));
                } catch (Exception e) {
                    // TODO Auto-generatedcatch block
                    e.printStackTrace();
                }
                break;
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            if (!repairBegtimeEt.getText().toString().equals(date)) {
                repairBegtimeEt.setText(data.getStringExtra("date"));
            } else {
                System.out.println("选择未变");
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            if (!repairEndtimeEt.getText().toString().equals(date)) {
                repairEndtimeEt.setText(data.getStringExtra("date"));
            } else {
                System.out.println("选择未变");
            }
        }
    }

    private Bitmap scaleBitmap(Bitmap src) {
        int width = src.getWidth();//原来尺寸大小
        int height = src.getHeight();
        final float destSize = 500;//缩放到这个大小,你想放大多少就多少

        //图片缩放比例，新尺寸/原来的尺寸
        float scaleWidth = ((float) destSize + 500) / width;
        float scaleHeight = ((float) destSize) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //返回缩放后的图片
        return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
    }

    //显示图片，上传服务器
    public void showImage(Bitmap bitmap) {
        ivFm.setImageBitmap(bitmap);//iv显示图片
        saveImage(bitmap);//保存文件
        System.out.println("filepath:===" + file);
    }

    //将bitmap保存在文件中
    public void saveImage(Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
    }

    //创建数据库的方法
    public void createDb() {
        //创建StuDBHelper对象
        ActSqliteHelper dbHelper = new ActSqliteHelper(getActivity());
        //得到一个可读的SQLiteDatabase对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
    }

    //更新数据库的方法
    public void updateeDb() {
        // 数据库版本的更新,由原来的1变为2
        ActSqliteHelper dbHelper = new ActSqliteHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
    }

    //插入数据的方法
    public void insertDb() {
        ActSqliteHelper dbHelper = new ActSqliteHelper(getActivity());
        //得到一个可写的数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //生成ContentValues对象 //key:列名，value:想插入的值
        ContentValues cv = new ContentValues();

        Timestamp creatTime=new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String activity_creattime=sdf.format(creatTime);

        //往ContentValues对象存放数据，键-值对模式
        cv.put("activity_id", 1);
        cv.put("user_id", 2);
        cv.put("activity_label", edtActivityTheme.getText().toString());
        cv.put("activity_theme", edtActivityTitle.getText().toString());
        cv.put("activity_begintime", repairBegtimeEt.getText().toString());
        cv.put("activity_creattime", activity_creattime);
        cv.put("activity_imgurl", picturePath);
        System.out.println("activity_imgurl=1=="+file.toString());
        System.out.println("activity_imgurl=1=="+imageUri.toString());
        cv.put("activity_desc", edtDetail.getText().toString());
        cv.put("activity_care", edtCare.getText().toString());
        cv.put("activity_cost", Double.parseDouble((t3.getText().toString()).equals("未填写") ? "0" : t3.getText().toString()));
        cv.put("activity_endtime", repairEndtimeEt.getText().toString());
        cv.put("activity_address", edtActivityAddress.getText().toString());
        cv.put("activity_max_people_number", Integer.parseInt((t4.getText().toString()).equals("未填写") ? "0" : t4.getText().toString()));
        cv.put("activity_phone", t5.getText().toString());
        cv.put("activity_trip", t2.getText().toString());
        cv.put("is_liudian", t6.isChecked());
        cv.put("activity_gather", t1.getText().toString());
        //调用insert方法，将数据插入数据库
        db.insert("act_table", null, cv);
        //关闭数据库
        db.close();
    }

    public void savedb(){
        insertDb();
        //updateeDb();
        Toast.makeText(getActivity(),"保存成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.repair_begtime_et, R.id.repair_endtime_et, R.id.iv_fm, R.id.tv_jihe, R.id.tv_renshu, R.id.tv_xingcheng, R.id.tv_dianhua, R.id.tv_feiyong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.repair_begtime_et:
                Intent intent1 = new Intent(getActivity(), DatePickActivity.class);
                intent1.putExtra("date", repairBegtimeEt.getText().toString());
                startActivityForResult(intent1, 1);
                break;
            case R.id.repair_endtime_et:
                Intent intent2 = new Intent(getActivity(), DatePickActivity.class);
                intent2.putExtra("date", repairEndtimeEt.getText().toString());
                startActivityForResult(intent2, 2);
                break;
            case R.id.iv_fm:
                //选择封面
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PIC);
                break;
            case R.id.tv_jihe:
                Intent intentjh = new Intent(getActivity(), GatherActivity.class);
                intentjh.putExtra("gather",t1.getText().toString());
                startActivityForResult(intentjh, 91);
                break;
            case R.id.tv_renshu:
                Intent intentrs = new Intent(getActivity(), PeopleNumberActivity.class);
                intentrs.putExtra("num",t4.getText().toString());
                startActivityForResult(intentrs, 94);
                break;
            case R.id.tv_dianhua:
                Intent intentdh = new Intent(getActivity(), PhoneActivity.class);
                intentdh.putExtra("phone",t5.getText().toString());
                startActivityForResult(intentdh, 95);
                break;
            case R.id.tv_feiyong:
                Intent intentfy = new Intent(getActivity(), CostActivity.class);
                intentfy.putExtra("cost",t3.getText().toString());
                startActivityForResult(intentfy, 93);
                break;
            case R.id.tv_xingcheng:
                Intent intentxc = new Intent(getActivity(), TripActivity.class);
                intentxc.putExtra("trip",t2.getText().toString());
                startActivityForResult(intentxc, 92);
                break;
        }
    }
}
