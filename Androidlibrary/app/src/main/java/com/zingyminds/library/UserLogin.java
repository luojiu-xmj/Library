package com.zingyminds.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.zingyminds.library.R;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin extends AppCompatActivity{
    private EditText username; //用户名输入框
    private EditText password; //密码输入框
    private CheckBox passwordCheck; //显示密码复选框
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verifyStoragePermissions(this);
        username = (EditText) findViewById(R.id.username_text); //创建用户名输入框对象
        password = (EditText) findViewById(R.id.password_text); //创建密码输入框对象
        passwordCheck = (CheckBox) findViewById(R.id.password_checkbook); //创建显示密码复选框对象
        ischeck(passwordCheck); //设置密码显示或不显示
    }

    /**
     * 设置显示密码的点击操作
     */
    private void ischeck(CheckBox passwordCheck) {
        passwordCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //显示明文，即设置为可见的密码
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //不显示明文，即设置为不可见的密码
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }


    public void gotoregister(View v) {
        Intent intent = new Intent(UserLogin.this, UserRegister.class);
        startActivity(intent);
    }


    public void login(View view){
        new Thread(){
            @Override
            public void run() {
                UserDao userDao = new UserDao();
                boolean aa = userDao.login(username.getText().toString(),password.getText().toString());
                int msg = 0;
                if(aa){
                    msg = 1;
                }
                hand1.sendEmptyMessage(msg);
            }
        }.start();
    }
    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserLogin.this, MainActivity.class);
                intent.putExtra("username", username.getText().toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
            }
        }
    };


    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
