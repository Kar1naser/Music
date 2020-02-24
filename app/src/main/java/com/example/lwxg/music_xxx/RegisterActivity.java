package com.example.lwxg.music_xxx;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regiest_et_user;
    private EditText regiest_et_pwd;
    private Button register_confirm;
    private Button register_back;
    private EditText regiest_et_pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        regiest_et_user = (EditText) findViewById(R.id.regiest_et_user);
        regiest_et_pwd = (EditText) findViewById(R.id.regiest_et_pwd);
        register_confirm = (Button) findViewById(R.id.register_confirm);

        register_confirm.setOnClickListener(this);
        register_back = (Button) findViewById(R.id.register_back);
        register_back.setOnClickListener(this);
        regiest_et_pwd2 = (EditText) findViewById(R.id.regiest_et_pwd2);
        regiest_et_pwd2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_confirm:
                submit();
                break;
            case R.id.register_back:
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String user = regiest_et_user.getText().toString().trim();
        String pwd = regiest_et_pwd.getText().toString().trim();
        String pwd2 = regiest_et_pwd2.getText().toString().trim();
        if (regiest_et_user.length() <= 0) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (regiest_et_pwd.length() <= 0) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd2)) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(pwd2)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("User", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("pwd", pwd);
        editor.commit();
        finish();

    }
}
