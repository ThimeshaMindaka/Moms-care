package com.example.momscare.Medical;

import androidx.appcompat.app.AppCompatActivity;
import com.example.momscare.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Duedate_rep extends AppCompatActivity {

    TextView txt24;
    TextView txt004;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duedate_reply);

        txt24 = findViewById(R.id.txt24);
        txt004 = findViewById(R.id.txt004);

        Intent cal = getIntent();
        String year1 = cal.getStringExtra("year");
        String month1 = cal.getStringExtra("month");

        txt24.setText(month1);
        txt004.setText(year1);
    }
}