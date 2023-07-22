package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class world_nations_corona_list extends AppCompatActivity {

    Context context=this;
    private RequestQueue mrequestQueue;
    TextView world_newconfirmedt,world_newdeatht,world_newrecoveredt,world_totalconfirmedt,world_totaldeatht,world_totalrecoveredt;
    LinearLayout linearLayout_corona;

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_nations_corona_list);
        mrequestQueue = Volley.newRequestQueue(this);

        world_newconfirmedt=(TextView)findViewById(R.id.world_newconfirmed);
        world_newdeatht=(TextView)findViewById(R.id.world_newdeath);
        world_newrecoveredt=(TextView)findViewById(R.id.world_newrecovered);
        world_totalconfirmedt=(TextView)findViewById(R.id.world_totalconfirmed);
        world_totaldeatht=(TextView)findViewById(R.id.world_totaldeath);
        world_totalrecoveredt=(TextView)findViewById(R.id.world_totalrecovered);

        linearLayout_corona=(LinearLayout)findViewById(R.id.linearlayout_world_nations_corona);
        go();

    }
    public void go()
    {
        final ProgressDialog progress=new ProgressDialog(this);
        progress.setCancelable(true);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.setProgressStyle(progress.STYLE_SPINNER);
        progress.show();
        String url="https://api.covid19api.com/summary";

        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progress.dismiss();
                            linearLayout_corona.setVisibility(View.VISIBLE);
                            JSONObject global=response.getJSONObject("Global");
                            String world_new_confirmed=global.getString("NewConfirmed");
                            world_newconfirmedt.setText("NewConfirmed:"+world_new_confirmed);
                            String world_new_death=global.getString("NewDeaths");
                            world_newdeatht.setText("NewDeaths:"+world_new_death);
                            String world_new_recovered=global.getString("NewRecovered");
                            world_newrecoveredt.setText("NewRecovered:"+world_new_recovered);
                            String world_total_confirmed=global.getString("TotalConfirmed");
                            world_totalconfirmedt.setText("TotalConfirmed:"+world_total_confirmed);
                            String world_total_death=global.getString("TotalDeaths");
                            world_totaldeatht.setText("TotalDeaths:"+world_total_death);
                            String world_total_recovered=global.getString("TotalRecovered");
                            world_totalrecoveredt.setText("TotalRecovered:"+world_total_recovered);

                            JSONArray jsonArray=response.getJSONArray("Countries");
                            LinearLayout linearLayout_main=(LinearLayout)findViewById(R.id.linearlayout);
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                LinearLayout l1 = new LinearLayout(context);
                                LinearLayout.LayoutParams paramsis = new LinearLayout.LayoutParams(702,290);
                                l1.setLayoutParams(paramsis);
                                paramsis.setMargins(5,5,5,5);
                                l1.setOrientation(LinearLayout.HORIZONTAL);
                                l1.setBackgroundColor(getResources().getColor(R.color.linearlayout3));


                                LinearLayout l2 = new LinearLayout(context);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,290);
                                l2.setLayoutParams(params);
                                l2.setGravity(Gravity.CENTER);
                                l2.setOrientation(LinearLayout.VERTICAL);
                                TextView countryname_text=new TextView(context);
                                String countryname = jsonObject.getString("Country");
                                countryname_text.setTextColor(getResources().getColor(R.color.countryname_textview_color));
                                countryname_text.setText(countryname);
                                if(countryname.length()>23)
                                { countryname_text.setTextSize(19); }
                                else {
                                    countryname_text.setTextSize(25); }
                                countryname_text.setGravity(Gravity.CENTER);
                                l2.addView(countryname_text);
                                String countrycode = jsonObject.getString("CountryCode");
                                ImageView imageView = new ImageView(context);
                                String urlopen="https://www.countryflags.io/";
                                String urlending="/flat/64.png";
                                String imageUrl = urlopen+countrycode+urlending;
                                Picasso.get().load(imageUrl).into(imageView);
                                imageView.setLayoutParams(new LinearLayout.LayoutParams(310,170));
                                l2.addView(imageView);

                                l1.addView(l2);

                                LinearLayout l3 = new LinearLayout(context);
                                LinearLayout.LayoutParams paramses = new LinearLayout.LayoutParams(410, 290);
                                l3.setLayoutParams(paramses);
                                paramses.setMargins(5,0,0,0);
                                l3.setOrientation(LinearLayout.VERTICAL);

                                TextView newconfirmed_text=new TextView(context);
                                String newconfirmed = jsonObject.getString("NewConfirmed");
                                newconfirmed_text.setText("New Confirmed:"+newconfirmed);
                                newconfirmed_text.setTextSize(17);
                                l3.addView(newconfirmed_text);
                                TextView newdeath_text=new TextView(context);
                                String newdeath = jsonObject.getString("NewDeaths");
                                newdeath_text.setText("New Deaths:"+newdeath);
                                newdeath_text.setTextSize(17);
                                l3.addView(newdeath_text);
                                TextView newrecovered_text=new TextView(context);
                                String newrecovered = jsonObject.getString("NewRecovered");
                                newrecovered_text.setText("New Recovered:"+newrecovered);
                                newrecovered_text.setTextSize(17);
                                l3.addView(newrecovered_text);
                                TextView totalconfirmed_text=new TextView(context);
                                String totalconfirmed = jsonObject.getString("TotalConfirmed");
                                totalconfirmed_text.setText("Total Confirmed:"+totalconfirmed);
                                totalconfirmed_text.setTextSize(17);
                                l3.addView(totalconfirmed_text);
                                TextView totaldeath_text=new TextView(context);
                                String totaldeath = jsonObject.getString("TotalDeaths");
                                totaldeath_text.setText("Total Deaths:"+totaldeath);
                                totaldeath_text.setTextSize(17);
                                l3.addView(totaldeath_text);
                                TextView totalrecovered_text=new TextView(context);
                                String totalrecovered = jsonObject.getString("TotalRecovered");
                                totalrecovered_text.setText("Total Recovered:"+totalrecovered);
                                totalrecovered_text.setTextSize(17);
                                l3.addView(totalrecovered_text);

                                l1.addView(l3);

                                linearLayout_main.addView(l1);
                            }

                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace(); }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); }
        });

        mrequestQueue.add(request);
    }



}
