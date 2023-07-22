package com.example.iotproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText email,password,repassword;
    SQLiteDatabase database;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        database=openOrCreateDatabase("hello",0,null);
    }

    public void submit(View v)
    {
        String emails=email.getText().toString();
        String passwords=password.getText().toString();
        String repasswords=repassword.getText().toString();


        if(!emails.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emails).matches())
        {
            if(!passwords.isEmpty())
            {
                if(passwords.length()>6)
                {
                    if(passwords.equals(repasswords))
                    {
                        String query1="SELECT *FROM pranciya WHERE email='"+emails+"'";
                        Cursor cursor=database.rawQuery(query1,null);
                        if(cursor.moveToFirst())
                        {
                            database.execSQL("UPDATE pranciya SET email='"+emails+"',password='"+passwords+"' WHERE email='"+emails+"'");
                            Toast.makeText(this,"Password modified",Toast.LENGTH_SHORT).show();
                            finish();

                            SharedPreferences save = this.getSharedPreferences("Log in", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor;
                            editor = save.edit();
                            editor.putString("Emails", emails);
                            editor.putString("Passwords",passwords);
                            editor.commit();
                        }
                        else
                        {
                            Toast.makeText(this,"Account not Exist. Please SignUp Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Password and Retype passsword are not equal",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this,"Password length must be more than 6 letters",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this,"Enter new Password",Toast.LENGTH_SHORT).show();

            }

        }
        else
        {
            Toast.makeText(this,"Please Enter Valid Email Address",Toast.LENGTH_SHORT).show();
        }
    }

}
