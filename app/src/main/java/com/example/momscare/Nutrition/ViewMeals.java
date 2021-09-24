package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;
//import com.example.momscare.ToDo_List.To_Do_List;
import com.example.momscare.Workout.WorkOuts;

import java.util.ArrayList;

public class ViewMeals extends AppCompatActivity {

    Button navigate_todo,navigate_workout,navigate_supplement;

    RecyclerView recyclerView;

    DBHelper db ;
    ArrayList<String> mealID, mealName;
    UserMealsAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meals);

        navigate_todo = findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
        navigate_supplement = findViewById(R.id.navigate_supplement);

        recyclerView = findViewById(R.id.user_meals_recycler);

        db = new DBHelper(ViewMeals.this);
        mealID = new ArrayList<>();
        mealName = new ArrayList<>();

        storeMealsInArray();

        customAdapter = new UserMealsAdapter(ViewMeals.this,mealID,mealName);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewMeals.this));

        //bottom navigation buttons on click listners
       /* navigate_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewMeals.this, To_Do_List.class);
                startActivity(intent);

            }
        }); */

        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewMeals.this, WorkOuts.class);
                startActivity(intent);

            }
        });


    }

    //method to read mealID and mealName from meal plans details
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