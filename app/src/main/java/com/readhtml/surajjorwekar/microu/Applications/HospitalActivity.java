package com.readhtml.surajjorwekar.microu.Applications;

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
    }
}
