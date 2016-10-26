package com.example.cdm.huntfun.MineActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Sport_name;
import com.example.cdm.huntfun.pojo.SysOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FaBuActivity extends AppCompatActivity {

    final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/"+
            getPhotoFileName());
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;

    private Button shangchuan;
    private Button quxiao;
    private EditText input_type;
    private EditText input_sportName;
    private DatePicker start_date_time;
    private DatePicker end_timeDate;
    private EditText editText;
    private ImageView imageView2;
    private EditText money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysOut.getInstance().addActivity(this);
        setContentView(R.layout.activity_fa_bu);

        initView();
    }

    private void initView() {
        shangchuan = ((Button) findViewById(R.id.shangchuan));
        shangchuan.setText("确定");
        quxiao = ((Button) findViewById(R.id.quxiao));
        input_type = ((EditText) findViewById(R.id.input_type));//活动类型
        input_sportName = ((EditText) findViewById(R.id.input_sportName));//活动名称
        start_date_time = ((DatePicker) findViewById(R.id.date_time));//开始时间
        end_timeDate = ((DatePicker) findViewById(R.id.end_timeDate));//结束时间
        editText = ((EditText) findViewById(R.id.editText));//描述
        imageView2 = ((ImageView) findViewById(R.id.imageView2));//图片/选择图片点击
        money = ((EditText) findViewById(R.id.money));//参与费用
//        click();
    }

    private void click() {
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.putExtra("tag","t");
                startActivity(intent);
            }
        });
        shangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog  = new AlertDialog.Builder(FaBuActivity.this);
                dialog.setNegativeButton("拍 照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPicFromCamera();
                    }
                });

                dialog.setPositiveButton("选择照片", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getPicFromPhoto();
                    }
                });
                dialog.show();
            }
        });
    }

    private void upload() {

        String type = input_type.getText().toString().trim();
        String sportName = input_sportName.getText().toString().trim();
        String startTime_year =  String.valueOf(start_date_time.getYear());
        String startTime_mouth =  String.valueOf(start_date_time.getMonth());
        String startTime_day=  String.valueOf(start_date_time.getDayOfMonth());
        String _money = money.getText().toString().trim();
        String startTime =  startTime_year+"-"+startTime_mouth+"-"+startTime_day;//开始时间

        String endTime_year =  String.valueOf(end_timeDate.getYear());
        String endTime_mouth =  String.valueOf(end_timeDate.getMonth());
        String endTime_day=  String.valueOf(end_timeDate.getDayOfMonth());
        String endTime =  endTime_year+"-"+endTime_mouth+"-"+endTime_day;//结束时间
        String miaoshu =editText.getText().toString().trim();

        System.out.println(type+"======type");
        System.out.println(sportName+"======sportName");
        System.out.println(startTime+"======startTime");
        System.out.println(miaoshu+"======miaoshu");
        System.out.println(Sport_name.NAME+"======name");
        System.out.println(Sport_name.ID+"=========id");
        System.out.println(endTime+"======endTime");
        System.out.println(file.getName()+"==========fielName");

//      RequestParams rp = new RequestParams(Constent.URL+"getSportInfo");
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        return sdf.format(date) + ".png";
    }

    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
//        System.out.println("getPicFromCamera==========="+file.getAbsolutePath());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, CAMERA_REQUEST);

    }

    private void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功  固定
                        System.out.println("CAMERA_REQUEST"+file.getAbsolutePath());
                        if (file.exists()) {
                            photoClip(Uri.fromFile(file));
                        }
                        break;
                    default:
                        break;
                }
                break;
            case PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        saveImageToGallery(FaBuActivity.this,photo);//保存bitmap到本地
                        imageView2.setImageBitmap(photo);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = System.currentTimeMillis() + ".jpg";
//        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

    }
}
