package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
//import com.example.momscare.ToDo_List.To_Do_List;
import com.example.momscare.Workout.WorkOuts;

public class user_meal_view extends AppCompatActivity {

    TextView um_bm01,um_bm02,um_bm03,um_bm04,um_lm01,um_lm02,um_lm03,um_lm04,um_dm01,um_dm02,um_dm03,um_dm04;
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_meal_view);

        //Log.d("mealId",(getIntent().getStringExtra("mealID")));

        //retrieve the mealID from intent
        String clickedRow = getIntent().getStringExtra("mealID");

        navigate_todo = findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);

        um_bm01 = findViewById(R.id.um_bm01);
        um_bm02= findViewById(R.id.um_bm02);
        um_bm03= findViewById(R.id.um_bm03);
        um_bm04= findViewById(R.id.um_bm04);
        um_lm01 = findViewById(R.id.um_lm01);
        um_lm02= findViewById(R.id.um_lm02);
        um_lm03= findViewById(R.id.um_lm03);
        um_lm04= findViewById(R.id.um_lm04);
        um_dm01 = findViewById(R.id.um_dm01);
        um_dm02= findViewById(R.id.um_dm02);
        um_dm03= findViewById(R.id.um_dm03);
        um_dm04= findViewById(R.id.um_dm04);

        dbHelper = new DBHelper(getApplicationContext());
        Cursor cursor = dbHelper.SearchMeal(clickedRow);

        if(cursor.moveToFirst()){

            um_bm01.setText(cursor.getString(2));
            um_bm02.setText(cursor.getString(3));
            um_bm03.setText(cursor.getString(4));
            um_bm04.setText(cursor.getString(5));
            um_lm01.setText(cursor.getString(6));
            um_lm02.setText(cursor.getString(7));
            um_lm03.setText(cursor.getString(8));
            um_lm04.setText(cursor.getString(9));
            um_dm01.setText(cursor.getString(10));
            um_dm02.setText(cursor.getString(11));
            um_dm03.setText(cursor.getString(12));
            um_dm04.setText(cursor.getString(13));

        }

        //bottom navigation buttons on click listners


        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(user_meal_view.this, WorkOuts.class);
                startActivity(intent);

            }
        });

        navigate_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(user_meal_view.this,ViewMeals.class);
                startActivity(intent);

            }
        });

       /* navigate_supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(user_meal_view.this, ViewSupplement.class);
                startActivity(intent);

            }
        });*/
    }
}