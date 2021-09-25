package com.example.momscare.Medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

public class Medical_info extends AppCompatActivity {
    // ceating references

    Button btn14, btn16, btn17;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_information);

        btn14 = findViewById(R.id.btn14);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
    }

    //navigation
    public void openduedate_cal(View view) {
        Intent opnDue = new Intent( Medical_info.this,Duedate_cal.class);
        startActivity(opnDue);
    }
    public void openMS(View view){
        Intent opnMS = new Intent(Medical_info.this,MedicalShechedule.class);
        startActivity(opnMS);
    }
    public void viewDoc(View view) {
        Intent vDoc = new Intent(Medical_info.this, View_doc.class);
        startActivity(vDoc);
    }
}