package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class OTPverification extends AppCompatActivity {

    SQLiteDatabase database;
    String query;
    String names,emails,phones,passwords;
    SharedPreferences save;
    SharedPreferences.Editor editor;
    Random random;
    int rollResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        Bundle bundle=getIntent().getExtras();
        names=bundle.getString("name","");
        emails=bundle.getString("email","");
        names=bundle.getString("phone","");
        passwords=bundle.getString("password","");

        database=openOrCreateDatabase("hello",0,null);
        query="CREATE TABLE IF NOT EXISTS pranciya(name VARCHAR(25),email VARCHAR(20),phone VARCHAR(20),password VARCHAR(15))";
        database.execSQL(query);

        random=new Random();
        rollResult=random.nextInt(999999)+1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setAutoCancel(false);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentText("OTP Verification");
        builder.setContentTitle("Corona Awarness App");

        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmsound);

        builder.setLights(Color.BLUE, 500, 500);
        long[] pattern = {0, 100, 200, 300, 400, 500, 500, 500, 500};
        builder.setVibrate(pattern);

        Intent intent = new Intent(this, Otpverification_notification.class);
        intent.putExtra("message",String.valueOf(rollResult));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        save=this.getSharedPreferences("Log in",Context.MODE_PRIVATE);
        editor=save.edit();
        editor.putString("OTPVerification",String.valueOf(rollResult));
        editor.commit();
    }

    public void verify(View v)
    {
        EditText editText=(EditText)findViewById(R.id.otp);
        String otp=editText.getText().toString();


        save=this.getSharedPreferences("Log in",Context.MODE_PRIVATE);
        String oldotp=save.getString("OTPVerification","");
        if(otp.equals(oldotp)) {
            query = "INSERT INTO pranciya(name,email,phone,password) VALUES('" + names + "','" + emails + "','" + phones + "','" + passwords + "')";
            database.execSQL(query);
            Toast.makeText(this, "Sign up successfull", Toast.LENGTH_SHORT).show();

            editor.putString("Emails", emails);
            editor.putString("Passwords", passwords);
            editor.putString("Phone",phones);
            editor.commit();
        }

        else
        {
            Toast.makeText(this, "OTP is Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void resend(View v)
    {

        random=new Random();
        rollResult=random.nextInt(999999)+1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setAutoCancel(false);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentText("OTP Verification");
        builder.setContentTitle("Corona Awarness App");

        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmsound);

        builder.setLights(Color.BLUE, 500, 500);
        long[] pattern = {0, 100, 200, 300, 400, 500, 500, 500, 500};
        builder.setVibrate(pattern);

        Intent intent = new Intent(this, Otpverification_notification.class);
        intent.putExtra("message",String.valueOf(rollResult));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        save=this.getSharedPreferences("Log in",Context.MODE_PRIVATE);
        editor=save.edit();
        editor.putString("OTPVerification",String.valueOf(rollResult));
        editor.commit();

    }
}
