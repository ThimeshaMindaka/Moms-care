package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
import com.example.momscare.Workout.DataBase.DBHelper;
import com.example.momscare.Workout.AllExercises;
import com.example.momscare.Workout.ImageUpload;

public class AddExercise extends AppCompatActivity {

    EditText addExe_etn1, addExe_etn2,addExe_etn3;
    EditText addExe_etv1;
    EditText addExe_emt1;
    Button addExe_btn;
    Spinner addExe_spinner;
    Button addExeImage_btn;
    String imgIdVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        addExe_etn1 = findViewById(R.id.addExe_etn1);//workout id
        addExe_etn2 = findViewById(R.id.addExe_etn2);//time duration
        addExe_etn3 = findViewById(R.id.addExe_etn3);//calorie burnout

        addExe_etv1 = findViewById(R.id.addExe_etv1);//workout name
        addExe_emt1 = findViewById(R.id.addExe_emt1);//steps in the workout

        addExe_spinner = (Spinner)(findViewById(R.id.addExe_spinner));//packageType

        addExe_btn =findViewById(R.id.addExe_btn);//add button
        addExeImage_btn =findViewById(R.id.addExeImage_btn);//add image button

    }

    public void addData(View view){

        try{
            if(TextUtils.isEmpty(addExe_etn1.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter a workoutId",Toast.LENGTH_SHORT).show();
                addExe_etn1.requestFocus();
                addExe_etn1.setError("Please enter a workoutId");
            }else if(TextUtils.isEmpty(addExe_etv1.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter a workout Name",Toast.LENGTH_SHORT).show();
                addExe_etv1.requestFocus();
                addExe_etv1.setError("Please enter a workout Name");
            }else if(TextUtils.isEmpty(addExe_etn3.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter Calorie amount",Toast.LENGTH_SHORT).show();
                addExe_etn3.requestFocus();
                addExe_etn3.setError("Please enter Calorie amount");
            }else if(TextUtils.isEmpty(addExe_etn2.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter Duration",Toast.LENGTH_SHORT).show();
                addExe_etn2.requestFocus();
                addExe_etn2.setError("Please enter Duration");
            }else if(TextUtils.isEmpty(addExe_emt1.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter Steps",Toast.LENGTH_SHORT).show();
                addExe_emt1.requestFocus();
                addExe_emt1.setError("Please enter Steps");
            }else{

                DBHelper dbHelper = new DBHelper(this);
                long resultVal = dbHelper.insertInfo(addExe_etn1.getText().toString(),
                        addExe_etv1.getText().toString(),
                        addExe_spinner.getSelectedItem().toString(),
                        Integer.parseInt(addExe_etn3.getText().toString()),
                        Integer.parseInt(addExe_etn2.getText().toString()),
                        addExe_emt1.getText().toString());

                if(resultVal > 0){
                    Toast.makeText(this,"Workout Plan successfully added",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, AllExercises.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Workout Plan could not be added successfully",Toast.LENGTH_LONG).show();
                }

                emptyFilledData();

            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Invalid Number inserted check again",Toast.LENGTH_SHORT).show();
        }

    }

    public void emptyFilledData(){
        imgIdVal = addExe_etn1.getText().toString();
        addExe_etn1.setText("");
        addExe_etv1.setText("");
        addExe_etn3.setText("");
        addExe_etn2.setText("");
        addExe_emt1.setText("");
    }

    public void moveToUploadImage(View view){
        Intent intent = new Intent(AddExercise.this, ImageUpload.class);
        intent.putExtra("imageID",imgIdVal);
        startActivity(intent);
    }

}