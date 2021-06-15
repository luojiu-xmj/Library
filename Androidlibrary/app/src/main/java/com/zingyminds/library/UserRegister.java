package com.zingyminds.library;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zingyminds.library.UserDao;
import com.zingyminds.library.R;

public class UserRegister extends AppCompatActivity{
    private EditText new_username;//注册页面的用户名输入框
    private EditText new_password;//注册页面的密码输入框
    private CheckBox ispassword;//显示密码按钮

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        new_username=(EditText)findViewById(R.id.newusername_text);//创建注册页面的用户名输入框对象
        new_password=(EditText)findViewById(R.id.newpassword_text);//创建注册页面的密码输入框对象
        ispassword=(CheckBox)findViewById(R.id.new_password_checkbook);//创建显示密码按钮对象
        ischeck(ispassword);
    }

    public void goback(View v) {
        onBackPressed();
    }
    /*显示密码的点击操作*/
    private void ischeck(CheckBox passwordCheck){
        passwordCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //显示明文，即设置为可见的密码
                    new_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    //不显示明文，即设置为不可见的密码
                    new_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void register(View view){
        final String cname = new_username.getText().toString();
        final String cpassword = new_password.getText().toString();


        final User user = new User();
        user.setName(cname);
        user.setPassword(cpassword);

        new Thread(){
            @Override
            public void run() {

                int msg = 0;

                UserDao userDao = new UserDao();

                User uu = userDao.findUser(user.getName());

                if(uu != null&&!cname.equals("")){
                    msg = 1;
                }

                boolean flag = userDao.register(user);
                if(flag&&!cname.equals("")&&!cpassword.equals("")){
                    msg = 2;
                }
                hand.sendEmptyMessage(msg);

            }
        }.start();


    }
    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"请输入完整的注册信息",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"该用户名已经存在",Toast.LENGTH_LONG).show();
                new_username.setText("");
                new_username.requestFocus();

            }
            if(msg.what == 2)
            {
                //startActivity(new Intent(getApplication(),MainActivity.class));
                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(UserRegister.this,UserLogin.class);
                startActivity(intent);
                finish();
            }

        }
    };


}