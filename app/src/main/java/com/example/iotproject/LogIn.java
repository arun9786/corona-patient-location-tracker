package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    EditText email,password;
    SQLiteDatabase database;
    String databaseemail,databasepassword;
    String query;

    int passwordshowhide=0;
    SharedPreferences save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.editpassword);

        save= this.getSharedPreferences("Log in", Context.MODE_PRIVATE);
        String Emails=save.getString("Emails","");
        String Passwords=save.getString("Passwords","");
        if(Emails!=null && Passwords!=null)
        {
            email.setText(Emails);
            password.setText(Passwords);
        }

        database=openOrCreateDatabase("hello",0,null);
        query="CREATE TABLE IF NOT EXISTS pranciya(name VARCHAR(25),email VARCHAR(20),phone VARCHAR(20),password VARCHAR(15))";
        database.execSQL(query);
    }

    public void login(View v)
    {
        String emails=email.getText().toString();
        String passwords=password.getText().toString();

        String query1="SELECT  *from pranciya";
        Cursor cursor=database.rawQuery(query1,null);

        if(!emails.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emails).matches())
        {
            if(!passwords.isEmpty())
            {
                while ((cursor.moveToNext()))
                {
                    databaseemail=cursor.getString(cursor.getColumnIndex("email"));
                    databasepassword=cursor.getString(cursor.getColumnIndex("password"));
                }
                if(emails.equals(databaseemail) && passwords.equals(databasepassword))
                {
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(this,"Welcome to Corona Awarness App",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Sorry your's information is not valid",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this,"Please Enter Valid Password",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Please Enter Valid Email Address",Toast.LENGTH_SHORT).show();
        }
    }

    public void signup(View v)
    {
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);
    }

    public void forgotpassword(View v)
    {
        Intent intent=new Intent(this,ForgotPassword.class);
        startActivity(intent);
    }

    public void showpassword(View v)
    {
        EditText editText=(EditText)findViewById(R.id.editpassword);
        TextView textView=(TextView)findViewById(R.id.showhideassword);
        if(passwordshowhide%2!=0)
        {
            textView.setText(" Show Password ");
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordshowhide++;
        }
        else
        {
            textView.setText(" Hide Password ");
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordshowhide++;
        }
    }

    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning_icon);
        builder.setTitle("Warning");
        builder.setMessage("Do you want exit from this app");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


}
