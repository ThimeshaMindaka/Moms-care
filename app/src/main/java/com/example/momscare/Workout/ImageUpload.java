package com.example.momscare.Workout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;
import com.example.momscare.Workout.DataBase.DBHelper;
import com.example.momscare.Workout.DataBase.ModelClass;

public class ImageUpload extends AppCompatActivity {

    private EditText imageDetailsET;
    private ImageView objectImageView;
    Bitmap imageToStore;
    DBHelper dbHelper;

    private static  final int PICK_IMAGE_REQUEST = 100;//any number other than to pass the request
    private Uri imageFilepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        //getting the id for the image to to be uploaded or deleted
        Intent imgId = getIntent();
        String imageId = imgId.getStringExtra("imageID");


        try{
            imageDetailsET =findViewById(R.id.wo_ImageUploadEtn1);
            objectImageView =findViewById(R.id.wo_image);

            if(imageId != null){
                imageDetailsET.setText(imageId);
            }

            dbHelper = new DBHelper(this);

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void chooseImage(View view){
        try{
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        try{
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData() !=null){
                imageFilepath =data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilepath);

                objectImageView.setImageBitmap(imageToStore);
            }


        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



    public void storeImage(View view){
        try{
            if(!imageDetailsET.getText().toString().isEmpty() && objectImageView.getDrawable() != null && imageToStore !=null)
            {
                dbHelper.storeImage(new ModelClass(imageDetailsET.getText().toString(),imageToStore));
                Intent intent = new Intent(ImageUpload.this,AllExercises.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Please select image name and image" , Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteImage(View view){
        try{
            if(!imageDetailsET.getText().toString().isEmpty())
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ImageUpload.this);
                dialog.setCancelable(false);
                dialog.setTitle("Get Fit App Workout Image Delete");
                dialog.setMessage("Are you sure you want to delete this image "+ imageDetailsET.getText().toString() +" ?" );
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        boolean result = dbHelper.deleteImageInfo(imageDetailsET.getText().toString());
                        if (result == false){
                            Toast.makeText(ImageUpload.this, "There is no such image stored", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(ImageUpload.this,AllExercises.class);
                        startActivity(intent);
                    }


                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                                //Remain within the same activity
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
            else{
                //Toast.makeText(this,"Please enter image Id to proceed " , Toast.LENGTH_SHORT).show();
                imageDetailsET.requestFocus();
                imageDetailsET.setError("Please enter image Id to proceed ");
            }



        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}