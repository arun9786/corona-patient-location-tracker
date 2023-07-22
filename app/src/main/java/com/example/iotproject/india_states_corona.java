package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;

public class india_states_corona extends AppCompatActivity {

    Context context=this;
    private RequestQueue mrequestQueue;
    TextView india_newconfirmedt,india_newdeatht,india_newrecoveredt,india_totalconfirmedt,india_totaldeatht,india_totalrecoveredt;
    LinearLayout linearLayout_india_states;

    String[] string_states={"Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh","Delhi","Dadra and Nagar Haveli and Daman and Diu",
                    "Goa","Gujarat","Himachal Pradesh","Haryana","Jharkhand","Jammu and Kashmir","Karnataka","Kerala","Ladakh","Lakshadweep","Maharashtra",
                    "Meghalaya","Manipur","Madhya Pradesh","Mizoram","Nagaland","Odisha","Punjab","Puducherry","Rajasthan","Telangana","Tamil Nadu",
                     "Tripura","Uttar Pradesh","Uttarkhand","West Bengal"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_states_corona);

        mrequestQueue = Volley.newRequestQueue(this);

        india_newconfirmedt=(TextView)findViewById(R.id.world_newconfirmed);
        india_newdeatht=(TextView)findViewById(R.id.world_newdeath);
        india_newrecoveredt=(TextView)findViewById(R.id.world_newrecovered);
        india_totalconfirmedt=(TextView)findViewById(R.id.world_totalconfirmed);
        india_totaldeatht=(TextView)findViewById(R.id.world_totaldeath);
        india_totalrecoveredt=(TextView)findViewById(R.id.world_totalrecovered);

        linearLayout_india_states=(LinearLayout)findViewById(R.id.linearlayout_india_states_corona);
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

        String url="https://api.covid19india.org/state_district_wise.json";
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progress.dismiss();
                            linearLayout_india_states.setVisibility(View.VISIBLE);

                            for(int l=0;l<string_states.length;l++) {
                                JSONObject state = response.getJSONObject(string_states[l]);
                                JSONObject districtdata = state.getJSONObject("districtData");

                                JSONArray jsonArray = districtdata.names();
                                LinearLayout linearLayout_main = (LinearLayout) findViewById(R.id.linearlayout);
                                TextView state_name_text = new TextView(context);
                                state_name_text.setText(string_states[l]);
                                state_name_text.setGravity(Gravity.CENTER);
                                state_name_text.setTextSize(30);
                                state_name_text.setTextColor(Color.BLUE);
                                linearLayout_main.addView(state_name_text);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String name = jsonArray.getString(i);
                                    JSONObject jsonObject = districtdata.getJSONObject(name);


                                    LinearLayout l4 = new LinearLayout(context);
                                    LinearLayout.LayoutParams paramsises = new LinearLayout.LayoutParams(702, 80);
                                    l4.setLayoutParams(paramsises);
                                    l4.setGravity(Gravity.CENTER);
                                    paramsises.setMargins(5, 5, 5, 5);
                                    l4.setOrientation(LinearLayout.VERTICAL);
                                    l4.setBackgroundColor(getResources().getColor(R.color.linearlayout2));

                                    TextView districtname_text = new TextView(context);
                                    districtname_text.setText(name);
                                    districtname_text.setGravity(Gravity.CENTER);
                                    districtname_text.setTextSize(23);
                                    districtname_text.setTextColor(Color.WHITE);
                                    l4.addView(districtname_text);

                                    linearLayout_main.addView(l4);

                                    LinearLayout l1 = new LinearLayout(context);
                                    LinearLayout.LayoutParams paramsis = new LinearLayout.LayoutParams(702, 290);
                                    l1.setLayoutParams(paramsis);
                                    paramsis.setMargins(5, 5, 5, 5);
                                    l1.setOrientation(LinearLayout.HORIZONTAL);
                                    l1.setGravity(Gravity.CENTER);
                                    l1.setBackgroundColor(getResources().getColor(R.color.linearlayout3));

                                    LinearLayout l2 = new LinearLayout(context);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 250);
                                    l2.setLayoutParams(params);
                                    l2.setGravity(Gravity.CENTER);
                                    paramsis.setMargins(5, 1, 5, 5);
                                    l2.setOrientation(LinearLayout.VERTICAL);

                                    TextView totalcases_text = new TextView(context);
                                    totalcases_text.setText("Total Cases");
                                    totalcases_text.setTextSize(25);
                                    totalcases_text.setTextColor(Color.parseColor("#C43D12"));
                                    totalcases_text.setPadding(0, 0, 0, 5);
                                    totalcases_text.setGravity(Gravity.START);
                                    l2.addView(totalcases_text);

                                    TextView newconfirmed_text = new TextView(context);
                                    int newconfirmed = jsonObject.getInt("active");
                                    newconfirmed_text.setText("Active:" + newconfirmed);
                                    newconfirmed_text.setTextSize(19);
                                    newconfirmed_text.setTextColor(Color.WHITE);
                                    l2.addView(newconfirmed_text);
                                    TextView newdeath_text = new TextView(context);
                                    int newdeath = jsonObject.getInt("confirmed");
                                    newdeath_text.setText("Confirmed:" + newdeath);
                                    newdeath_text.setTextSize(19);
                                    newdeath_text.setTextColor(Color.WHITE);
                                    l2.addView(newdeath_text);
                                    TextView newrecovered_text = new TextView(context);
                                    int newrecovered = jsonObject.getInt("deceased");
                                    newrecovered_text.setText("Death:" + newrecovered);
                                    newrecovered_text.setTextSize(19);
                                    newrecovered_text.setTextColor(Color.WHITE);
                                    l2.addView(newrecovered_text);
                                    TextView newrecovereds_text = new TextView(context);
                                    int newrecovereds = jsonObject.getInt("recovered");
                                    newrecovereds_text.setText("Recovered:" + newrecovereds);
                                    newrecovereds_text.setTextSize(19);
                                    newrecovereds_text.setTextColor(Color.WHITE);
                                    l2.addView(newrecovereds_text);

                                    l1.addView(l2);


                                    LinearLayout l3 = new LinearLayout(context);
                                    LinearLayout.LayoutParams paramses = new LinearLayout.LayoutParams(300, 220);
                                    l3.setLayoutParams(paramses);
                                    paramses.setMargins(5, 1, 5, 5);
                                    l3.setGravity(Gravity.CENTER);
                                    l3.setOrientation(LinearLayout.VERTICAL);

                                    TextView newcases_text = new TextView(context);
                                    newcases_text.setText("New Cases");
                                    newcases_text.setTextColor(Color.parseColor("#C43D12"));
                                    newcases_text.setPadding(0, 0, 0, 10);
                                    newcases_text.setTextSize(25);
                                    newcases_text.setGravity(Gravity.START);
                                    l3.addView(newcases_text);


                                    l1.addView(l3);

                                    linearLayout_main.addView(l1);
                                }
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
