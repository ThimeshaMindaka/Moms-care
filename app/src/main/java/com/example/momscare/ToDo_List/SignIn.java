package com.example.momscare.ToDo_List;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.MainActivity;
//import com.example.momscare.Nutrition.NutritionNavigate;
import com.example.momscare.R;
import com.example.momscare.ToDo_List.Database.UserManagementDBHelper;
//import com.example.momscare.Workout.WorkoutDashBoard;


public class SignIn extends AppCompatActivity {

    //variables
    EditText username,password;
    TextView signUp;
    Button SignIn;

    UserManagementDBHelper userManagementDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in2);

        //find view by id
        username = findViewById(R.id.SignInUserName);
        password = findViewById(R.id.SignInPassword);
        SignIn =findViewById(R.id.SignIn);
        signUp = findViewById(R.id.SignIntxtNewUserLink);

        //create object
        userManagementDBHelper = new UserManagementDBHelper(this);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                String work_username,work_password,nutrition_username,nutrition_password;

                work_username = "achini";
                work_password = "123";
                nutrition_username = "thimesh";
                nutrition_password = "1234";

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(SignIn.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserPassword = userManagementDBHelper.checkpassword(user,pass);
                    if(checkuserPassword == true) {

                        Toast.makeText(SignIn.this, "Login to the momscare", Toast.LENGTH_SHORT).show();
                     if{
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                        /*f((user.equals(work_username) && (pass.equals(work_password) ))){
                            Intent intent = new Intent(getApplicationContext(), WorkoutDashBoard.class);
                            startActivity(intent);
                        }

                        else if((user.equals(nutrition_username) && (pass.equals(nutrition_password) ))){
                            Intent intent = new Intent(getApplicationContext(), NutritionNavigate.class);
                            startActivity(intent);
                        }*/
                    }else {
                        Toast.makeText(SignIn.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });


    }
}