package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
import com.example.momscare.Workout.DataBase.DBHelper;
import com.example.momscare.Workout.DataBase.ModelClass;

public class ViewExerciseImage extends AppCompatActivity {

    String workOutImageID, workoutImageName;
    ImageView viewExe_ImgView;
    TextView wo_imageTitle;
    ModelClass objectModelClass;
    Button viewExeImg_btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_image);

        Intent intent = getIntent();

        workOutImageID = intent.getStringExtra("imageID");
        workoutImageName = intent.getStringExtra("imageName");

        viewExe_ImgView =findViewById(R.id.viewExe_ImgView);
        viewExeImg_btn1 = findViewById(R.id.viewExeImg_btn1);
        wo_imageTitle = findViewById(R.id.wo_imageTitle);

        wo_imageTitle.setText(workoutImageName);

        getImage(workOutImageID);

    }

    public void getImage(String workoutID){
        DBHelper db = new DBHelper(this);
        objectModelClass = db.getObject(workOutImageID);

        if(objectModelClass == null){
            setTheWorkOutImage();
        }else{
            viewExe_ImgView.setImageBitmap(objectModelClass.getImage());
        }

    }

    private void setTheWorkOutImage() {

        if(workOutImageID .equals("0001")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img1);
        }else if(workOutImageID .equals("0002")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img2);
        } else if(workOutImageID .equals("0003")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img3);
        }else if(workOutImageID .equals("0004")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img4);
        }else if(workOutImageID .equals("0005")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img5);
        }else if(workOutImageID .equals("0006")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img6);
        }else if(workOutImageID .equals("0007")){
            viewExe_ImgView.setImageResource(R.drawable.wo_img7);
        }
    }


    public void goToImageUpload(View view){
        Intent imageChange = new Intent(ViewExerciseImage.this,ImageUpload.class);
        imageChange.putExtra("imageID",workOutImageID);
        startActivity(imageChange);
    }
}