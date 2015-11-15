package xyz.annt.helloandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";
    static final int PERMISSIONS_REQUEST_CALL_PHONE = 1;
    static final int SECOND_ACTIVITY_RESULT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        startSecondActivity();
//        startActionView();
//        callPhone();
                getSecondActivityResult();
            }
        });
    }

    public void getSecondActivityResult() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case SECOND_ACTIVITY_RESULT: {
                TextView textResult = (TextView) findViewById(R.id.textResult);
                if (resultCode == RESULT_OK) {
                    String message = intent.getStringExtra("message");
                    textResult.setText("RESULT_OK " + message);
                } else {
                    textResult.setText("RESULT_CANCELED");
                }
            }
        }
    }

    public void callPhone() {
        Intent callPhone = new Intent(Intent.ACTION_CALL);
        callPhone.setData(Uri.parse("tel:012-345-6789"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
                return;
            }
        }

        startActivity(callPhone);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    callPhone();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void startActionView() {
        Intent actionView = new Intent(Intent.ACTION_VIEW);
        startActivity(actionView);
    }

    public void startSecondActivity() {
        Intent secondActivity = new Intent(this, SecondActivity.class);
        startActivity(secondActivity);
    }
}
