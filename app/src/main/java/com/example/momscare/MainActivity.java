package com.example.momscare;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.momscare.Nutrition.ViewMeals;
import com.example.momscare.Nutrition.macroCal;
//import com.example.momscare.ToDo_List.To_Do_List;
//import com.example.momscare.ToDo_List.UserProfile;
import com.example.momscare.Workout.WeightConverter;
import com.example.momscare.Workout.WorkOuts;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Button btnWeightCon, navigate_todo, navigate_workout, navigate_nutrition, navigate_supplement,btnMacroCal;


    //for the side nav bar
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("username");

        btnMacroCal = findViewById(R.id.btnMacroCal);
        btnWeightCon = findViewById(R.id.btnWeightCon);


        //side nav bar
        dl = findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);


        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

               /* if(id == R.id.myProfile){

                    Intent profile = new Intent(MainActivity.this, UserProfile.class);
                    profile.putExtra("username",username);
                    startActivity(profile);
                }else if(id == R.id.toDoNav){

                    Intent todoNav = new Intent(MainActivity.this, To_Do_List.class);
                    startActivity(todoNav);
                }*/
                 if(id == R.id.nutritionNav){

                    Intent nutritionNav = new Intent(MainActivity.this, ViewMeals.class);
                    startActivity(nutritionNav);
                }
                if (id == R.id.woNav) {

                    Intent woNav = new Intent(MainActivity.this, WorkOuts.class);
                    startActivity(woNav);
                }

                return true;
            }
        });

        //bottom navigation bar
        navigate_todo = findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);


        //redirects to weight convertor page
        btnWeightCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeightConverter.class);
                startActivity(intent);
            }
        });
        //redirects to macro finder page
        btnMacroCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, macroCal.class);
                startActivity(intent);

            }
        });


        //redirects to workout
        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, WorkOuts.class);
                startActivity(intent);

            }
        });
        //redirects to meal list
        navigate_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ViewMeals.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }
}
