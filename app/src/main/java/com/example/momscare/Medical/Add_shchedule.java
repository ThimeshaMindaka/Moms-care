package com.example.momscare.Medical;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.Medical.Database.MsDBHelper;
import com.example.momscare.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_shchedule extends AppCompatActivity {
    EditText txt30, txt31, txt32;
    Button btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shchedule);

        txt30 = findViewById(R.id.txt30);
        txt31 = findViewById(R.id.txt31);
        txt32 = findViewById(R.id.txt32);
        btn7 = findViewById(R.id.btn7);
    }

    public void saveSch(View view){
        String name = txt30.getText().toString();
        String date = txt31.getText().toString();
        String time = txt32.getText().toString();

        MsDBHelper msdbhelper = new MsDBHelper(this);

        if(name.isEmpty()){
            Toast.makeText(this,"Enter Values", Toast.LENGTH_SHORT).show();

        }
        else{
            msdbhelper.addlist(name,date,time);
        }
    }
}