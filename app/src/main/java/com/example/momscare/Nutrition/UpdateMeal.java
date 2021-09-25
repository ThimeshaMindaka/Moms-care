package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

public class UpdateMeal extends AppCompatActivity {

    EditText mealID,mealName,bm01,bm02,bm03,bm04,lm01,lm02,lm03,lm04,dm01,dm02,dm03,dm04;
    Button button_mealSearch,nutrition_button_updateMeal;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meal);

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

        button_mealSearch = findViewById(R.id.button_mealSearch);
        nutrition_button_updateMeal = findViewById(R.id.nutrition_button_updateMeal);


        button_mealSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputID = mealID.getText().toString();

                if(inputID.isEmpty()){
                    mealID.requestFocus();
                    mealID.setError("Field cannot be empty");
                }
                else {
                    dbHelper = new DBHelper(getApplicationContext());
                    Cursor cursor = dbHelper.SearchMeal(inputID);

                    if (cursor.moveToFirst()) {

                        bm01.setText(cursor.getString(2));
                        bm02.setText(cursor.getString(3));
                        bm03.setText(cursor.getString(4));
                        bm04.setText(cursor.getString(5));
                        lm01.setText(cursor.getString(6));
                        lm02.setText(cursor.getString(7));
                        lm03.setText(cursor.getString(8));
                        lm04.setText(cursor.getString(9));
                        dm01.setText(cursor.getString(10));
                        dm02.setText(cursor.getString(11));
                        dm03.setText(cursor.getString(12));
                        dm04.setText(cursor.getString(13));

                    }
                }

            }
        });

        nutrition_button_updateMeal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                String inputId = mealID.getText().toString();
                String inputName = mealName.getText().toString();

                //meal name pattern
                String nameVal = "[a-zA-Z,,0-9]+";

                //validating mealName input
                if(inputId.isEmpty()){
                    mealID.requestFocus();
                    mealID.setError("Field cannot be empty");
                }else if(inputName.isEmpty()) {
                    mealName.requestFocus();
                    mealName.setError("Field cannot be empty");
                }else if(!inputName.matches(nameVal)){
                    mealName.requestFocus();
                    mealName.setError("invalid characters!");
                }
                else {

                    DBHelper dbHelper = new DBHelper(UpdateMeal.this);
                    dbHelper.updateMealPlan(
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
                            dm02.getText().toString().trim(),
                            dm03.getText().toString().trim(),
                            dm04.getText().toString().trim());


                    Intent intent = new Intent(UpdateMeal.this, MealList.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}