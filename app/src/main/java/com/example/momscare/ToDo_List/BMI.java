package com.example.momscare.ToDo_List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.MainActivity;
import com.example.momscare.R;


public class BMI extends AppCompatActivity {

    //variables
    TextView SignUpHeight, SignUpWeight, SignUpAge;
    TextView BMIResult, txtResultHbmi, txtResultHweight;
    Button getfitBMI;


    String H, W, A;

    float finalheight, finalweight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);

        String userName = getIntent().getStringExtra("username");

        //find view by id
        SignUpHeight = findViewById(R.id.SignUpHeight);
        SignUpWeight = findViewById(R.id.SignUpWeight);
        SignUpAge = findViewById(R.id.SignUpAge);
        BMIResult = findViewById(R.id.BMIResult);
        txtResultHbmi = findViewById(R.id.txtResultHbmi);
        txtResultHweight = findViewById(R.id.txtResultHweight);
        getfitBMI = findViewById(R.id.getfit);

        //redirects to main activity
        getfitBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMI.this, MainActivity.class);

                intent.putExtra("username",userName);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        H = intent.getStringExtra("height");
        W = intent.getStringExtra("weight");
        A = intent.getStringExtra("age");

        SignUpHeight.setText(H);
        SignUpWeight.setText(W);
        SignUpAge.setText(A);
        finalheight = Float.valueOf(H);
        finalweight = Float.valueOf(W);

        float healthybmi = calculate(finalheight, finalweight);

        BMIResult.setText("BMI = " + healthybmi);
        showhealthyBMI(healthybmi, finalheight);


    }

    //bmi calculation method
    public float calculate(float finalheight, float finalweight) {
        return finalweight / ((finalheight / 100) * (finalheight / 100));
    }

    //check bmi
    public void showhealthyBMI(float healthybmi, float finalheight) {
        if ((healthybmi == 18.49) || (healthybmi < 18.49)) {
            txtResultHbmi.setText("Under Weight");
            {
                if (finalheight < 149) {
                    txtResultHweight.setText("40kg - 52kg");
                } else if ((finalheight > 148)  && (finalheight < 152)) {
                    txtResultHweight.setText("41kg - 54kg");
                } else if ((finalheight > 151)  && (finalheight < 154)) {
                    txtResultHweight.setText("45kg - 56kg");
                } else if ((finalheight > 153)  && (finalheight < 157)) {
                    txtResultHweight.setText("45kg - 59kg");
                } else if ((finalheight > 156)  && (finalheight < 160)) {
                    txtResultHweight.setText("47kg - 61kg");
                } else if ((finalheight > 159)  && (finalheight < 162)) {
                    txtResultHweight.setText("47kg - 63kg");
                } else if ((finalheight > 161)  && (finalheight < 165)) {
                    txtResultHweight.setText("50kg - 65kg");
                } else if ((finalheight > 164)  && (finalheight < 167)) {
                    txtResultHweight.setText("52kg - 65kg");
                } else if ((finalheight > 166)  && (finalheight < 170)) {
                    txtResultHweight.setText("52kg - 68kg");
                } else if ((finalheight > 169) && (finalheight < 172)) {
                    txtResultHweight.setText("54kg - 70kg");
                } else if ((finalheight > 171) && (finalheight < 177)) {
                    txtResultHweight.setText("56kg - 72kg");
                } else if ((finalheight > 176)  && (finalheight < 180)) {
                    txtResultHweight.setText("59kg - 75kg");
                } else if ((finalheight > 179)  && (finalheight < 182)) {
                    txtResultHweight.setText("59kg - 77kg");
                } else if ((finalheight > 181)  && (finalheight < 185)) {
                    txtResultHweight.setText("63kg - 81kg");
                } else if ((finalheight > 184)  && (finalheight < 187)) {
                    txtResultHweight.setText("63kg - 84kg");
                } else if ((finalheight > 186)  && (finalheight < 190)) {
                    txtResultHweight.setText("65kg - 86kg");
                } else if ((finalheight > 189) && (finalheight < 193)) {
                    txtResultHweight.setText("68kg - 88kg");
                } else {
                    txtResultHweight.setText("70kg - 90kg");
                }
            }
        }

        else if (((healthybmi == 18.5) || (healthybmi > 18.5)) && ((healthybmi == 24.99) || (healthybmi < 24.99))) {
            txtResultHbmi.setText("Normal Weight");
            {
                if (finalheight < 149) {
                    txtResultHweight.setText("40kg - 52kg");
                } else if ((finalheight > 148)  && (finalheight < 152)) {
                    txtResultHweight.setText("41kg - 54kg");
                } else if ((finalheight > 151)  && (finalheight < 154)) {
                    txtResultHweight.setText("45kg - 56kg");
                } else if ((finalheight > 153)  && (finalheight < 157)) {
                    txtResultHweight.setText("45kg - 59kg");
                } else if ((finalheight > 156)  && (finalheight < 160)) {
                    txtResultHweight.setText("47kg - 61kg");
                } else if ((finalheight > 159)  && (finalheight < 162)) {
                    txtResultHweight.setText("47kg - 63kg");
                } else if ((finalheight > 161)  && (finalheight < 165)) {
                    txtResultHweight.setText("50kg - 65kg");
                } else if ((finalheight > 164)  && (finalheight < 167)) {
                    txtResultHweight.setText("52kg - 65kg");
                } else if ((finalheight > 166)  && (finalheight < 170)) {
                    txtResultHweight.setText("52kg - 68kg");
                } else if ((finalheight > 169) && (finalheight < 172)) {
                    txtResultHweight.setText("54kg - 70kg");
                } else if ((finalheight > 171) && (finalheight < 177)) {
                    txtResultHweight.setText("56kg - 72kg");
                } else if ((finalheight > 176)  && (finalheight < 180)) {
                    txtResultHweight.setText("59kg - 75kg");
                } else if ((finalheight > 179)  && (finalheight < 182)) {
                    txtResultHweight.setText("59kg - 77kg");
                } else if ((finalheight > 181)  && (finalheight < 185)) {
                    txtResultHweight.setText("63kg - 81kg");
                } else if ((finalheight > 184)  && (finalheight < 187)) {
                    txtResultHweight.setText("63kg - 84kg");
                } else if ((finalheight > 186)  && (finalheight < 190)) {
                    txtResultHweight.setText("65kg - 86kg");
                } else if ((finalheight > 189) && (finalheight < 193)) {
                    txtResultHweight.setText("68kg - 88kg");
                } else {
                    txtResultHweight.setText("70kg - 90kg");
                }
            }
        }
        else if (((healthybmi == 25) || (healthybmi > 25)) && ((healthybmi == 29.99) || (healthybmi < 29.99))) {
            txtResultHbmi.setText("OVER WEIGHT");
            {
                if (finalheight < 149) {
                    txtResultHweight.setText("40kg - 52kg");
                } else if ((finalheight > 148)  && (finalheight < 152)) {
                    txtResultHweight.setText("41kg - 54kg");
                } else if ((finalheight > 151)  && (finalheight < 154)) {
                    txtResultHweight.setText("45kg - 56kg");
                } else if ((finalheight > 153)  && (finalheight < 157)) {
                    txtResultHweight.setText("45kg - 59kg");
                } else if ((finalheight > 156)  && (finalheight < 160)) {
                    txtResultHweight.setText("47kg - 61kg");
                } else if ((finalheight > 159)  && (finalheight < 162)) {
                    txtResultHweight.setText("47kg - 63kg");
                } else if ((finalheight > 161)  && (finalheight < 165)) {
                    txtResultHweight.setText("50kg - 65kg");
                } else if ((finalheight > 164)  && (finalheight < 167)) {
                    txtResultHweight.setText("52kg - 65kg");
                } else if ((finalheight > 166)  && (finalheight < 170)) {
                    txtResultHweight.setText("52kg - 68kg");
                } else if ((finalheight > 169) && (finalheight < 172)) {
                    txtResultHweight.setText("54kg - 70kg");
                } else if ((finalheight > 171) && (finalheight < 177)) {
                    txtResultHweight.setText("56kg - 72kg");
                } else if ((finalheight > 176)  && (finalheight < 180)) {
                    txtResultHweight.setText("59kg - 75kg");
                } else if ((finalheight > 179)  && (finalheight < 182)) {
                    txtResultHweight.setText("59kg - 77kg");
                } else if ((finalheight > 181)  && (finalheight < 185)) {
                    txtResultHweight.setText("63kg - 81kg");
                } else if ((finalheight > 184)  && (finalheight < 187)) {
                    txtResultHweight.setText("63kg - 84kg");
                } else if ((finalheight > 186)  && (finalheight < 190)) {
                    txtResultHweight.setText("65kg - 86kg");
                } else if ((finalheight > 189) && (finalheight < 193)) {
                    txtResultHweight.setText("68kg - 88kg");
                } else {
                    txtResultHweight.setText("70kg - 90kg");
                }
            }
        }

        else {
            txtResultHbmi.setText("OBESE");
            {
                if (finalheight < 149) {
                    txtResultHweight.setText("40kg - 52kg");
                } else if ((finalheight > 148)  && (finalheight < 152)) {
                    txtResultHweight.setText("41kg - 54kg");
                } else if ((finalheight > 151)  && (finalheight < 154)) {
                    txtResultHweight.setText("45kg - 56kg");
                } else if ((finalheight > 153)  && (finalheight < 157)) {
                    txtResultHweight.setText("45kg - 59kg");
                } else if ((finalheight > 156)  && (finalheight < 160)) {
                    txtResultHweight.setText("47kg - 61kg");
                } else if ((finalheight > 159)  && (finalheight < 162)) {
                    txtResultHweight.setText("47kg - 63kg");
                } else if ((finalheight > 161)  && (finalheight < 165)) {
                    txtResultHweight.setText("50kg - 65kg");
                } else if ((finalheight > 164)  && (finalheight < 167)) {
                    txtResultHweight.setText("52kg - 65kg");
                } else if ((finalheight > 166)  && (finalheight < 170)) {
                    txtResultHweight.setText("52kg - 68kg");
                } else if ((finalheight > 169) && (finalheight < 172)) {
                    txtResultHweight.setText("54kg - 70kg");
                } else if ((finalheight > 171) && (finalheight < 177)) {
                    txtResultHweight.setText("56kg - 72kg");
                } else if ((finalheight > 176)  && (finalheight < 180)) {
                    txtResultHweight.setText("59kg - 75kg");
                } else if ((finalheight > 179)  && (finalheight < 182)) {
                    txtResultHweight.setText("59kg - 77kg");
                } else if ((finalheight > 181)  && (finalheight < 185)) {
                    txtResultHweight.setText("63kg - 81kg");
                } else if ((finalheight > 184)  && (finalheight < 187)) {
                    txtResultHweight.setText("63kg - 84kg");
                } else if ((finalheight > 186)  && (finalheight < 190)) {
                    txtResultHweight.setText("65kg - 86kg");
                } else if ((finalheight > 189) && (finalheight < 193)) {
                    txtResultHweight.setText("68kg - 88kg");
                } else {
                    txtResultHweight.setText("70kg - 90kg");
                }
            }
        }
    }
}
