package com.example.cdm.huntfun.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.application.Myapplication;
import com.example.cdm.huntfun.util.NetUtil;
import com.example.cdm.huntfun.util.PhotoAdapter;
import com.example.cdm.huntfun.util.RecyclerItemClickListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

public class AddDynamicActivity extends AppCompatActivity {
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private int currentClickId = -1;
    ImageView uploapImage;
    TextView publish;
    File file;
    List<File> fileList;
    EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dynamic);
        uploapImage=(ImageView)findViewById(R.id.imageUpload);
        content=(EditText)findViewById(R.id.writer);
        publish=(TextView)findViewById(R.id.publish);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_LONG).show();
                finish();
            }
        });


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        photoAdapter = new PhotoAdapter(this, selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        findViewById(R.id.imageUpload).setOnClickListener(v -> onClick(v.getId()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                (view, position) ->
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(position)
                                .start(AddDynamicActivity.this)
        ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
               fileList=new ArrayList<File>();
                for (String photo:photos) {
                    System.out.println(photo);
                    file=new File(photo);
                    fileList.add(file);
                }

            }
            selectedPhotos.clear();

            if (photos != null) {

                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (currentClickId != -1) onClick(currentClickId);
        } else {

            Toast.makeText(this, "No read storage permission! Cannot perform the action.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {

        return false;
    }

    private void onClick(@IdRes int viewId) {
        switch (viewId) {
            case R.id.imageUpload: {
                PhotoPicker.builder()
                        .setPhotoCount(12)
                        .setGridColumnCount(4)
                        .start(this);
                break;
            }
        }

        currentClickId = viewId;
    }
    public void uploadImage(){
        RequestParams requestParams=new RequestParams(NetUtil.str+"uploaddynamicservlet");
        if(fileList!=null) {
            for (File file : fileList) {
                requestParams.addBodyParameter("file", file);
            }
        }
        Myapplication myApplication=(Myapplication)getApplication();
        try {
            requestParams.addBodyParameter("content", URLEncoder.encode(content.getText().toString(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        requestParams.addBodyParameter("publish",new Timestamp(System.currentTimeMillis())+"");
        requestParams.addBodyParameter("userId",myApplication.getSendDynamic().getUserId()+"");
        requestParams.setMultipart(true);


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                Log.i("ModifyPersonInfo", "onSuccess: "+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("activity",ex.toString());
                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();

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

