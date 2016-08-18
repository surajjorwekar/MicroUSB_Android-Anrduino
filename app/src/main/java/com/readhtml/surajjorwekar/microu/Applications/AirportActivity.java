package com.readhtml.surajjorwekar.microu.Applications;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.readhtml.surajjorwekar.microu.R;

public class AirportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        TextView txt1 = (TextView) findViewById(R.id.iname);
        TextView txt2 = (TextView) findViewById(R.id.ical);
        TextView txt3 = (TextView) findViewById(R.id.igen);
        TextView txt4 = (TextView) findViewById(R.id.ipo);
        TextView txt5 = (TextView) findViewById(R.id.ipc);
        TextView txt6 = (TextView) findViewById(R.id.idis);
        TextView txt7 = (TextView) findViewById(R.id.istate);
        TextView txt8 = (TextView) findViewById(R.id.ihome);

        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);
        txt5.setTypeface(font);
        txt6.setTypeface(font);
        txt7.setTypeface(font);
        txt8.setTypeface(font);

        //----------- Initiate textfields--------------------------
        Intent i = getIntent();
        if (i!=null) {
            String[] stringData= i.getStringArrayExtra("name");

            //TextView name = (TextView) findViewById(R.id.hname);
            ((TextView) findViewById(R.id.name)).setText(stringData[1]);
            ((TextView) findViewById(R.id.gender)).setText(stringData[2]);
            ((TextView) findViewById(R.id.dob)).setText(stringData[3]);
            ((TextView) findViewById(R.id.address)).setText(stringData[4]);
            ((TextView) findViewById(R.id.po)).setText(stringData[5]);
            ((TextView) findViewById(R.id.pc)).setText(stringData[6]);
            ((TextView) findViewById(R.id.dist)).setText(stringData[7]);
            ((TextView) findViewById(R.id.state)).setText(stringData[8]);


        }
        //---------------------------------------------------------


    }
}
