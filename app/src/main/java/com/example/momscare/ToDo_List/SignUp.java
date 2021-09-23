package com.example.momscare.ToDo_List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
import com.example.momscare.ToDo_List.Database.UserManagementDBHelper;
import com.example.momscare.ToDo_List.BMI;
import com.example.momscare.ToDo_List.SignIn;

public class SignUp extends AppCompatActivity {

    //variables
    EditText username,email,password,gender,height,weight,age;
    Button SignUpbtnSignUp, SignInbtnSignIn;

    UserManagementDBHelper userManagementDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.SignUpName);
        email = findViewById(R.id.SignUpEmail);
        password = findViewById(R.id.SignUpPassword);
        gender = findViewById(R.id.SignUpGender);
        height = findViewById(R.id.SignUpHeight);
        weight = findViewById(R.id.SignUpWeight);
        age = findViewById(R.id.SignUpAge);

        SignInbtnSignIn = findViewById(R.id.SignInbtnSignIn);
        Button SignUpbtnSignUp  = (Button)findViewById(R.id.SignUpbtnSignUp);
        userManagementDBHelper = new UserManagementDBHelper(this);

        SignUpbtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String ema = email.getText().toString();
                String pass = password.getText().toString();
                String gen = gender.getText().toString();

                if(user.equals("") || ema.equals("") || pass.equals("") || gen.equals("")){
                    username.requestFocus();
                    username.setError("Field cannot be empty");
                    email.requestFocus();
                    email.setError("Field cannot be empty");
                    password.requestFocus();
                    password.setError("Field cannot be empty");
                    gender.requestFocus();
                    gender.setError("Field cannot be empty");
                    height.requestFocus();
                    height.setError("Field cannot be empty");
                    weight.requestFocus();
                    weight.setError("Field cannot be empty");
                    age.requestFocus();
                    age.setError("Field cannot be empty");

                }else{
                    if(pass.equals((pass))){
                        Boolean checkuser = userManagementDBHelper.checkusername(user);
                        if(checkuser == false){
                            float heit = Float.valueOf(height.getText().toString());
                            float weit = Float.valueOf(weight.getText().toString());
                            int agt = Integer.valueOf(age.getText().toString());
                            Boolean insert = userManagementDBHelper.insertData(user,ema,pass,gen,heit,weit,agt);
                            if(insert == true){
                                Toast.makeText(SignUp.this, "Welcome to the GET FIT", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), BMI.class);
                                intent.putExtra("height",height.getText().toString());
                                intent.putExtra("weight",weight.getText().toString());
                                intent.putExtra("age",age.getText().toString());
                                intent.putExtra("username",user);

                                startActivity(intent);
                            }else{

                                Toast.makeText(SignUp.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                            }
                        }else{

                            Toast.makeText(SignUp.this, "You are Already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        SignInbtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignIn.class);

                startActivity(intent);
            }
        });

    }
}