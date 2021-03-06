package com.example.cdm.huntfun.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.cdm.huntfun.pojo.User;
import com.example.cdm.huntfun.util.NetUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

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
public class FragmentPublish extends Fragment {
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

    com.example.cdm.huntfun.pojo.Activity activity;

    //相机拍摄照片和视频的标准目录
    private File file;
    private Uri imageUri;
    String items[] = {"相册选择", "拍照"};
    public static final int SELECT_PIC = 11;

    public Boolean chooseImg=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_publish, null);
        ButterKnife.inject(this, view);
        //判断sd卡是否存在，存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //目录，文件名Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            file = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
            imageUri = Uri.fromFile(file);
        }

        fromCaogao();

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(360), DensityUtil.dip2px(180))//图片大小
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setLoadingDrawableId(R.drawable.loadimg)//加载中默认显示图片
                .setUseMemCache(true)//设置使用缓存
                .setFailureDrawableId(R.drawable.ee)//加载失败后默认显示图片
                .build();
        x.image().bind(ivFm,NetUtil.url+"image/ee.png");
        return view;
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }

    String gather ="";
    String cost ="";
    String num ="";
    String phone ="";
    String trip ="";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult");
        switch (requestCode) {
            case 91:
                if (gather!=null) {
                    gather = data.getStringExtra("gather");
                    System.out.println("gather"+gather);
                    t1.setText(gather);
                }
                break;
            case 93:
                if (gather!=null) {
                    cost = data.getStringExtra("cost");
                    t3.setText(cost);
                }
                break;
            case 94:
                if (gather!=null) {
                    num = data.getStringExtra("num");
                    t4.setText(num);
                }
                break;
            case 95:
                if (gather!=null) {
                    phone = data.getStringExtra("phone");
                    t5.setText(phone);
                }
                break;
            case 92:
                if (gather!=null) {
                    trip = data.getStringExtra("trip");
                    t2.setText(trip);
                }
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
                    String picturePath = cursor.getString(columnIndex);  //获取照片路径
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
        float scaleWidth = ((float) destSize+500) / width;
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
        //uploadImage();//上传服务器
        chooseImg=true;

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

    public void uploadImage() {

        RequestParams requestParams = new RequestParams(NetUtil.url + "UploadFmServlet");
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file", file);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("success" + result);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.repair_begtime_et, R.id.repair_endtime_et, R.id.iv_fm, R.id.tv_jihe, R.id.tv_renshu, R.id.tv_dianhua, R.id.tv_feiyong, R.id.tv_xingcheng})
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
                /*intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        "image*//*");*/
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

    public void getData() {
        Timestamp activityBeginTime = null;
        Timestamp activityEndTime = null;
        String activityImgurl = null;
        System.out.println("initData");
        String activityLable = edtActivityTheme.getText().toString();
        String activityTheme = edtActivityTitle.getText().toString();
        /*if (repairBegtimeEt.getText().toString() != null && repairEndtimeEt.getText().toString() != null) {
            activityBeginTime = Timestamp.valueOf((repairBegtimeEt.getText().toString()) + ":00");
            activityEndTime = Timestamp.valueOf((repairEndtimeEt.getText().toString()) + ":00");
        }*/
        if (repairBegtimeEt.getText().toString() .equals("") || repairEndtimeEt.getText().toString().equals("")) {
            Toast.makeText(getActivity(),"活动开始和结束时间不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else {
            activityBeginTime = Timestamp.valueOf((repairBegtimeEt.getText().toString()) + ":00");
            activityEndTime = Timestamp.valueOf((repairEndtimeEt.getText().toString()) + ":00");
            if(activityBeginTime.getTime()>activityEndTime.getTime()){
                Toast.makeText(getActivity(),"活动开始时间必须小于结束时间",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String activityAddress = edtActivityAddress.getText().toString();
        String activityDesc = edtDetail.getText().toString();
        String activityCare = edtCare.getText().toString();
        if (chooseImg) {
            activityImgurl = "image/" + file.getName();
        }else {
            activityImgurl = "image/ee.png";
        }

        Double activityCost=Double.parseDouble((t3.getText().toString()).equals("未填写")?"0":t3.getText().toString());
        Integer activityMaxPeopleNumber=Integer.parseInt((t4.getText().toString()).equals("未填写")?"0":t4.getText().toString());
        String activityTrip=t2.getText().toString();
        String gather=t1.getText().toString();
        String phone=t5.getText().toString();
        Boolean isLiuDian=t6.isChecked();
        System.out.println("sdasdasdasda"+isLiuDian);

        Timestamp creatTime=new Timestamp(System.currentTimeMillis());
        System.out.println("creatTime"+creatTime);

        activity=new com.example.cdm.huntfun.pojo.Activity(activityLable,activityTheme,activityBeginTime,activityEndTime,creatTime,activityAddress,activityDesc,activityCare,activityImgurl,activityCost,activityMaxPeopleNumber,activityTrip,gather,phone,isLiuDian,new User(2));
        System.out.println("1111111111111" + activity);
    }

    public void publis() {
        getData();
        if (chooseImg) {
            uploadImage();//上传服务器
        }
        if (activity != null) {
            RequestParams requestParams = new RequestParams(NetUtil.url+"InsertActivityServlet");
            Gson gson = new Gson();
            String activityInfo = gson.toJson(activity);
            requestParams.addBodyParameter("activityInfo", activityInfo);
            System.out.println("=======" + activityInfo);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("success"+result);
                    Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    System.out.println("fail" + ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
        /*edtActivityTheme.setText("");
        edtActivityTitle.setText("");*/
        repairBegtimeEt.setText("");
        repairEndtimeEt.setText("");
        /*edtActivityAddress.setText("");
        edtDetail.setText("");
        edtCare.setText("");
        t1.setText("未填写");
        t2.setText("未填写");
        t3.setText("未填写");
        t4.setText("未填写");
        t5.setText("未填写");
        t6.setChecked(false);*/
    }

    public void fromCaogao(){
        Intent intent = getActivity().getIntent();
        activity = intent.getParcelableExtra("activityInfo");
        System.out.println("initData+===" + activity);
        if (activity!=null) {
            edtActivityTheme.setText(activity.getActivityLable());
            edtActivityTitle.setText(activity.getActivityTheme());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            repairBegtimeEt.setText(sdf.format(activity.getActivityBeginTime()));
            repairEndtimeEt.setText(sdf.format(activity.getActivityEndTime()));
            edtActivityAddress.setText(activity.getActivityAddress());
            edtDetail.setText(activity.getActivityDesc());
            edtCare.setText(activity.getActivityCare());
            t1.setText(activity.getGather());
            t2.setText(activity.getActivityTrip());
            t3.setText(activity.getActivityCost() + "");
            t4.setText(activity.getActivityMaxPeopleNumber() + "");
            t5.setText(activity.getPhone());
            t6.setChecked(activity.getLiuDian());

            if (activity.getActivityImgurl()!=null) {
                Bitmap bitmap = BitmapFactory.decodeFile(activity.getActivityImgurl());
                //ivFm.setImageBitmap(bitmap);
                showImage(scaleBitmap(bitmap));
            }
        }
    }
}
