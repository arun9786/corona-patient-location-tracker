package com.example.iotproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText name,email,pass,repassword,phone;
    CheckBox privacy;
    SQLiteDatabase database;
    String query,databasemail;
    int founddatabaseemail=0;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        privacy=(CheckBox)findViewById(R.id.checkbox_privacy);

        builder=new AlertDialog.Builder(this);

        database=openOrCreateDatabase("hello",0,null);
        query="CREATE TABLE IF NOT EXISTS pranciya(name VARCHAR(25),email VARCHAR(20),phone VARCHAR(20),password VARCHAR(15))";
        database.execSQL(query);
    }

    public void signup(View v)
    {
        founddatabaseemail=0;
        String names=name.getText().toString();
        String emails=email.getText().toString();
        String phones=phone.getText().toString();
        String passwords=pass.getText().toString();
        String repasswords=repassword.getText().toString();

        String regnames="\\p{Upper}(\\p{Lower}+\\s?)";


        String query1="Select * from pranciya";
        Cursor cursor=database.rawQuery(query1,null);


        if(!emails.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emails).matches())
        {
            while (cursor.moveToNext())
            {
                databasemail=cursor.getString(cursor.getColumnIndex("email"));
                if(databasemail.equals(emails))
                {
                    founddatabaseemail=1;
                }

            }
            if (founddatabaseemail!=1)
            {
                if(!names.isEmpty() && names.matches(regnames))
                {
                    if(!phones.isEmpty() && phones.length()==10)
                    {
                        if(!passwords.isEmpty() && passwords.length()>6)
                        {
                            if(!repasswords.isEmpty())
                            {
                                if (passwords.equals(repasswords))
                                {
                                    if (privacy.isChecked())
                                    {
                                        Intent intent=new Intent(this,OTPverification.class);
                                        intent.putExtra("email",emails);
                                        intent.putExtra("name",names);
                                        intent.putExtra("phone",phones);
                                        intent.putExtra("password",passwords);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(this,"Please Accept the Privacy and Policy conditions",Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else
                                {
                                    Toast.makeText(this,"Password and Retype password are not valid",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(this,"Please Enter Valid Retype password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(this,"Password length must be more than 6 leters",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Please Enter valid Phone No",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this,"Please Enter valid Name[Ex:Arun]",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this,"Email Address is already exists",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Please Valid Emial Address",Toast.LENGTH_SHORT).show();
        }
    }

}
