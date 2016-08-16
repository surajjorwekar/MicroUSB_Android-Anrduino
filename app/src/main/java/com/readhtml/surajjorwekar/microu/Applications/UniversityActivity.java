package com.readhtml.surajjorwekar.microu.Applications;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.readhtml.surajjorwekar.microu.R;

public class UniversityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university);

        //-------- Creating Font object ------------------------
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        TextView txt1 = (TextView) findViewById(R.id.t1);
        TextView txt2 = (TextView) findViewById(R.id.t2);
        TextView txt3 = (TextView) findViewById(R.id.t3);
        TextView txt4 = (TextView) findViewById(R.id.t4);
        TextView txt5 = (TextView) findViewById(R.id.t5);
        TextView txt6 = (TextView) findViewById(R.id.t6);

        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);
        txt5.setTypeface(font);
        txt6.setTypeface(font);
        //--------------------------------------------------------


        //----------- Initiate textfields--------------------------
        Intent i = getIntent();
        if (i!=null) {
            String[] stringData= i.getStringArrayExtra("name");

            //TextView name = (TextView) findViewById(R.id.hname);

            ((TextView) findViewById(R.id.hname)).setText(stringData[1]);
            ((TextView) findViewById(R.id.universityno)).setText(stringData[2]);
            ((TextView) findViewById(R.id.year)).setText(stringData[3]);
            ((TextView) findViewById(R.id.course)).setText(stringData[4]);
            ((TextView) findViewById(R.id.college)).setText(stringData[5]);
            ((TextView) findViewById(R.id.name)).setText(stringData[6]);
            ((TextView) findViewById(R.id.gender)).setText(stringData[7]);
            ((TextView) findViewById(R.id.phone)).setText(stringData[8]);
            ((TextView) findViewById(R.id.mail)).setText(stringData[9]);
            ((TextView) findViewById(R.id.dob)).setText(stringData[10]);
            ((TextView) findViewById(R.id.address)).setText(stringData[11]);
        }
        //---------------------------------------------------------
    }


}
