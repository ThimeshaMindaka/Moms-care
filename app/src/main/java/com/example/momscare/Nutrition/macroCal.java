package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
//import com.example.momscare.ToDo_List.To_Do_List;
import com.example.momscare.Workout.WorkOuts;

public class macroCal extends AppCompatActivity {

    EditText txtInFood,numInServSize;
    TextView txtViewEnergy,txtViewFat,txtViewCarbs,txtViewFiber,txtViewProtein,txtViewSodium,txtViewCholesterol,txtViewPotasium,txtViewCalories;
    Button btnFindMacro,navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;
    DBHelper dbHelper;
    String foodName;
    Float servings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_cal);

        txtInFood = findViewById(R.id.txtInFood);
        numInServSize = findViewById(R.id.numInServSize);

        txtViewEnergy = findViewById(R.id.txtViewEnergy);
        txtViewFat = findViewById(R.id.txtViewFat);
        txtViewCarbs = findViewById(R.id.txtViewCarbs);
        txtViewFiber = findViewById(R.id.txtViewFiber);
        txtViewProtein = findViewById(R.id.txtViewProtein);
        txtViewSodium = findViewById(R.id.txtViewSodium);
        txtViewCholesterol = findViewById(R.id.txtViewCholesterol);
        txtViewPotasium = findViewById(R.id.txtViewPotasium);
        txtViewCalories = findViewById(R.id.txtViewCalories);

        btnFindMacro = findViewById(R.id.btnFindMacro);

        navigate_todo = findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);

        btnFindMacro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodName = txtInFood.getText().toString().trim();
                servings = Float.valueOf(numInServSize.getText().toString().trim());
                Log.d("inputed string", foodName);
                dbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = dbHelper.SearchMacro(foodName);
                if(cursor.moveToFirst())
                {

                    Float energyVal = getCalories(Float.valueOf(cursor.getString(2)),servings);
                    Float fatVal = getCalories(Float.valueOf(cursor.getString(3)),servings);
                    Float carbyVal = getCalories(Float.valueOf(cursor.getString(4)),servings);
                    Float fiberVal = getCalories(Float.valueOf(cursor.getString(5)),servings);
                    Float proteinVal = getCalories(Float.valueOf(cursor.getString(6)),servings);
                    Float sodiumVal = getCalories(Float.valueOf(cursor.getString(7)),servings);
                    Float cholesterolVal = getCalories(Float.valueOf(cursor.getString(8)),servings);
                    Float potasiumVal = getCalories(Float.valueOf(cursor.getString(9)),servings);
                    Float caloriesVal = getCalories(Float.valueOf(cursor.getString(10)),servings);

                    txtViewEnergy.setText(String.valueOf(energyVal));
                    txtViewFat.setText(String.valueOf(fatVal));
                    txtViewCarbs.setText(String.valueOf(carbyVal));
                    txtViewFiber.setText(String.valueOf(fiberVal));
                    txtViewProtein.setText(String.valueOf(proteinVal));
                    txtViewSodium.setText(String.valueOf(sodiumVal));
                    txtViewCholesterol.setText(String.valueOf(cholesterolVal));
                    txtViewPotasium.setText(String.valueOf(potasiumVal));
                    txtViewCalories.setText(String.valueOf(caloriesVal));

                }

            }
        });
        //redirects to todoList


        //redirects to workout
        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(macroCal.this, WorkOuts.class);
                startActivity(intent);

            }
        });

        //redirects to meal list
        navigate_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(macroCal.this,ViewMeals.class);
                startActivity(intent);

            }
        });

      /*  //redirects to supplement list
        navigate_supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(macroCal.this, ViewSupplement.class);
                startActivity(intent);

            }
        });*/
    }

    public float getCalories(float value, float servings){

        return (float) value / 150 * servings;
    }


}