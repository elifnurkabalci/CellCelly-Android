package com.i2i.cellcelly.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.i2i.cellcelly.R;
import com.i2i.cellcelly.connect.RetrofitClientInstance;
import com.i2i.cellcelly.model.RegisterRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordScreen extends AppCompatActivity{
    EditText textPassword;

    EditText textPasswordAgain;

    Button buttonChangePassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        textPassword = findViewById(R.id.editTextPassword);
        textPasswordAgain = findViewById(R.id.editTextPasswordAgain);
        buttonChangePassword = findViewById(R.id.buttonChange);

        buttonChangePassword.setOnClickListener(view -> {
           if(textPassword.getText().toString().equals(textPasswordAgain.getText().toString())){
                //Retrofit Kodu yazılacak
               Map<String, Object> map = new HashMap<>();//hashmapping for request
               map.put("password",textPassword.getText().toString());
               //get Package ID değil put olanı koyacaksın
               Call<RegisterRequest> registerRequestCall = RetrofitClientInstance.getPackageId()
                       .register(map);
               registerRequestCall.enqueue(new Callback<RegisterRequest>() {
                   @Override
                   public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                       if(response.isSuccessful()){

                           RegisterRequest registerRequest = response.body();

                           new Handler().postDelayed(new Runnable() {
                               @Override
                               public void run() {

                               }
                           },700);
                       }else{
                       }
                   }

                   @Override
                   public void onFailure(Call<RegisterRequest> call, Throwable t) {

                   }
               });
               Intent intent = new Intent(getApplicationContext(),MainScreen.class);
               startActivity(intent);
               finish();

           }else{
               Toast.makeText(getApplicationContext(),"Şifreler Uyumlu Değil",Toast.LENGTH_LONG).show();
           }

        });
    }
}
