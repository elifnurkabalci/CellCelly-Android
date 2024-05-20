package com.i2i.cellcelly.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.i2i.cellcelly.R;

public class ForgotPasswordScreen extends AppCompatActivity {
    EditText textPhone;

    EditText textMail;

    EditText textSecurity;

    Button buttonSend;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        textPhone = findViewById(R.id.textPhoneNumber);
        textMail = findViewById(R.id.textEmail);
        textSecurity = findViewById(R.id.textSecurityKey);
        buttonSend = findViewById(R.id.buttonSendMail);

        buttonSend.setOnClickListener(view -> {
           if(checkTexts(textPhone.getText().toString(),textMail.getText().toString(),textSecurity.getText().toString())){
               Intent nextPage= new Intent(ForgotPasswordScreen.this, LoginScreen.class);
               startActivity(nextPage);
           }else{
               Toast.makeText(ForgotPasswordScreen.this,"Bazı Yerleri Boş Bıraktınız",Toast.LENGTH_LONG).show();
           }

        });
    }

    private boolean checkTexts(String phone, String email, String security){
        if(!phone.isEmpty() && !email.isEmpty() && !security.isEmpty()) {
            return true;
        }
        return false;
    }
}
