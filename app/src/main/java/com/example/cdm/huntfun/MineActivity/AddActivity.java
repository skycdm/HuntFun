package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Constent;
import com.example.cdm.huntfun.pojo.SysOut;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText add_name;
    private EditText add_psw;
    private Button add_sure;
    private Button add_back;
    private EditText add_psw_again;
    private EditText add_phone;
    private EditText add_email;
    private EditText editText2;
    private RadioGroup check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysOut.getInstance().addActivity(this);
        setContentView(R.layout.activity_add);

        add_name = ((EditText) findViewById(R.id.add_name));
        add_psw = ((EditText) findViewById(R.id. add_psw));
        add_psw_again = ((EditText) findViewById(R.id.add_psw_again));//再次输入密码
        add_phone = ((EditText) findViewById(R.id.add_phone));
        add_email = ((EditText) findViewById(R.id.add_email));
        editText2 = ((EditText) findViewById(R.id.editText2));//身份证号
        check = ((RadioGroup) findViewById(R.id.check));

        add_sure = ((Button) findViewById(R.id.add_sure));
        add_sure.setOnClickListener(this);
        add_back = ((Button) findViewById(R.id.add_back));
        add_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_sure:
                add_sure();
                break;
            case R.id.add_back:
                add_back();
                break;
        }
    }

    private void add_back() {
        Intent intent = new Intent(AddActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void add_sure() {

        String name=null;
        String psw=null;
        String pswagain=null;
        String phone=null;
        String email = null;
        String[] sex = new String[0];
        try {
            sex = new String[]{URLEncoder.encode("男","utf-8")};
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String[] finalSex = sex;
//        String add_editText2=null;//身份证号码
        String regExp = "^[1][3,4,5,8][0-9]{9}$";
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";//qq邮箱
        try {
            name = URLEncoder.encode(add_name.getText().toString().trim(),"utf-8");
            psw = URLEncoder.encode(add_psw.getText().toString().trim(),"utf-8");
            pswagain=URLEncoder.encode(add_psw_again.getText().toString().trim(),"utf-8");
            phone=URLEncoder.encode(add_phone.getText().toString().trim(),"utf-8");
            email=URLEncoder.encode(add_email.getText().toString().trim(),"utf-8");
//            add_editText2=URLEncoder.encode(editText2.getText().toString().trim(),"utf-8");


            check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.check_box1:
                            try {
                                finalSex[0] = URLEncoder.encode("女","utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            break;
                        case R.id.check_box:
                            try {
                                finalSex[0] = URLEncoder.encode("男","utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(name!=""&&psw!=""&&pswagain!=""&&phone!=""&&email!="") {
            if (add_psw.getText().toString().trim().equals(add_psw_again.getText().toString().trim()) == false) {
                Toast.makeText(getApplicationContext(), "两次密码不同，请重新输入", Toast.LENGTH_SHORT).show();
                add_psw_again.setText("");
            } else if (Pattern.compile(regExp).matcher(phone).matches() == false) {
                Toast.makeText(getApplicationContext(), "手机号码错误，请重新输入", Toast.LENGTH_SHORT).show();
                add_phone.setText("");

            }
            else if(Pattern.compile(regex).matcher(add_email.getText().toString().trim()).matches()==false){
                Toast.makeText(getApplicationContext(),"邮箱输入不正确",Toast.LENGTH_SHORT).show();
                add_email.setText("");
            }
            else {
                int flag=0;
                RequestParams params = new RequestParams(Constent.URL + "ww/getInformation?name=" + name + "&psw=" + psw + "&flag="+flag);
                final String finalName = name;
                final String finalPsw = psw;
                final String finalPhone = phone;
                final String finalEmail = email;
                final String finalsex = finalSex[0];
                x.http().get(params, new Callback.CacheCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        if (result.length() > 0) {
                            Toast.makeText(AddActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
                        } else {

                            RequestParams p = new RequestParams(Constent.URL + "ww/addInformtion?name=" + finalName + "&psw=" + finalPsw + "&sex=" + "" + finalsex + "&phone=" + "" + finalPhone + "&email=" + "" + finalEmail + "");
                            x.http().get(p, new Callback.CacheCallback<String>() {
                                @Override
                                public void onSuccess(String result) {

                                    if (result.length() > 0) {
                                        Intent intent = new Intent(AddActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
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

                                @Override
                                public boolean onCache(String result) {
                                    return false;
                                }
                            });
//
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(Callback.CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    @Override
                    public boolean onCache(String result) {
                        return false;
                    }
                });
            }
        }else{
            Toast.makeText(getApplicationContext(),"不能为空",Toast.LENGTH_SHORT).show();
        }

    }
}
