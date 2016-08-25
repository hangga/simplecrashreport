package com.research.hangga.simplecrashreport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityShowError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_show_error);

        TextView txtError = (TextView) findViewById(R.id.txtError);
        String error = getIntent().getStringExtra("error");
        int a = error.indexOf("Caused by");
        txtError.setText(error.substring(a));
    }
}
