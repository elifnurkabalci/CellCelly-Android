package com.i2i.cellcelly.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.i2i.cellcelly.R;
import com.i2i.cellcelly.connect.RetrofitClientInstance;
import com.i2i.cellcelly.model.PackageBalanceRemaining;
import com.i2i.cellcelly.model.PackageInfoRequest;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreen extends AppCompatActivity {

    int voiceAmount, smsAmount, dataAmount,price,duration;
    int voiceRemaining,smsRemaining,dataRemaining;
    int voiceUsage,dataUsage,smsUsage;
    TextView textViewMsisdn,textViewPackageName,textViewDuraiton;
    TextView textdata, textsms, textvoice;
    String packageName;
    private Button buttonQuit;
    Button buttonChangePassword;

    PieChart pieChartdata, pieChartvoice, pieChartsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        textViewMsisdn = findViewById(R.id.textViewPhoneNumber);
        textViewPackageName = findViewById(R.id.textViewPackageName);
        textViewDuraiton = findViewById(R.id.textViewDuraiton);
        pieChartvoice = findViewById(R.id.piechartvoice);
        pieChartsms = findViewById(R.id.piechartsms);
        pieChartdata = findViewById(R.id.piechartdata);


        buttonChangePassword = findViewById(R.id.buttonChange);
        Intent intent = getIntent();
        String edtMsisdn = intent.getStringExtra("msisdn");
        textViewMsisdn.setText("0"+edtMsisdn);

        buttonChangePassword.setOnClickListener(v -> {
            Intent nextPage= new Intent(getApplicationContext(), ChangePasswordScreen.class);
            startActivity(nextPage);
        });
        Call<PackageBalanceRemaining> packageBalanceRemainingCall = RetrofitClientInstance.getBalanceRemaining().getPackageBalanceRemaining(edtMsisdn);
        packageBalanceRemainingCall.enqueue(new Callback<PackageBalanceRemaining>() {
            @Override
            public void onResponse(Call<PackageBalanceRemaining> call, Response<PackageBalanceRemaining> response) {
                if(response.isSuccessful()){
                    PackageBalanceRemaining packageBalanceRemaining = response.body();
                    dataRemaining = packageBalanceRemaining.getData();
                    voiceRemaining = packageBalanceRemaining.getVoice();
                    smsRemaining = packageBalanceRemaining.getSms();
                    price = packageBalanceRemaining.getPrice();

                    textdata = findViewById(R.id.dataRemaining);
                    textsms = findViewById(R.id.smsRemaining);
                    textvoice = findViewById(R.id.voiceRemaining);

                    Call<PackageInfoRequest> packageInfoRequestCall = RetrofitClientInstance.getPackageInfo().getPackageInfo(edtMsisdn);
                    packageInfoRequestCall.enqueue(new Callback<PackageInfoRequest>() {
                        @Override
                        public void onResponse(Call<PackageInfoRequest> call1, Response<PackageInfoRequest> response1) {
                            if(response1.isSuccessful()){
                                PackageInfoRequest packageInfoRequest = response1.body();
                                dataAmount = packageInfoRequest.getAmountData();
                                voiceAmount = packageInfoRequest.getAmountVoice();
                                smsAmount = packageInfoRequest.getAmountSms();
                                packageName = packageInfoRequest.getPackageName();
                                duration = packageInfoRequest.getDuration();

                                textdata.setText(String.valueOf(dataAmount));
                                textsms.setText(String.valueOf(smsAmount));
                                textvoice.setText(String.valueOf(voiceAmount));

                                //textdata.setText(packageInfoRequest.getAmountData());
                                String edtduraiton = String.valueOf(duration);
                                textViewDuraiton.setText("30");
                                textViewPackageName.setText(packageName);

                                //Bunlara ekleme yapılacak cardViewlar içinde
                                dataUsage = dataAmount -(dataRemaining);
                                voiceUsage = voiceAmount -voiceRemaining;
                                smsUsage = smsAmount -smsRemaining;

                                String vremaining = String.valueOf(voiceRemaining);
                                String vusage = String.valueOf(voiceUsage);
                                pieChartvoice.addPieSlice(
                                        new PieModel(
                                                "voice",
                                                Integer.parseInt(vusage),
                                                Color.parseColor("#4082c7")));
                                pieChartvoice.addPieSlice(
                                        new PieModel(
                                                "emptyvoice",
                                                Integer.parseInt(vremaining),
                                                Color.parseColor("#FFFFFF")));

                                String dremaining = String.valueOf(dataRemaining);
                                String dusage = String.valueOf(dataUsage);
                                pieChartdata.addPieSlice(
                                        new PieModel(
                                                "data",
                                                Integer.parseInt(dusage),
                                                Color.parseColor("#4082c7")));
                                pieChartdata.addPieSlice(
                                        new PieModel(
                                                "emptydata",
                                                Integer.parseInt(dremaining),
                                                Color.parseColor("#FFFFFF")));

                                String sremaining = String.valueOf(smsRemaining);
                                String susage = String.valueOf(smsUsage);
                                pieChartsms.addPieSlice(
                                        new PieModel(
                                                "sms",
                                                Integer.parseInt(susage),
                                                Color.parseColor("#4082c7")));
                                pieChartsms.addPieSlice(
                                        new PieModel(
                                                "emptysms",
                                                Integer.parseInt(sremaining),
                                                Color.parseColor("#FFFFFF")));


                                buttonQuit = (Button) findViewById(R.id.buttonQuit);
                                buttonQuit.setOnClickListener(view -> {
                                    //Intent nextPage = new Intent(MainScreen.this, LoginScreen.class);
                                    //startActivity(nextPage);
                                    finish();
                                });
                            }
                        }
                        @Override
                        public void onFailure(Call<PackageInfoRequest> call, Throwable t) {
                            System.out.println(t.getLocalizedMessage());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<PackageBalanceRemaining> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}
