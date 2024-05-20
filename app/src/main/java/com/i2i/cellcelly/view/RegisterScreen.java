package com.i2i.cellcelly.view;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.i2i.cellcelly.R;
import com.i2i.cellcelly.connect.RetrofitClientInstance;
import com.i2i.cellcelly.model.PackageRequest;
import com.i2i.cellcelly.model.RegisterRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String msisdn;
    private int position = 0;
    private String email;
    private String name;
    private Integer packageId;
    private String password;
    private String surname;
    private String securityQuestion;
    private Spinner spinnerItem;
    private String[] listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        listData = getResources().getStringArray(R.array.tarifeler);

        spinnerItem = findViewById(R.id.spinner);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSecondName = findViewById(R.id.editTextSecondName);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        EditText editTextSecurityQuestion = findViewById(R.id.editTextSecurity);
        spinnerItem = findViewById(R.id.spinner);
        Button buttonSignIn = findViewById(R.id.buttonKaydol);

        if(spinnerItem != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listData);
            spinnerItem.setAdapter(adapter);
        }
        spinnerItem.setOnItemSelectedListener(this);

            listPackage();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position!=0){

                    msisdn = editTextPhoneNumber.getText().toString();
                    email = editTextEmail.getText().toString();
                    name = editTextName.getText().toString();
                    password = editTextRegisterPassword.getText().toString();
                    surname = editTextSecondName.getText().toString();
                    packageId = (int) spinnerItem.getSelectedItemId();
                    securityQuestion = editTextSecurityQuestion.getText().toString();

                    Map<String, Object> map = new HashMap<>();//hashmapping for request

                    if(hasAtAndDot(email)){
                        map.put("email",email);

                    }else{
                        Toast.makeText(getApplicationContext(),"@ veya . koymadınız yanlış mail adresi",Toast.LENGTH_LONG).show();
                    }
           //         if(isValidPassword(password)){
                        map.put("password",password);
             //       }else{
                 //       Toast.makeText(getApplicationContext(),"Şifreniz en az 1 büyük karakter 1 sembol ve en az 8 karakterli",Toast.LENGTH_LONG).show();
               //     }

                    map.put("MSISDN",msisdn);

                    map.put("name",name);
                    //Tekrardan bakılacak
                    position = packageId +1;
                    map.put("packageId",position);

                    map.put("surname",surname);
                    map.put("securityQuestion",securityQuestion);

                    Call<RegisterRequest> registerRequestCall = RetrofitClientInstance.getPackageId()
                            .register(map);
                    registerRequestCall.enqueue(new Callback<RegisterRequest>() {
                        @Override
                        public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(RegisterScreen.this,"Kayıt Başarılı!", Toast.LENGTH_LONG).show();
                                RegisterRequest registerRequest = response.body();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(RegisterScreen.this,LoginScreen.class));
                                    }
                                },700);
                            }else{
                                Toast.makeText(RegisterScreen.this,"Eksik alan bırakmayınız", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterRequest> call, Throwable t) {
                            Toast.makeText(RegisterScreen.this,"Hata!  "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        /*Button buttonAlreadyRegister;
        buttonAlreadyRegister = (Button) findViewById(R.id.buttonZatenUye);

        buttonAlreadyRegister.setOnClickListener(view -> {
            Intent nextPage = new Intent(RegisterScreen.this, LoginScreen.class);
            startActivity(nextPage);
        });
        */
    }
    private void listPackage(){//spinner items add
        //Spinner spinnerPackagePick = findViewById(R.id.spinnerPackagePick);
        Call<List<PackageRequest>> call = RetrofitClientInstance.getPackageIdName().getPackageId();
        call.enqueue(new Callback<List<PackageRequest>>() {
            @Override
            public void onResponse(Call<List<PackageRequest>> call, Response<List<PackageRequest>> response) {
                List<PackageRequest> PackageList = response.body();
                if(response.body()!=null){
                    assert PackageList != null;
                    String[] Packages = new String[PackageList.size()];

                    for(int i=0; i< PackageList.size(); i++){
                        Packages[i]= PackageList.get(i).getPackageName();

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(RegisterScreen.this, android.R.layout.simple_spinner_item, Packages);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinnerItem.setAdapter(spinnerArrayAdapter);
                    }

                }else{

                }

            }
            @Override
            public void onFailure(Call<List<PackageRequest>> call, Throwable t) {
                Toast.makeText(RegisterScreen.this,"hata !", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0){
            this.position = position;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean hasAtAndDot(String email) {
        if (email == null) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
    public boolean isValidPassword(String password) {
        // Şifrenin null veya boş olup olmadığını kontrol edin
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        // Düzenli ifade ile şifre kurallarını kontrol edin
        String regex = "^(?=.*[A-Z])(?=.*[@#$%^&+=!_*-]).{8,}$";

        return password.matches(regex);
    }


}
