package com.example.momscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.momscare.Medical.Medical_info;
import com.example.momscare.ToDo_List.SignIn;
import com.example.momscare.ToDo_List.SignUp;


public class WelcomeIntaface extends AppCompatActivity {
    Button btn,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_intaface);
        btn = findViewById(R.id.loginbtnwelcom);
        btn2 = findViewById(R.id.rebtnwelcome);
    }

    public void Register(View view){
        Intent log = new Intent(WelcomeIntaface.this, SignUp.class);
        startActivity(log);
    }
    public void Login(View view){
        Intent reg = new Intent(WelcomeIntaface.this, MainActivity.class);
        startActivity(reg);
    }
}