package com.example.cdm.huntfun.activity.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdm.huntfun.DatePickActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.fragment.child.CostActivity;
import com.example.cdm.huntfun.activity.fragment.child.GatherActivity;
import com.example.cdm.huntfun.activity.fragment.child.PeopleNumberActivity;
import com.example.cdm.huntfun.activity.fragment.child.PhoneActivity;
import com.example.cdm.huntfun.util.NetUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    @InjectView(R.id.tv_5)
    TextView tv5;
    @InjectView(R.id.tv_activity_endtimesign)
    TextView tvActivityEndtimesign;
    @InjectView(R.id.repair_endsigntime_et)
    EditText repairEndsigntimeEt;
    @InjectView(R.id.iv_in4)
    ImageView ivIn4;
    @InjectView(R.id.rl_endtimesign)
    RelativeLayout rlEndtimesign;
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
    TextView t6;

    //相机拍摄照片和视频的标准目录
    private File file;
    private Uri imageUri;
    String items[] = {"相册选择", "拍照"};
    public static final int SELECT_PIC = 11;
    public static final int TAKE_PHOTO = 12;
    public static final int CROP = 13;

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
        switch (requestCode){
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
            case SELECT_PIC:
                //相册选择
                if (data != null) {
                    crop(data.getData());
                }
                break;
            case TAKE_PHOTO:
                crop(Uri.fromFile(file));
                break;
            case CROP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {

                        Bitmap bitmap = extras.getParcelable("data");
                        showImage(scaleBitmap(bitmap));
                    }
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
        if (resultCode == Activity.RESULT_OK && requestCode == 3) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            if (!repairEndsigntimeEt.getText().toString().equals(date)) {
                repairEndsigntimeEt.setText(data.getStringExtra("date"));
                //Toast.makeText(getActivity().getApplicationContext(),"报名结束时间必须小于开始时间",Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("选择未变");
            }
        }
    }

    public void crop(Uri uri) {
        //  intent.setType("image/*");
        //裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        /*intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);*/

        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }

    private Bitmap scaleBitmap(Bitmap src) {
        int width = src.getWidth();//原来尺寸大小
        int height = src.getHeight();
        final float destSize = 500;//缩放到这个大小,你想放大多少就多少

        //图片缩放比例，新尺寸/原来的尺寸
        float scaleWidth = ((float) destSize) / width;
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
        uploadImage();//上传服务器

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

        RequestParams requestParams = new RequestParams(NetUtil.url + "UploadImageServlet");
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file", file);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("ModifyPersonInfo", "onSuccess: ");
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

    @OnClick({R.id.repair_begtime_et, R.id.repair_endtime_et, R.id.repair_endsigntime_et, R.id.iv_fm, R.id.tv_jihe, R.id.tv_renshu, R.id.tv_dianhua, R.id.tv_feiyong})
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
            case R.id.repair_endsigntime_et:
                Intent intent3 = new Intent(getActivity(), DatePickActivity.class);
                intent3.putExtra("date", repairEndsigntimeEt.getText().toString());
                startActivityForResult(intent3, 3);
                break;
            case R.id.iv_fm:
                //选择封面
                new AlertDialog.Builder(getActivity()).setTitle("选择").setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        switch (which) {
                            case 0:

                                //相册选择
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        "image/*");
                                startActivityForResult(intent, SELECT_PIC);
                                break;


                            case 1:

                                //拍照:
                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                startActivityForResult(intent2, TAKE_PHOTO);

                                break;
                        }
                    }
                }).show();
                break;
            case R.id.tv_jihe:
                Intent intentjh = new Intent(getActivity(), GatherActivity.class);
                startActivityForResult(intentjh, 91);
                break;
            case R.id.tv_renshu:
                Intent intentrs = new Intent(getActivity(), PeopleNumberActivity.class);
                startActivityForResult(intentrs, 94);
                break;
            case R.id.tv_dianhua:
                Intent intentdh = new Intent(getActivity(), PhoneActivity.class);
                startActivityForResult(intentdh, 95);
                break;
            case R.id.tv_feiyong:
                Intent intentfy = new Intent(getActivity(), CostActivity.class);
                startActivityForResult(intentfy, 93);
                break;
        }
    }
}
