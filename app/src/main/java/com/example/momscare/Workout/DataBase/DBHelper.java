package com.example.momscare.Workout.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_PATH = "/data/data/com.example.momscare/databases/";
    Context context;
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInByte;
    public static final String DATABASE_NAME="Moms.db";

    String createTableQuery ="CREATE TABLE ImageInfo (imageName TEXT UNIQUE NOT NULL"+",image BLOB)";

    public DBHelper( Context context) {

        super(context, DATABASE_NAME, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //query to create the table if there is no table existing

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + WorkOutMaster.WorkOuts.TABLE_NAMES + "(" +
                        WorkOutMaster.WorkOuts._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                        WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID + " TEXT NOT NULL UNIQUE, "+
                        WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME + " TEXT NOT NULL UNIQUE, "+
                        WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE + " TEXT,"+
                        WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY + " INTEGER NOT NULL, "+
                        WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION +" INTEGER NOT NULL, "+
                        WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS + " TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_ENTRIES);

        //image info table creation
        try{
            db.execSQL(createTableQuery);
            Toast.makeText(context, "Table created successfully inside our database", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkOutMaster.WorkOuts.TABLE_NAMES);
        onCreate(db);

    }





    public long insertInfo(String workoutID, String workName, String workPckg, int cal, int duration, String steps){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID,workoutID);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME,workName);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE,workPckg);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY,cal);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION,duration);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS,steps);

        long results = db.insert(WorkOutMaster.WorkOuts.TABLE_NAMES,null,values);
        return results;
    }


    public Cursor getAllData(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+WorkOutMaster.WorkOuts.TABLE_NAMES;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    //this method will read all the information within the table
    public List readAllInfo(){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                WorkOutMaster.WorkOuts._ID,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS

        };


        String sortOrder = WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID + " DESC";

        Cursor cursor = db.query(WorkOutMaster.WorkOuts.TABLE_NAMES,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();


        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME));
            userNames.add(username);

        }

        cursor.close();

        return userNames;
    }

    //this method will read all the details of selected one record of the  workout table
    public WorkOut readOneWorkOutInfo(String req){

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+WorkOutMaster.WorkOuts.TABLE_NAMES + " WHERE "
                + WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME + "  = '" + req +"'";

        Cursor data = db.rawQuery(query,null);


        WorkOut oneWorkOut = new WorkOut();


        if( data != null)
            data.moveToFirst();

        String woID = data.getString(data.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID));
        oneWorkOut.setWorkoutID(woID);

        String woName = data.getString(data.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME));
        oneWorkOut.setWorkoutName(woName);

        String woPackage = data.getString(data.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE));
        oneWorkOut.setWorkoutPackage(woPackage);

        String woCalory = data.getString(data.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY));
        oneWorkOut.setWorkoutCalorie(woCalory);

        String woDuration = data.getString(data.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION));
        oneWorkOut.setWorkoutDuration(woDuration);

        String woSteps = data.getString(data.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS));
        oneWorkOut.setWorkoutSteps(woSteps);
        data.close();
        return oneWorkOut;
    }

    //this method will delete a selected record using the workout name
    public boolean deleteWorkOutInfo(String name){
        SQLiteDatabase db = getReadableDatabase();
        String selection = WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID + " LIKE ?";
        String[] selectionArgs = { name };
        int result = db.delete(WorkOutMaster.WorkOuts.TABLE_NAMES,selection,selectionArgs);

        if(result >0){
            return true;
        }else{
            return false;
        }

    }

    //this method will update a selected one record by using the workout id
    public int updateWorkOutInfo (String woId, String woName, String woPckg, int cal, int duration, String steps){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID,woId);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME,woName);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE,woPckg);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY,cal);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION,duration);
        values.put(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS,steps);

        String selection = WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID + " LIKE ?";
        String[] selectionArgs = {woId};

        try{
            int count = db.update(
                    WorkOutMaster.WorkOuts.TABLE_NAMES,
                    values,
                    selection,
                    selectionArgs
            );
            return count;
        }catch(SQLException e){
            return -2;
        }

    }

    //this method will read all records of package muscle gain
    public List readAllMuscleGainPackageInfo(){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                WorkOutMaster.WorkOuts._ID,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS
        };

        String selection = WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE + "  = ? ";
        String []selectionArgs = {"Postpartum"};

        Cursor cursor = db.query(WorkOutMaster.WorkOuts.TABLE_NAMES,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        List muscleGain = new ArrayList<>();


        while(cursor.moveToNext()){
            String musclesGain = cursor.getString(cursor.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME));
            muscleGain.add(musclesGain);

        }

        cursor.close();

        return muscleGain;
    }

    //this method will read all records of package fat loss
    public List readAllFatLossPackageInfo(){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                WorkOutMaster.WorkOuts._ID,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_ID,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_CALORY,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_DURATION,
                WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_STEPS

        };

        String selection = WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_PACKAGE + "  = ? ";
        String []selectionArgs = {"Prenatal"};

        Cursor cursor = db.query(WorkOutMaster.WorkOuts.TABLE_NAMES,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        List fatLoss = new ArrayList<>();


        while(cursor.moveToNext()){
            String fatsLoss = cursor.getString(cursor.getColumnIndexOrThrow(WorkOutMaster.WorkOuts.COLUMN_NAME_WORKOUT_NAME));
            fatLoss.add(fatsLoss);

        }

        cursor.close();

        return fatLoss;
    }

    //insert an image to imageInfo

    public void storeImage(ModelClass objectModelClass){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

            imageInByte =objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();

            objectContentValues.put("imageName",objectModelClass.getImageName());
            objectContentValues.put("image",imageInByte);

            long checkIfQueryRuns = db.insert("ImageInfo",null,objectContentValues);

            if(checkIfQueryRuns !=-1){
                Toast.makeText(context, "Data added to our table", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Data not added to our table", Toast.LENGTH_SHORT).show();
            }


        }catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //retrieve the specific image from imageInfo
    public ModelClass getObject(String imageName) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ModelClass oModelClass = new ModelClass();
            String[] selectionArgs = {imageName};
            Cursor objectC = db.rawQuery("select * from imageInfo where imageName = ? ",selectionArgs);
            if(objectC.getCount() !=0){
                objectC.moveToFirst();

                String nameOfImage = objectC.getString(0);
                byte [] imageByte = objectC.getBlob(1);

                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                oModelClass.setImageName(nameOfImage);
                oModelClass.setImage(objectBitmap);

                return  oModelClass;

            }else{
                Toast.makeText(context, "No values", Toast.LENGTH_SHORT).show();
                return  null;
            }

        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }


    }

    public boolean deleteImageInfo(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = { name };
        int result = db.delete("ImageInfo","imageName = ?",selectionArgs);
        if(result >0){
            Toast.makeText(context, "Image deleted successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }

    }
}

