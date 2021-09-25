package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.momscare.R;
public class WorkOuts extends AppCompatActivity {

    //navigation bar buttons
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_outs);

        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);
        navigate_todo =findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);
    }

    //lower navigation bar button page directions
    protected void onResume() {
        super.onResume();


        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkOuts.this, WorkOuts.class);
                startActivity(intent);

            }
        });




    }

    public void changeFragment(View view){

        Fragment fragment;//object or the variable name must always be in simple letters

        if(view == findViewById(R.id.pckg_btn1)){//checking if we clicked the fragment 1 button
            fragment = new PostpartumBodyCoachingFragment();//fragment 1 object is created
            FragmentManager fm = getSupportFragmentManager();//make an object of fragment
            FragmentTransaction ft = fm.beginTransaction();//using the fragment manager object then we create an fragmnet transaction objetct
            ft.replace(R.id.fgmntDefault,fragment);//we can have add.replace or remove fragment options
            ft.commit();//at this point transaction finishes
        }

        if(view == findViewById(R.id.pckg_btn2)){
            fragment = new PrenatalBodyCoachingFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fgmntDefault,fragment);
            ft.commit();
        }

    }


}