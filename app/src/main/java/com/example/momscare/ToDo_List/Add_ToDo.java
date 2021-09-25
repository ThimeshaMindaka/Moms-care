package com.example.momscare.ToDo_List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
import com.example.momscare.ToDo_List.Database.UserManagementDBHelper;
import com.example.momscare.Workout.WorkOuts;

public class Add_ToDo extends AppCompatActivity {

    //variables
    EditText title_input;
    EditText description_input;
    Button add_button;

    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__to_do);

        //find view by id
        title_input = findViewById(R.id.title_input);
        description_input = findViewById(R.id.description_input);
        add_button = findViewById(R.id.add_button);
        navigate_todo = findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);

        //method to add data to db
        add_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                UserManagementDBHelper userManagementDBHelper = new UserManagementDBHelper(Add_ToDo.this);
                userManagementDBHelper.addlist(title_input.getText().toString().trim(),
                        description_input.getText().toString().trim());

                title_input.setText("");
                description_input.setText("");

            }
        });

        //redirects to todoList
        navigate_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Add_ToDo.this, To_Do_List.class);
                startActivity(intent);

            }
        });

        //redirects to workout
        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Add_ToDo.this, WorkOuts.class);
                startActivity(intent);

            }
        });
/*
        //redirects to meal list
        navigate_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Add_ToDo.this, ViewMeals.class);
                startActivity(intent);

            }
        });

        //redirects to supplement list
      navigate_supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Add_ToDo.this, ViewSupplement.class);
                startActivity(intent);

            }
        });*/

    }


}

