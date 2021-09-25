package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

public class AddMacros extends AppCompatActivity {

    EditText title_foodName,title_energy,title_fat,title_carb,title_fiber,title_protein,title_sodium,title_cholesterol,title_potasium,title_calories;
    Button button_add_macros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_macros);

        title_foodName = findViewById(R.id.title_foodName);
        title_energy = findViewById(R.id.title_energy);
        title_fat = findViewById(R.id.title_fat);
        title_carb = findViewById(R.id.title_carb);
        title_fiber = findViewById(R.id.title_fiber);
        title_protein = findViewById(R.id.title_protein);
        title_sodium = findViewById(R.id.title_sodium);
        title_cholesterol = findViewById(R.id.title_cholesterol);
        title_potasium = findViewById(R.id.title_potasium);
        title_calories = findViewById(R.id.title_calories);

        button_add_macros = findViewById(R.id.button_add_macros);

        button_add_macros.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                String input_foodName = title_foodName.getText().toString();
                String input_energy = title_energy.getText().toString();
                String input_fat = title_fat.getText().toString();
                String input_carb = title_carb.getText().toString();
                String input_fiber = title_fiber.getText().toString();
                String input_protein = title_protein.getText().toString();
                String input_sodium = title_sodium.getText().toString();
                String input_cholesterol = title_cholesterol.getText().toString();
                String input_potasium = title_potasium.getText().toString();
                String input_calories = title_calories.getText().toString();

                boolean isInputsEmpty = input_energy.isEmpty() || input_fat.isEmpty() || input_carb.isEmpty() || input_fiber.isEmpty() || input_protein.isEmpty() || input_sodium.isEmpty() || input_cholesterol.isEmpty() || input_potasium.isEmpty() || input_calories.isEmpty();

                String nameVal = "[a-zA-Z ]+";;

                if(input_foodName.isEmpty()){
                    title_foodName.requestFocus();
                    title_foodName.setError("Field cannot be empty");
                }else if(!input_foodName.matches(nameVal)){
                    title_foodName.requestFocus();
                    title_foodName.setError("invalid characters!");
                }else if(isInputsEmpty == true){
                    Toast.makeText(getApplicationContext(),"please fill all the fields!",Toast.LENGTH_SHORT).show();
                }else {

                    DBHelper dbHelper = new DBHelper(AddMacros.this);
                    SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                    dbHelper.addMacro(title_foodName.getText().toString().trim(),
                            title_energy.getText().toString().trim(),
                            title_fat.getText().toString().trim(),
                            title_carb.getText().toString().trim(),
                            title_fiber.getText().toString().trim(),
                            title_protein.getText().toString().trim(),
                            title_sodium.getText().toString().trim(),
                            title_cholesterol.getText().toString().trim(),
                            title_potasium.getText().toString().trim(),
                            title_calories.getText().toString().trim());


                    Intent intent = new Intent(AddMacros.this, macros.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}