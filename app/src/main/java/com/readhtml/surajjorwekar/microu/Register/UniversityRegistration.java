package com.readhtml.surajjorwekar.microu.Register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.readhtml.surajjorwekar.microu.R;

public class UniversityRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_registration);


        /*
        //-------- Creating Font object ------------------------
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        TextView txt0 = (TextView) findViewById(R.id.t0);
        TextView txt1 = (TextView) findViewById(R.id.t1);
        TextView txt2 = (TextView) findViewById(R.id.t2);
        TextView txt3 = (TextView) findViewById(R.id.t3);
        TextView txt4 = (TextView) findViewById(R.id.t4);
        TextView txt5 = (TextView) findViewById(R.id.t5);
        TextView txt6 = (TextView) findViewById(R.id.t6);


        txt0.setTypeface(font);
        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);
        txt5.setTypeface(font);
        txt6.setTypeface(font);

        //--------------------------------------------------------



        //-------- Initialising Layout variables ----------
        Button log=(Button) findViewById(R.id.createbutton);

        final String Data = String.valueOf(((TextView)findViewById(R.id.hname)).getText())     + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.hname)).getText())   + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.gender)).getText())  + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.phone)).getText())   + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.mail)).getText())    + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.dob)).getText())     + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.address)).getText()) + "\n" +
                        String.valueOf(((TextView)findViewById(R.id.url)).getText());

        //------------------------------------------------


        //-------- OnClick Method ------------------------
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ji = new Intent(getApplicationContext(), WaitForNFC.class);
                ji.putExtra("SendData", Data);
                startActivity(ji);

            }
        });*/

    }
}