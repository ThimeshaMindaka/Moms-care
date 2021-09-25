package com.example.momscare.Workout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.momscare.R;
import com.example.momscare.Workout.DataBase.DBHelper;
import com.example.momscare.Workout.DataBase.ModelClass;

public class ViewExercise extends AppCompatActivity {

    //navigation bar buttons
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;


    //notification channel variables
    String name ="Notification_channel";
    String CHANNEL_ID = "ID_1";
    String description = "Sample Notification";
    Button viewExe_btn3;


    TextView viewExe_tv2;//workOut Name
    TextView viewExe_tv5;//workout duration
    TextView viewExe_tv6;//workout steps
    TextView viewExe_tv1;//result display
    ImageView imageView2;
    ModelClass objectModelClass;//class for image Upload


    EditText viewExe_etv1;//no of repeats

    Button viewExe_btn2;//start button


    float woCalorie;
    String duration,workoutID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        //navigation buttons
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);
        navigate_todo =findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);

        Intent intent = getIntent();

        workoutID = intent.getStringExtra("id");
        String workoutName = intent.getStringExtra("name");
        String workoutPkg = intent.getStringExtra("pkg");
        String workoutDuration = intent.getStringExtra("due");
        String workoutCalorie = intent.getStringExtra("cal");
        String workoutStep = intent.getStringExtra("step");

        viewExe_tv2 =findViewById(R.id.viewExe_tv2);
        viewExe_tv5 =findViewById(R.id.viewExe_tv5);
        viewExe_tv6 =findViewById(R.id.viewExe_tv6);
        imageView2 =findViewById(R.id.imageView2);

        getImage(workoutID);

        viewExe_tv2.setText(workoutName);
        viewExe_tv5.setText(workoutDuration);
        viewExe_tv6.setText(workoutStep);


        viewExe_btn2 = findViewById(R.id.viewExe_btn2);


        woCalorie = Float.parseFloat(workoutCalorie);
        duration = workoutDuration;


        //notification
        viewExe_btn3 = findViewById(R.id.viewExe_btn3);
        //we check the version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            int importance = NotificationManager.IMPORTANCE_DEFAULT;//importance is the visibility level can either be default low or high

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);//this is optional

            //Then we have to Register the channel we created with the system; you can't change the importance
            //or other notification behaviour after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void getImage(String workoutID){
        DBHelper db = new DBHelper(this);
        objectModelClass = db.getObject(workoutID);

        if(objectModelClass == null){
            setTheWorkOutImage();
        }else{
            imageView2.setImageBitmap(objectModelClass.getImage());
        }

    }

    private void setTheWorkOutImage() {

        if(workoutID.equals("0001")){
            imageView2.setImageResource(R.drawable.wo_img1);
        }else if(workoutID.equals("0002")){
            imageView2.setImageResource(R.drawable.wo_img2);
        } else if(workoutID.equals("0003")){
            imageView2.setImageResource(R.drawable.wo_img3);
        }else if(workoutID.equals("0004")){
            imageView2.setImageResource(R.drawable.wo_img4);
        }else if(workoutID.equals("0005")){
            imageView2.setImageResource(R.drawable.wo_img5);
        }else if(workoutID.equals("0006")){
            imageView2.setImageResource(R.drawable.wo_img6);
        }else if(workoutID.equals("0007")){
            imageView2.setImageResource(R.drawable.wo_img7);
        }
    }

    //function to be performed when calories burnout button clicked
    public void calculateTotalBurnOut(View view){

        try {
            if(TextUtils.isEmpty(viewExe_etv1.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter no of times the exercise repeated",Toast.LENGTH_SHORT).show();
                viewExe_etv1.requestFocus();
                viewExe_etv1.setError("Please enter no of times the exercise repeated");
            }else{

                //float total = woCalorie * Float.parseFloat(viewExe_etv1.getText().toString());
                float input = Float.parseFloat(viewExe_etv1.getText().toString());
                float total = calcBurnOut(woCalorie, input);
                viewExe_tv1.setText("Total BurntOut = " + total);

                clearInputs();
            }
        }catch(NumberFormatException e){
            Toast.makeText(this, "Please input correct number format", Toast.LENGTH_SHORT).show();
        }
    }

    //calories burnOut Calculation
    public float calcBurnOut(float Calorie, float x) {
        return (float) Calorie * x;
    }

    //clearing the inputs
    public  void clearInputs(){
        viewExe_etv1.setText("");
    }

    public void moveToTimeCounter(View view){
        Intent timeCounter = new Intent(ViewExercise.this,Counter.class);
        timeCounter.putExtra("time",duration);
        startActivity(timeCounter);

    }


    public void showNotification(View view){
        Intent intent = new Intent(this,WeightConverter.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        //use to perform an action in the future
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        //then we have to set the details to be shown in the notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                //set the values now
                .setSmallIcon(R.drawable.image2)
                .setContentTitle(" Moms APP Notification")
                .setContentText("Lets Try on the latest Weight Converter Option!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0,builder.build());
    }


    //lower navigation bar button page directions
    protected void onResume() {
        super.onResume();



        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewExercise.this, WorkOuts.class);
                startActivity(intent);

            }
        });




    }
}