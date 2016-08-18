package com.readhtml.surajjorwekar.microu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.StringTokenizer;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanAadhar extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = null;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_aadhar);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }





    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        String[] wr = rawResult.toString().split("PrintLetterBarcodeData");
        String str = wr[1];
        StringTokenizer st = new StringTokenizer(str,"\"");
        String datarecevied = "";
        while (st.hasMoreElements()) {
            String data = st.nextElement().toString();
            if(data.charAt(0) != ' ' && data.charAt(0) != '/')
                datarecevied=datarecevied+"\n"+data;
        }
        Toast.makeText(getApplicationContext(),datarecevied, Toast.LENGTH_LONG).show();
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",datarecevied);
        setResult(2,intent);
        finish();//finishing activity
    }
}
