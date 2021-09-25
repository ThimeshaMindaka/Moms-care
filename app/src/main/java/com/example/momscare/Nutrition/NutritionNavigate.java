package com.example.momscare.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

public class NutritionNavigate extends AppCompatActivity {

    Button view_meal,add_meal,update_meal,view_macros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_navigate);

        view_meal = findViewById(R.id.nutrition_button_viewMeals);
        add_meal = findViewById(R.id.nutrition_button_addMeals);
        update_meal = findViewById(R.id.nutrition_button_updateMeals);
        view_macros = findViewById(R.id.nutrition_button_Macros);

        //redirects to MealList activity
        view_meal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionNavigate.this, MealList.class);
                startActivity(intent);
            }
        });

        //redirects to AddMeal activity
        add_meal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionNavigate.this, AddMeal.class);
                startActivity(intent);
            }
        });

        //redirects to UpdateMeal activity
        update_meal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionNavigate.this, UpdateMeal.class);
                startActivity(intent);
            }
        });

        //redirects to macros activity
        view_macros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionNavigate.this, macros.class);
                startActivity(intent);
            }
        });
    }
}