package com.example.momscare.Medical;

import androidx.appcompat.app.AppCompatActivity;
import com.example.momscare.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Medical_shchedule extends AppCompatActivity {
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_shchedule);
        btn2 = findViewById(R.id.btn2);

    }

    public void openAdd(View view) {
        Intent opnAdd = new Intent(Medical_shchedule.this,Add_shchedule.class);
        startActivity(opnAdd);
    }
}