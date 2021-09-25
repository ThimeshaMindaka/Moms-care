package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.momscare.R;
import com.example.momscare.Workout.WorkOuts;


public class WeightConverter extends AppCompatActivity {
    EditText weight;
    RadioGroup radioGroup;
    RadioButton weight_radioBtn1;
    RadioButton weight_radioBtn2;
    RadioButton weight_radioBtn3;
    float weightValue;
    TextView weight_tv2;
    //navigation bar buttons
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        weight =findViewById(R.id.weight_etnd1);
        weight_tv2 =findViewById(R.id.weight_tv2);
        radioGroup =findViewById(R.id.weight_radioGrp);
        weight_radioBtn1 =findViewById(R.id.weight_radioBtn1);
        weight_radioBtn2 =findViewById(R.id.weight_radioBtn2);
        weight_radioBtn3 =findViewById(R.id.weight_radioBtn3);
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);
        navigate_todo =findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);

    }
    public void calculateWeight(View view){
        try {
            if(TextUtils.isEmpty(weight.getText().toString()) && radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),"Please enter a weight value and check option ",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(weight.getText().toString())){
                weight.setError("Please enter a weight value to continue");
                weight.requestFocus();
                return;
            }else if(radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),"Please select a weight conversion option ",Toast.LENGTH_SHORT).show();
            }else{
                weightValue =  Float.parseFloat(weight.getText().toString());
                if(weight_radioBtn1.isChecked()){
                    float result = kiloGramsToPounds(weightValue);
                    String formatValue = String.format("%.02f",result);
                    weight_tv2.setText("Your Weight is "+formatValue+" pounds");
                }else if(weight_radioBtn2.isChecked()){
                    float result = PoundsToKiloGrams(weightValue);
                    String formatValue = String.format("%.02f",result);
                    weight_tv2.setText("Your Weight is "+formatValue+" kilograms");
                }else if (weight_radioBtn3.isChecked()){
                    float result = kiloGramsToStones(weightValue);
                    String formatValue = String.format("%.02f",result);
                    weight_tv2.setText("Your Weight is "+formatValue+" stones");
                }
                emptyForm();
            }
        }catch(Exception e){
            Toast.makeText(this, "Please input correct number format", Toast.LENGTH_SHORT).show();
        }
    }
    public void emptyForm(){
        //weight.setText("");
        weight_radioBtn1.setChecked(false);
        weight_radioBtn2.setChecked(false);
        weight_radioBtn3.setChecked(false);
    }
    //Kilograms to Pounds conversion Calculation
    public float kiloGramsToPounds(double weightValue){
        float value = (float) (weightValue * 2.205);
        return value;
    }
    //Pounds to Kilograms conversion Calculation
    public float PoundsToKiloGrams(float weightValue){
        float value = (float) (weightValue / 2.205);
        return value;
    }
    //Kilograms to Stones conversion Calculation
    public float kiloGramsToStones(float weightValue){
        float value = (float) (weightValue / 6.34);
        return value;
    }
    //lower navigation bar button page directions
    protected void onResume() {
        super.onResume();
        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightConverter.this, WorkOuts.class);
                startActivity(intent);
            }
        });

    }



}