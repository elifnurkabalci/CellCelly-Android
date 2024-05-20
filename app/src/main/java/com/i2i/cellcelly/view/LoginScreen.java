package com.i2i.cellcelly.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.i2i.cellcelly.R;
import com.i2i.cellcelly.connect.RetrofitClientInstance;
import com.i2i.cellcelly.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import org.apache.commons.validator.routines.EmailValidator;


public class LoginScreen extends AppCompatActivity {
    EditText msisdn, password;
    Button buttonLogin;
    Button buttonSignUp;
    Button buttonForgotPassword;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
/*
        Intent intent = new Intent(getApplicationContext(),MainScreen.class);
        startActivity(intent);
        */


        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        msisdn = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonForgotPassword = findViewById(R.id.buttonForgot);
        checkBox = findViewById(R.id.checkBox);


        buttonForgotPassword.setOnClickListener(v -> {
            Intent nextPage= new Intent(LoginScreen.this, ForgotPasswordScreen.class);
            startActivity(nextPage);
        });

        buttonSignUp.setOnClickListener(view -> {
            Intent nextPage= new Intent(LoginScreen.this, RegisterScreen.class);
            startActivity(nextPage);
        });
        //Bunları sil
//        msisdn.setText("5544614538");
  //      password.setText("mysecretpassword");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(msisdn.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginScreen.this,"Telefon numarası veya şifre eksik", Toast.LENGTH_LONG).show();
                }else{
                    login_user(msisdn.getText().toString(),password.getText().toString());
                }
            }
        });
    }
    public void login_user(String edtmsisdn,String edtpassword){
        Call<LoginRequest> loginRequestCall = RetrofitClientInstance.getUserService().login(edtmsisdn,edtpassword);

        loginRequestCall.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                if(response.isSuccessful()){

                    Toast.makeText(LoginScreen.this,"Giriş Başarılı", Toast.LENGTH_LONG).show();
                    LoginRequest loginRequest = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(checkBox.isChecked()){
                                sharedPreferences.edit().putString("phoneNumber", String.valueOf(msisdn)).apply();
                                sharedPreferences.edit().putString("password",password.getText().toString()).apply();
                            }
                            Intent intent = new Intent(LoginScreen.this,MainScreen.class);
                            startActivity(intent.putExtra("msisdn", edtmsisdn));
                            finish();
                        }
                    },700);
                }else{
                    Toast.makeText(LoginScreen.this,"Kullanıcı adı veya şifre hatalı", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
                Log.e("TAG",t.getLocalizedMessage().toString());
                Toast.makeText(LoginScreen.this,"Hata!  "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}

