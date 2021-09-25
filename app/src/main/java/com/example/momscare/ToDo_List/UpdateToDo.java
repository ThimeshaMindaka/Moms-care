package com.example.momscare.ToDo_List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.momscare.Nutrition.ViewMeals;
import com.example.momscare.R;
import com.example.momscare.ToDo_List.Database.UserManagementDBHelper;
import com.example.momscare.Workout.WorkOuts;

public class UpdateToDo extends AppCompatActivity {

    //variables
    EditText title_input, description_input;
    Button update_button, delete_button;

    String id, title, description;

    //bottom navigation button
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_to_do);

        title_input = findViewById(R.id.title_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //bootom navigation button Id
        navigate_todo = findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);


        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        //update list
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                UserManagementDBHelper userManagementDBHelper = new UserManagementDBHelper(UpdateToDo.this);

                title = title_input.getText().toString().trim();
                description = description_input.getText().toString().trim();

                userManagementDBHelper.updateData(id, title, description);

                Intent intent = new Intent(UpdateToDo.this,To_Do_List.class);

                startActivity(intent);
            }
        });

        //delete list
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();

            }
        });


        //redirects to todoList
        navigate_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateToDo.this, To_Do_List.class);
                startActivity(intent);

            }
        });

        //redirects to workout
        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateToDo.this, WorkOuts.class);
                startActivity(intent);

            }
        });

        //redirects to meal list
       /* navigate_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateToDo.this, ViewMeals.class);
                startActivity(intent);

            }
        });*/

      /*  //redirects to supplement list
        navigate_supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateToDo.this, ViewSupplement.class);
                startActivity(intent);

            }
        });
*/

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("description")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");


            //Setting Intent Data
            title_input.setText(title);
            description_input.setText(description);

            Log.d("dataaa", title+" "+description);
        }else{
            Log.d("errorr","no data");
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + "List ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserManagementDBHelper userManagementDBHelper = new UserManagementDBHelper(UpdateToDo.this);
                userManagementDBHelper.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
