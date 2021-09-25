package com.example.momscare.Medical;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.fitnes.Nutrition.ViewMeals;
import com.example.momscare.Medical.Add_shchedule;
import com.example.momscare.Medical.Database.DocterAdepter;
import com.example.momscare.R;
//import com.example.fitnes.Supplement.ViewSupplement;
//import com.example.momscare.ToDo_List.Add_ToDo;
import com.example.momscare.Medical.Database.DocterAdepter;
import com.example.momscare.Medical.Database.MsDBHelper;
import com.example.momscare.Workout.WorkOuts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

    public class MedicalShechedule   extends AppCompatActivity {

        //variables
        Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement, delete_button ;

        RecyclerView recyclerView;
        FloatingActionButton add_button;
        ImageView empty_imageview;
        TextView no_data;

        com.example.momscare.Medical.Database.MsDBHelper MsDBHelper;
        ArrayList<String> list_id, list_title, list_date,list_time;
        com.example.momscare.Medical.Database.DocterAdepter DocterAdepter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_medical_shchedule);

            navigate_todo = findViewById(R.id.navigate_todo);
            navigate_workout = findViewById(R.id.navigate_workout);
            navigate_nutrition = findViewById(R.id.navigate_nutrition);
            navigate_supplement = findViewById(R.id.navigate_supplement);


            recyclerView = findViewById(R.id.recyclerView);
            add_button = findViewById(R.id.add_button);
            delete_button = findViewById(R.id.btndelete);
            empty_imageview = findViewById(R.id.empty_imageview);

            no_data = findViewById(R.id.no_data);

            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MedicalShechedule.this, Add_shchedule.class);

                    startActivity(intent);
                }
            });

            navigate_todo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MedicalShechedule.this, MedicalShechedule.class);
                    startActivity(intent);

                }
            });

            //redirects to workout
            navigate_workout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MedicalShechedule.this, WorkOuts.class);
                    startActivity(intent);

                }
            });





            MsDBHelper = new MsDBHelper(MedicalShechedule.this);

            list_id = new ArrayList<>();
            list_title = new ArrayList<>();
            list_date = new ArrayList<>();
            list_time = new ArrayList<>();

            storeDataInArrays();

            DocterAdepter= new DocterAdepter(MedicalShechedule.this, this, list_id, list_title, list_date,list_time);
            recyclerView.setAdapter(DocterAdepter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MedicalShechedule.this));
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                recreate();
            }
        }

        void storeDataInArrays() {
            Cursor cursor = MsDBHelper.readAllData();
            if (cursor.getCount() == 0) {
                empty_imageview.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.VISIBLE);
            } else {
                while (cursor.moveToNext()) {
                    list_id.add(cursor.getString(0));
                    list_title.add(cursor.getString(1));
                    list_date.add(cursor.getString(2));
                    list_time.add(cursor.getString(3));


                }
                empty_imageview.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
            }
        }


        //delete all layout
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.my_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        //delete all method
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.delete_all){
                confirmDialog();
            }
            return super.onOptionsItemSelected(item);
        }

        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete All?");
            builder.setMessage("Are you sure you want to delete all Medical Schedules ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MsDBHelper  MsDBHelper= new  MsDBHelper(MedicalShechedule.this);
                    MsDBHelper.deleteAllData();
                    //Refresh Activity
                    Intent intent = new Intent(MedicalShechedule.this,MedicalShechedule.class);
                    startActivity(intent);
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


