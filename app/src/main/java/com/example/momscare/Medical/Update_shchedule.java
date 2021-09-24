package com.example.momscare.Medical;

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

import com.example.momscare.Medical.Database.MsDBHelper;
//import com.example.momscare.Nutrition.ViewMeals;
import com.example.momscare.R;
//import com.example.fitnes.Supplement.ViewSupplement;
import com.example.momscare.ToDo_List.Database.UserManagementDBHelper;
//import com.example.momscare.ToDo_List.UpdateToDo;
import com.example.momscare.Workout.WorkOuts;

public class Update_shchedule extends AppCompatActivity {

    EditText title_day, title_date,title_time;
    Button update_button, delete_button;

    String id, name, date,time;

    //bottom navigation button
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shc);

        title_day = findViewById(R.id.txt43);
        title_date= findViewById(R.id.txt44);
        title_time= findViewById(R.id.txttime);
        update_button = findViewById(R.id.btn8);
        delete_button = findViewById(R.id.btndelete);


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
            ab.setTitle(name);
        }

        //update list
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MsDBHelper  MsDBHelper= new MsDBHelper(Update_shchedule.this);

                name = title_day.getText().toString().trim();
                date =title_date.getText().toString().trim();
                time =title_time.getText().toString().trim();


                MsDBHelper.updateData(id, name, date,time);

                Intent intent = new Intent(Update_shchedule.this,MedicalShechedule.class);

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

                Intent intent = new Intent(Update_shchedule.this, MedicalShechedule.class);
                startActivity(intent);

            }
        });

        //redirects to workout
        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Update_shchedule.this, WorkOuts.class);
                startActivity(intent);

            }
        });



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
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("date")){
            id = getIntent().getStringExtra("id");
            name= getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");



            //Setting Intent Data
            title_day.setText(name);
            title_date.setText(date);
            title_time.setText(time);
            Log.d("dataaa", name+" "+date+"");

        }else{
            Log.d("errorr","no data");
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + "List ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MsDBHelper  MsDBHelper= new MsDBHelper(Update_shchedule.this);
                MsDBHelper.deleteOneRow(id);
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