package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.momscare.R;
import com.example.momscare.Workout.DataBase.DBHelper;
import com.example.momscare.Workout.DataBase.WorkOut;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AllExercises extends AppCompatActivity {

    private ListView lworkout;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private FloatingActionButton addWO_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exercises);

        addWO_button =findViewById(R.id.addWO_button);
        lworkout = (ListView)findViewById(R.id.lworkouts);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);

        lworkout.setAdapter(adapter);


        viewAll();

    }


    public void viewAll(){
        DBHelper dbHelper = new DBHelper(this);

        List unames = dbHelper.readAllInfo();

        for(Object woName : unames){
            arrayList.add(woName.toString());
        }
        //Toast.makeText(this, unames.toString(),Toast.LENGTH_SHORT).show();

        lworkout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listItemName = parent.getItemAtPosition(position).toString();
                WorkOut wt = new WorkOut();
                wt = dbHelper.readOneWorkOutInfo(listItemName);


                String woId = wt.getWorkoutID();
                String name = wt.getWorkoutName();
                String pkg = wt.getWorkoutPackage();
                String due = wt.getWorkoutDuration();
                String cal = wt.getWorkoutCalorie();
                String steps = wt.getWorkoutSteps();


                Intent intent = new Intent(AllExercises.this, ModifyExercise.class);
                intent.putExtra("id", woId);
                intent.putExtra("name", name);
                intent.putExtra("pkg", pkg);
                intent.putExtra("due", due);
                intent.putExtra("cal", cal);
                intent.putExtra("step", steps);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        addWO_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdd = new Intent(AllExercises.this,AddExercise.class);
                startActivity(backToAdd);
            }
        });
    }
}