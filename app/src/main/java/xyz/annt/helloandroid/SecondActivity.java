package xyz.annt.helloandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setActivityResult();
    }

    public void setActivityResult() {
        Intent intent = getIntent();
        intent.putExtra("message", "This is a message from SecondActivity");

        setResult(RESULT_OK, intent);
        finish();
    }
}
