package com.example.momscare.Nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class macros extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton nutrition_add_macro;


    DBHelper db ;
    ArrayList<String> id,foodName;
    NutritionMacroAdapter customAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macros);

        recyclerView = findViewById(R.id.macros_recyclerView);

        db = new DBHelper(macros.this);
        id = new ArrayList<>();
        foodName = new ArrayList<>();

        storeMacrosInArray();

        customAdapater = new NutritionMacroAdapter(macros.this,id,foodName);
        recyclerView.setAdapter(customAdapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(macros.this));


        nutrition_add_macro = findViewById(R.id.nutrition_add_macro);

        nutrition_add_macro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(macros.this, AddMacros.class);
                startActivity(intent);
            }
        });
    }

    //method to read id and foodName from macro details table
    void storeMacrosInArray(){
        Cursor cursor =  db.readMacroTableData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                foodName.add(cursor.getString(1));
            }
        }
    }
}