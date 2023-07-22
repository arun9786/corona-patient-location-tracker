package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Self_Checking extends AppCompatActivity {

    CheckBox cough,fever,sneezing,breathing,smelling,diabetes,hypertension,lungdisease,heartdisease,kidneydisease,contact_patient,healthcare_worker;
    RadioGroup gender,age,travelling;

    TextView textView;
    LinearLayout linearLayout;

    int genderid,ageid,travelid;

    int sum=0,found=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self__checking);

        cough=(CheckBox)findViewById(R.id.checkbox_selfchecking_cough);
        fever=(CheckBox)findViewById(R.id.checkbox_selfchecking_fever);
        sneezing=(CheckBox)findViewById(R.id.checkbox_selfchecking_sneezing);
        breathing=(CheckBox)findViewById(R.id.checkbox_selfchecking_breathing);
        smelling=(CheckBox)findViewById(R.id.checkbox_selfchecking_losssmell);
        diabetes=(CheckBox)findViewById(R.id.checkbox_selfchecking_diabetes);
        hypertension=(CheckBox)findViewById(R.id.checkbox_selfchecking_hypertension);
        lungdisease=(CheckBox)findViewById(R.id.checkbox_selfchecking_lungdisease);
        heartdisease=(CheckBox)findViewById(R.id.checkbox_selfchecking_heartdisease);
        kidneydisease=(CheckBox)findViewById(R.id.checkbox_selfchecking_kidneydisorder);
        contact_patient=(CheckBox)findViewById(R.id.checkbox_selfchecking_interactwith_covidpatient);
        healthcare_worker=(CheckBox)findViewById(R.id.checkbox_selfchecking_healthcare_worker);

        gender=(RadioGroup)findViewById(R.id.radiogroup_selfchecking_gender);
        age=(RadioGroup)findViewById(R.id.radiogroup_selfchecking_age);
        travelling=(RadioGroup)findViewById(R.id.radiogroup_selfchecking_travelling);

        linearLayout=(LinearLayout)findViewById(R.id.linearlayout_selfchecking_textview);
        textView=(TextView)findViewById(R.id.textview_selfchecking_textview);

    }

    public void Check(View v)
    {
        sum=0;
        found=0;
        genderid=gender.getCheckedRadioButtonId();
        ageid=age.getCheckedRadioButtonId();
        travelid=travelling.getCheckedRadioButtonId();

        if(genderid==-1)
        {
            Toast.makeText(this,"Please Select Your Gender",Toast.LENGTH_SHORT).show();
        }else{found++;
            if(genderid==2131230880)
            {
                sum+=15;
            }else if(genderid==2131230879){
                sum+=10;
            }else if(genderid==2131230882){
                sum+=15;
            }

            if(ageid==-1)
            {
                Toast.makeText(this,"Please Select Your Age",Toast.LENGTH_SHORT).show();
            }else{found++;
                if(ageid==2131230878)
                {
                    sum+=15;
                }else if(ageid==2131230877){
                    sum+=10;
                }else if(ageid==2131230881){
                    sum+=20;
                }

                if(travelid==-1)
                {
                    Toast.makeText(this,"Please Select have you traveled anywhere internationally in the last 15-45 days ",Toast.LENGTH_SHORT).show();
                }else{found++;
                    if(travelid==2131230884)
                    {
                        sum+=230;
                    }else if(travelid==2131230883){
                        sum+=0;
                    }
                }
            }
        }


        if(cough.isChecked())
        {
            sum+=70;
        }
        if(fever.isChecked())
        {
            sum+=90;
        }
        if(sneezing.isChecked())
        {
            sum+=30;
        }
        if(breathing.isChecked())
        {
            sum+=35;
        }
        if(smelling.isChecked())
        {
            sum+=80;
        }
        if(diabetes.isChecked())
        {
            sum+=20;
        }
        if(hypertension.isChecked())
        {
            sum+=10;
        }
        if(lungdisease.isChecked())
        {
            sum+=90;
        }
        if(heartdisease.isChecked())
        {
            sum+=80;
        }
        if(kidneydisease.isChecked())
        {
            sum+=70;
        }
        if(contact_patient.isChecked())
        {
            sum+=230;
        }
        if(healthcare_worker.isChecked())
        {
            sum+=150;
        }

        if(sum>=250 && found>=3) {

            linearLayout.setVisibility(View.VISIBLE);
            textView.setHeight(810);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.linearlayout_selfchecking_textview_red));
            textView.setText("Thank you for taking this assessment.\n\n" +
                    "You are advised for testing as your infection risk is high. Please call the toll-free help line 1075 to schedule your testing. If the information " +
                    "provided by you is accurate, it indicates that you are either unwell or at risk. Your test results and location history need to be sent to the Health inistry to help you and help " +
                    "identify potential hotspots where infection may be spreading.\n\n" +
                    "List of testing centers:\n" +
                    "https://icmr.nic.in/node/39071\n\n" +
                    "Pleas wait\n" +
                    "https:www.mohfw.gov.in/ \n" +
                    "for more information on testing and quarantine guidelines.");
        }else if(found>=3){
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.linearlayout_selfchecking_textview_green));
            textView.setHeight(570);
            textView.setText("Thank you for taking this assessment.\n\n" +
                    "Your infection risk is low. We recommend that you stay at home to avoidany chance of exposure to the Novel Coronavirus.\n\n" +
                    "Retake the Self-Assessment Test if you develop symptoms or come in contact with a COVID-10 confirmed patient\n\n" +
                    "Do Visit\n" +
                    "https://www.mohfw.gov.in/ \nfor more information. ");
        }

    }
}
