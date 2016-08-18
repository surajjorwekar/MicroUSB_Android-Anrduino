package com.readhtml.surajjorwekar.microu.Applications;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.readhtml.surajjorwekar.microu.R;

public class HospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital);


        //-------- Creating Font object ------------------------
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        TextView txt1 = (TextView) findViewById(R.id.idoc);
        TextView txt2 = (TextView) findViewById(R.id.ical);
        TextView txt3 = (TextView) findViewById(R.id.idec);
        TextView txt4 = (TextView) findViewById(R.id.iapp);
        TextView txt5 = (TextView) findViewById(R.id.ibat);
        TextView txt6 = (TextView) findViewById(R.id.imed);
        TextView txt7 = (TextView) findViewById(R.id.icon);

        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);
        txt5.setTypeface(font);
        txt6.setTypeface(font);
        txt7.setTypeface(font);
        //---------------------------------------------------------




        //----------- Initiate textfields--------------------------
        Intent i = getIntent();
        if (i!=null) {
            String[] strinData= i.getStringArrayExtra("name");

            //TextView name = (TextView) findViewById(R.id.hname);

            ((TextView) findViewById(R.id.indate)).setText(strinData[1]);
            ((TextView) findViewById(R.id.doctor)).setText(strinData[2]);
            ((TextView) findViewById(R.id.app)).setText(strinData[3]);
            ((TextView) findViewById(R.id.dis)).setText(strinData[4]);
            ((TextView) findViewById(R.id.age)).setText(strinData[5]);
            ((TextView) findViewById(R.id.weight)).setText(strinData[6]);
            ((TextView) findViewById(R.id.height)).setText(strinData[7]);
            ((TextView) findViewById(R.id.insuff)).setText(strinData[8]);
            ((TextView) findViewById(R.id.medi)).setText(strinData[9]);
            ((TextView) findViewById(R.id.emergency)).setText(strinData[10]);
        }
        //---------------------------------------------------------
    }
}
