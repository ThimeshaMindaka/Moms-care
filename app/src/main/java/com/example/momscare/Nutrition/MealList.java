package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MealList extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton button_addMeal;

    DBHelper db ;
    ArrayList<String> mealID, mealName;
    MealPlansAdapter customAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        recyclerView = findViewById(R.id.meals_recyclerView);
        button_addMeal = findViewById(R.id.button_addMeal);

        //redirects to addMeals activity when click add button
        button_addMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealList.this, AddMeal.class);
                startActivity(intent);
            }
        });

        db = new DBHelper(MealList.this);
        mealID = new ArrayList<>();
        mealName = new ArrayList<>();

        storeMealsInArray();

        customAdapater = new MealPlansAdapter(MealList.this,mealID,mealName);
        recyclerView.setAdapter(customAdapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(MealList.this));
    }

    //read mealID and mealName from meal plans table
    void storeMealsInArray(){
        Cursor cursor =  db.readMealTableData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                mealID.add(cursor.getString(0));
                mealName.add(cursor.getString(1));
            }
        }
    }
}