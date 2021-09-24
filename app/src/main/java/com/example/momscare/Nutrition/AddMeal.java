package com.example.momscare.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

public class AddMeal extends AppCompatActivity {

    EditText mealID,mealName,bm01,bm02,bm03,bm04,lm01,lm02,lm03,lm04,dm01,dm02,dm03,dm04;
    Button nutrition_button_addMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mealID = findViewById(R.id.mealID);
        mealName = findViewById(R.id.mealName);
        bm01 = findViewById(R.id.bm01);
        bm02 = findViewById(R.id.bm02);
        bm03 = findViewById(R.id.bm03);
        bm04 = findViewById(R.id.bm04);
        lm01 = findViewById(R.id.lm01);
        lm02 = findViewById(R.id.lm02);
        lm03 = findViewById(R.id.lm03);
        lm04 = findViewById(R.id.lm04);
        dm01 = findViewById(R.id.dm01);
        dm02 = findViewById(R.id.dm02);
        dm03 = findViewById(R.id.dm03);
        dm04 = findViewById(R.id.dm04);
        nutrition_button_addMeal = findViewById(R.id.nutrition_button_updateMeal);

        nutrition_button_addMeal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                String inputId = mealID.getText().toString();
                String inputName = mealName.getText().toString();

                //input patterns
                String idVal = "[M]+[0-9]+";
                String nameVal = "[a-zA-Z,0-9]+";

                //validating mealID and mealName
                if(inputId.isEmpty()){
                    mealID.requestFocus();
                    mealID.setError("Field cannot be empty");
                }else if(inputName.isEmpty()){
                    mealName.requestFocus();
                    mealName.setError("Field cannot be empty");
                }else if(!inputId.matches(idVal)){
                    mealID.requestFocus();
                    mealID.setError("invalid characters!");
                }else if(!inputName.matches(nameVal)){
                    mealName.requestFocus();
                    mealName.setError("invalid characters!");
                }
                else {

                    DBHelper dbHelper = new DBHelper(AddMeal.this);
                    dbHelper.addMeal(
                            mealID.getText().toString().trim(),
                            mealName.getText().toString().trim(),
                            bm01.getText().toString().trim(),
                            bm02.getText().toString().trim(),
                            bm03.getText().toString().trim(),
                            bm04.getText().toString().trim(),
                            lm01.getText().toString().trim(),
                            lm02.getText().toString().trim(),
                            lm03.getText().toString().trim(),
                            lm04.getText().toString().trim(),
                            dm01.getText().toString().trim(),
                            dm01.getText().toString().trim(),
                            dm01.getText().toString().trim(),
                            dm01.getText().toString().trim());


                    Intent intent = new Intent(AddMeal.this, MealList.class);
                    startActivity(intent);

                }
            }
        });
    }
}