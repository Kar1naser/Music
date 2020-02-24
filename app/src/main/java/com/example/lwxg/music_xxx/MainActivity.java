package com.example.lwxg.music_xxx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText main_et_user;
    private EditText main_et_pwd;
    private Button main_login;
    private Button main_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_et_user = (EditText) findViewById(R.id.main_et_user);
        main_et_pwd = (EditText) findViewById(R.id.main_et_pwd);
        main_login = (Button) findViewById(R.id.main_login);

        main_login.setOnClickListener(this);
        main_register = (Button) findViewById(R.id.main_register);
        main_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_login:
                submit();
                break;
            case R.id.main_register:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void submit() {
        String user = main_et_user.getText().toString().trim();
        String pwd = main_et_pwd.getText().toString().trim();
        if (user.length() <= 0) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.length() <= 0) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        if (user.equals(sharedPreferences.getString("user", "")) && pwd.equals(sharedPreferences.getString("pwd", ""))) {
            startActivity(new Intent(MainActivity.this, MusicActivity.class));
        } else {
            Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
        }

    }
}
