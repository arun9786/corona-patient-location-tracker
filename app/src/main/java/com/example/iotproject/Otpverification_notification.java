package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class Otpverification_notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification_notification);

        TextView time=(TextView)findViewById(R.id.time_textview);
        TextView otp=(TextView)findViewById(R.id.otp_number);
        String otps=getIntent().getStringExtra("message");
        Date date=new Date();
        time.setText(String.valueOf(date));
        otp.setText("OTP:"+otps);
    }
}
