package com.example.iotproject;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                TextView date_text=(TextView)findViewById(R.id.date);
                TextView time_text=(TextView)findViewById(R.id.time);
                Date calendar= Calendar.getInstance().getTime();
                SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String day_of_week=new SimpleDateFormat("EEEE",Locale.ENGLISH).format(calendar.getTime());
                String dates=df.format(calendar);
                date_text.setText(dates +"   "+day_of_week);
                SimpleDateFormat tf=new SimpleDateFormat("HH:mm a", Locale.getDefault());
                String times=tf.format(calendar);
                time_text.setText(times);
                handler.postDelayed(this,0);
            }
        });

    }

    public void world_corona(View v)
    {
        Intent intent=new Intent(this,world_nations_corona_list.class);
        startActivity(intent);
    }
    public void india_corona(View v)
    {
        Intent intent=new Intent(this,india_states_corona.class);
        startActivity(intent);
    }
    public void doctor(View v)
    {
        Intent intent=new Intent(this,Doctor.class);
        startActivity(intent);
    }
    public void self_checking(View v)
    {
        Intent intent=new Intent(this,Self_Checking.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
