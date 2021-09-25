package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

public class WorkoutDashBoard extends AppCompatActivity {

    Button btnAddExercise,btnUpdateExercise,btndltExercise,btnWeightConverter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_dash_board);

        btnAddExercise =findViewById(R.id.btnAddExercise);
        btnUpdateExercise =findViewById(R.id.btnUpdateExercise);
        btndltExercise =findViewById(R.id.btndltExercise);
        btnWeightConverter =findViewById(R.id.btnWeightConverter);

    }

    public void moveToAllList(View view){
        Intent moveToList = new Intent(WorkoutDashBoard.this,AllExercises.class);
        startActivity(moveToList);
    }

    public void moveToAddExercise(View view){
        Intent moveToAdd = new Intent(WorkoutDashBoard.this,AddExercise.class);
        startActivity(moveToAdd);
    }

    public void moveToWeightConverter(View view){
        Intent moveToWC = new Intent(WorkoutDashBoard.this,WeightConverter.class);
        startActivity(moveToWC);
    }
}