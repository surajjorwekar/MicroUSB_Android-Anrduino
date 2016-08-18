package com.readhtml.surajjorwekar.microu;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.readhtml.surajjorwekar.microu.Applications.AirportActivity;
import com.readhtml.surajjorwekar.microu.Applications.HospitalActivity;
import com.readhtml.surajjorwekar.microu.Applications.PaymentActivity;
import com.readhtml.surajjorwekar.microu.Applications.UniversityActivity;
import com.readhtml.surajjorwekar.microu.Register.CardRegistration;
import com.readhtml.surajjorwekar.microu.Utils.UsbService;

import java.lang.ref.WeakReference;
import java.util.Set;

public class WaitForNFC extends AppCompatActivity {

    public int FLAG = 0;
    public TextView txt1;
    public TextView txt2;
    public ProgressBar progressBar;
    /*
    * Notifications from UsbService will be received here.
    */
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();

                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private UsbService usbService;
    //private TextView display;

    private MyHandler mHandler;
    private final ServiceConnection usbConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName arg0, IBinder arg1) {
        usbService = ((UsbService.UsbBinder) arg1).getService();
        usbService.setHandler(mHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName arg0) {
        usbService = null;
    }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_nfc);
        txt1 = (TextView) findViewById(R.id.header);
        txt2 = (TextView) findViewById(R.id.footer);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        txt2.setVisibility(View.INVISIBLE);

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.INVISIBLE);
        mHandler = new MyHandler(this);
        //onResume();
        //usbService.serialPort.write("data".getBytes());

    }



    @Override
    public void onResume() {
        super.onResume();

        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it

    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(this, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            startService(startService);
        }
        Intent bindingIntent = new Intent(this, service);
        bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            usbService.serialPort.write(message.getBytes());
        }


    }

    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */
    class MyHandler extends Handler {
        private final WeakReference<WaitForNFC> mActivity;
        public String data="";
        public int flag=0;
        public MyHandler(WaitForNFC activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {


                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    if (flag == 0) {
                        txt1.setText("Found NFC Card");
                        txt2.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        flag = 1;
                    }

                    if(! ((String) msg.obj).equals("#") ) {

                        //Toast.makeText(getApplicationContext(),"REC : "+data,Toast.LENGTH_LONG).show();
                        data = data + (String) msg.obj;

                    }
                    else
                    {
                        switch (data.charAt(0))
                        {
                            case '1':
                                    Intent ji;
                                    switch (data.charAt(1)) {
                                        case 'U':

                                            //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                                            ji = new Intent(getApplicationContext(), UniversityActivity.class);
                                            String[] words = data.split("\n");
                                            ji.putExtra("name", words);
                                            startActivity(ji);
                                            data = "";
                                            //ji = new Intent(getApplicationContext(), HospitalActivity.class);
                                            //ji.putExtra("name", data);
                                            //startActivity(ji);
                                            break;

                                        case 'P':
                                            ji = new Intent(getApplicationContext(), PaymentActivity.class);
                                            ji.putExtra("name", data);
                                            startActivity(ji);
                                            break;

                                        case 'H':
                                            ji = new Intent(getApplicationContext(), HospitalActivity.class);
                                            String[] Awords = data.split("\n");
                                            ji.putExtra("name", Awords);
                                            startActivity(ji);
                                            data = "";
                                            break;

                                        case 'A':
                                            ji = new Intent(getApplicationContext(), AirportActivity.class);
                                            String[] Arrwords = data.split("\n");
                                            ji.putExtra("name", Arrwords);
                                            startActivity(ji);
                                            break;
                                    }
                                    txt1.setText("Waiting for NFC ");
                                    txt2.setVisibility(View.INVISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    flag = 0;

                                    break;

                            case 'S':

                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(WaitForNFC.this);

                                    Toast.makeText(getApplicationContext(),"Application not installed on Card.",Toast.LENGTH_LONG).show();
                                    alertDialog.setTitle("Create New Application");

                                    alertDialog.setMessage("Are you sure you want to add new app to card ?");


                                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent i;
                                            switch (data.charAt(1)) {
                                                case 'U':

                                                    final View v = LayoutInflater.from(WaitForNFC.this).inflate(R.layout.university_registration, null);
                                                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

                                                    TextView txt0 = (TextView) v.findViewById(R.id.t0);
                                                    TextView txt1 = (TextView) v.findViewById(R.id.t1);
                                                    TextView txt2 = (TextView) v.findViewById(R.id.t2);
                                                    TextView txt3 = (TextView) v.findViewById(R.id.t3);
                                                    TextView txt4 = (TextView) v.findViewById(R.id.t4);
                                                    TextView txt5 = (TextView) v.findViewById(R.id.t5);
                                                    TextView txt6 = (TextView) v.findViewById(R.id.t6);
                                                    TextView txt4_1 = (TextView) v.findViewById(R.id.t4_1);
                                                    TextView txt4_2 = (TextView) v.findViewById(R.id.t4_2);
                                                    TextView txt4_3 = (TextView) v.findViewById(R.id.t4_3);
                                                    TextView txt4_4 = (TextView) v.findViewById(R.id.t4_3);

                                                    txt0.setTypeface(font);
                                                    txt1.setTypeface(font);
                                                    txt2.setTypeface(font);
                                                    txt3.setTypeface(font);
                                                    txt4.setTypeface(font);
                                                    txt5.setTypeface(font);
                                                    txt6.setTypeface(font);
                                                    txt4_1.setTypeface(font);
                                                    txt4_2.setTypeface(font);
                                                    txt4_3.setTypeface(font);
                                                    txt4_4.setTypeface(font);

                                                    AlertDialog.Builder builder = new AlertDialog.Builder(WaitForNFC.this);
                                                    builder
                                                            .setView(v)
                                                            .setPositiveButton("Create Application", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    final String DataReceived =
                                                                            String.valueOf(((TextView) v.findViewById(R.id.hname)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.universityno)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.year)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.course)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.college)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.fname)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.gender)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.phone)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.mail)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.dob)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v.findViewById(R.id.address)).getText()) + "\n";

                                                                    usbService.serialPort.write(DataReceived.getBytes());
                                                                }
                                                            })

                                                            .setCancelable(false);
                                                    AlertDialog alert = builder.create();
                                                    alert.show();
                                                    data = "";
                                                    break;

                                                case 'P':
                                                    i = new Intent(getApplicationContext(), CardRegistration.class);
                                                    i.putExtra("name", data);
                                                    startActivity(i);
                                                    break;

                                                case 'H':
                                                    final View v1 = LayoutInflater.from(WaitForNFC.this).inflate(R.layout.hospital_registration, null);
                                                    Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

                                                    TextView txt00h = (TextView) v1.findViewById(R.id.iname);
                                                    TextView txt0h = (TextView) v1.findViewById(R.id.ical);
                                                    TextView txt1h = (TextView) v1.findViewById(R.id.idoc);
                                                    TextView txt2h = (TextView) v1.findViewById(R.id.idec);
                                                    TextView txt3h = (TextView) v1.findViewById(R.id.iapp);
                                                    TextView txt4h = (TextView) v1.findViewById(R.id.ibat);
                                                    TextView txt5h = (TextView) v1.findViewById(R.id.imed);
                                                    TextView txt6h = (TextView) v1.findViewById(R.id.icon);


                                                    txt0h.setTypeface(font1);
                                                    txt00h.setTypeface(font1);
                                                    txt1h.setTypeface(font1);
                                                    txt2h.setTypeface(font1);
                                                    txt3h.setTypeface(font1);
                                                    txt4h.setTypeface(font1);
                                                    txt5h.setTypeface(font1);
                                                    txt6h.setTypeface(font1);

                                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(WaitForNFC.this);
                                                    builder1
                                                            .setView(v1)
                                                            .setPositiveButton("Create Application", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    final String DataReceived =
                                                                            String.valueOf(((TextView) v1.findViewById(R.id.name)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.indate)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.doctor)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.app)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.dis)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.age)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.weight)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.height)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.insuff)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.medi)).getText()) + "\n" +
                                                                                    String.valueOf(((TextView) v1.findViewById(R.id.emergency)).getText()) + "\n";

                                                                    usbService.serialPort.write(DataReceived.getBytes());
                                                                }
                                                            })

                                                            .setCancelable(false);
                                                    AlertDialog alert1 = builder1.create();
                                                    alert1.show();
                                                    data = "";

                                                    break;

                                                case 'A':
                                                    Intent intent=new Intent(getApplicationContext(), ScanAadhar.class);
                                                    startActivityForResult(intent, 2);

                                                    data = "";

                                                    break;
                                            }
                                        }
                                    });


                                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();


                                        }
                                    });



                                    alertDialog.show();
                                    txt1.setText("Waiting for NFC ");
                                    txt2.setVisibility(View.INVISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    flag = 0;

                                    break;

                            default:
                                    Toast t1 = Toast.makeText(getApplicationContext(), "Corrupted Data Received", Toast.LENGTH_SHORT);
                                    t1.show();
                                    txt1.setText("Waiting for NFC ");
                                    txt2.setVisibility(View.INVISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    flag = 0;
                                    data = "";
                                    break;
                        }

                    }

                    break;
            }
        }
    }


}
