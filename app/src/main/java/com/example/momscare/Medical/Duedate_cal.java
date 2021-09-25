package com.example.momscare.Medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;


public class Duedate_cal extends AppCompatActivity {

    EditText txt50, txt00;
    Button btn9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duedate_calculator);

        txt50 = findViewById(R.id.txt50);
        txt00 = findViewById(R.id.txt00);
        btn9 = findViewById(R.id.btn9);


    }

    public void calculate(View view) {
        String year1 = txt50.getText().toString();
        String month1 = txt00.getText().toString();
        int year = Integer.parseInt(year1);
        int month = Integer.parseInt(month1);

        String month2 = null;
        if (month == 1) {
            month2 = "October";


        } else if (month == 2) {
            month2 = "November";


        }
         else if (month == 3) {
        month2 = "December";


        }
         else if (month == 4) {
            month2 = "January";
            year++;
        }
        else if (month == 5) {
            month2 = "February";
            year++;
        }
        else if (month == 6) {
            month2 = "March";
            year++;
        }
        else if (month == 7) {
            month2 = "April";
            year++;
        }
        else if (month == 8) {
            month2 = "May";
            year++;
        }
        else if (month == 9) {
            month2 = "June";
            year++;
        }
        else if (month == 10) {
            month2 = "July";
            year++;
        }
        else if (month == 11) {
            month2 = "August";
            year++;
        }
        else if (month == 12) {
            month2 = "September";
            year++;
        }

        Intent cal = new Intent(Duedate_cal.this,Duedate_rep.class);

        String year2 = Integer.toString(year);

        cal.putExtra("year", year2);
        cal.putExtra("month", month2);
        startActivity(cal);
    }
}