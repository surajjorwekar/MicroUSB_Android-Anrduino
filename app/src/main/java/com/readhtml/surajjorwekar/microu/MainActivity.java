package com.readhtml.surajjorwekar.microu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.readhtml.surajjorwekar.microu.Applications.AirportActivity;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //-------- Initialising Layout variables ----------
        final TextView uname = (TextView)findViewById(R.id.uname);
        final TextView pass = (TextView)findViewById(R.id.pass);
        Button log=(Button) findViewById(R.id.login);
        //------------------------------------------------



        //-------- Initialising FAN library --------------
        AndroidNetworking.initialize(getApplicationContext());
        //------------------------------------------------



        //-------- Creating Font object ------------------------
        //Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        //FontManager.markAsIconContainer(findViewById(R.id.container), iconFont);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        TextView txt1 = (TextView) findViewById(R.id.t1);
        TextView txt2 = (TextView) findViewById(R.id.t2);

        txt1.setTypeface(font);
        txt2.setTypeface(font);
        //--------------------------------------------------------





        //-------- LOGIN On-Click Method ------------------------
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this, AirportActivity.class);
                startActivity(intent2);

                /*
                //********* Getting details from dislay *********
                String user = String.valueOf(uname.getText());
                String pwd = String.valueOf(pass.getText());


                AndroidNetworking.get("http://192.168.43.138:8000/logindata/{id}")
                        .addPathParameter("id", user)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                //String Airport =;

                                String intent_str=response.toString();
                                //Toast t0 = Toast.makeText(getApplicationContext(), intent_str, Toast.LENGTH_SHORT);
                                //t0.show();
                                switch(intent_str)
                                {
                                    case  "[\"Airport\"]"      :    Toast t1 = Toast.makeText(getApplicationContext(),"Welcome to Airport Actvity..!" , Toast.LENGTH_SHORT);
                                                                    t1.show();
                                                                    Intent intent1 = new Intent(MainActivity.this, AirportActivity.class);
                                                                    startActivity(intent1);
                                                                    break;

                                    case  "[\"University\"]"   :    t1 = Toast.makeText(getApplicationContext(),"Welcome to University Actvity..!" , Toast.LENGTH_SHORT);
                                                                    t1.show();
                                                                    Intent intent2 = new Intent(MainActivity.this, UniversityActivity.class);
                                                                    startActivity(intent2);
                                                                    break;


                                    case  "[\"Payment\"]"      :    t1 = Toast.makeText(getApplicationContext(),"Welcome to Payment Actvity..!" , Toast.LENGTH_SHORT);
                                                                    t1.show();
                                                                    Intent intent3 = new Intent(MainActivity.this, PaymentActivity.class);
                                                                    startActivity(intent3);
                                                                    break;

                                    case  "[\"Hospital\"]"     :    t1 = Toast.makeText(getApplicationContext(),"Welcome to Hospital Actvity..!" , Toast.LENGTH_SHORT);
                                                                    t1.show();
                                                                    Intent intent4 = new Intent(MainActivity.this, HospitalActivity.class);
                                                                    startActivity(intent4);
                                                                    break;

                                    default                    :    t1 = Toast.makeText(getApplicationContext(),"Username or Password wrong" , Toast.LENGTH_SHORT);
                                                                    t1.show();
                                                                    break;
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                Toast t1 = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                                t1.show();
                            }
                        });*/

            }
        });
        //--------------------------------------------------------


    }


}





    /*

    ------------------------ INIT ----------------
    1) public static final String ACTION_USB_ATTACHED = "com.android.example.USB_PERMISSION";
    2) Call request : getPermissionToReadUserContacts();
    3) Put following functions in class
    ----------------------------------------------


    // Identifier for the permission request

    //private static final int ACTION_USB_PERMISSION = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    @TargetApi(Build.VERSION_CODES.M)
    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this,ACTION_USB_ATTACHED)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    ACTION_USB_ATTACHED)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
    String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "USB permission granted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "USB permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }

*/



