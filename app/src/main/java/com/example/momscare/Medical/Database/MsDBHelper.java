package com.example.momscare.Medical.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MsDBHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "Moms.db";
    private static final int DATABASE_VERSION = 2;

    //Medical schedule list data base
    private static final String TABLE_NAME = "MedicalSchedule";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "schedule_name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";

    public MsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

        @Override
    public void onCreate(SQLiteDatabase db) {

            //create Schedule list table
            String query = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_TIME + "TEXT);";
            db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data in to medical schedule
    public void addlist(String name, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        long result = db.insert(TABLE_NAME, null, values);

        if(result ==  -1){
            Toast.makeText(context, "Failed to added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,  name + " Added successfully", Toast.LENGTH_SHORT).show();
        }

    }

    //View -> Medical Schedule retrieve as list method
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    //Update -> Medical Schedule update in data base method
    public void updateData(String row_id, String name, String date,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);


        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //Delete All -> Medical Shedule delete all data from data base
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    //Delete One Row -> Medical Schedule delete one row from data base
    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
