package com.example.momscare.Nutrition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Moms.db";
    private static final int DATABASE_VERSION = 2;


    private static final String TABLE_NAME_MACRO = "MacroDetails";
    private static final String TABLE_NAME_MEAL = "Meals";

    //query to create macro details table
    public static final String queryToMacro = "CREATE TABLE " + TABLE_NAME_MACRO +
            " (" + "Id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "foodName" + " TEXT," +
            "energy" + " DECIMAL(5,2)," +
            "fat" + " DECIMAL(5,2)," +
            "carb" + " DECIMAL(5,2)," +
            "fiber" + " DECIMAL(5,2)," +
            "protein" + " DECIMAL(5,2)," +
            "sodium" + " DECIMAL(5,2)," +
            "cholesterol" + " DECIMAL(5,2)," +
            "potasium" + " DECIMAL(5,2)," +
            "calories" + " DECIMAL(5,2));";

    //query to create macro details table
    public static final String queryToMeal = "CREATE TABLE " + TABLE_NAME_MEAL +
            " (" +
            "mealID" + " TEXT PRIMARY KEY,"+
            "mealName" + " TEXT," +
            "bm01" + " TEXT," +
            "bm02" + " TEXT," +
            "bm03" + " TEXT," +
            "bm04" + " TEXT," +
            "lm01" + " TEXT," +
            "lm02" + " TEXT," +
            "lm03" + " TEXT," +
            "lm04" + " TEXT," +
            "dm01" + " TEXT," +
            "dm02" + " TEXT," +
            "dm03" + " TEXT," +
            "dm04" + " TEXT);";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    //executing sql queries in onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(queryToMacro);
        db.execSQL(queryToMeal);

    }

    //method to add meals
    void addMeal(String mealID, String mealName, String bm01, String bm02, String bm03, String bm04, String lm01, String lm02, String lm03, String lm04, String dm01, String dm02, String dm03, String dm04){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("mealID", mealID);
        cv.put("mealName", mealName);
        cv.put("bm01", bm01);
        cv.put("bm02", bm02);
        cv.put("bm03", bm03);
        cv.put("bm04", bm04);
        cv.put("lm01", lm01);
        cv.put("lm02", lm02);
        cv.put("lm03", lm03);
        cv.put("lm04", lm04);
        cv.put("dm01", dm01);
        cv.put("dm02", dm02);
        cv.put("dm03", dm03);
        cv.put("dm04", dm04);

        long result = db.insert(TABLE_NAME_MEAL,null,cv);
        if(result == -1){
            Toast.makeText(context, "data insertion failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(context,AddMeal.class);
            intent.putExtra("retValue", result);
            context.sendBroadcast(intent);*/
        }
    }

    //method to add macro details
    void addMacro(String foodName, String energy, String fat, String carb, String fiber, String protein, String sodium, String cholesterol, String potasium, String calories){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("foodName", foodName);
        cv.put("energy", energy);
        cv.put("fat", fat);
        cv.put("carb", carb);
        cv.put("fiber", fiber);
        cv.put("protein", protein);
        cv.put("sodium", sodium);
        cv.put("cholesterol", cholesterol);
        cv.put("potasium", potasium);
        cv.put("calories", calories);


        long result = db.insert(TABLE_NAME_MACRO,null,cv);
        if(result == -1){
            Toast.makeText(context, "data insertion failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //method to read all data from macro details table
    Cursor readMacroTableData(){
        String query = "SELECT * FROM " + TABLE_NAME_MACRO;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //method to read all data from macro details table according to foodName
    Cursor SearchMacro(String foodName){

        String query = "SELECT * FROM " + TABLE_NAME_MACRO + " WHERE foodName LIKE " + "'" + foodName + "'";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }if (cursor.getCount() == 0){
            Toast.makeText(context,"Sorry no data found!",Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    //method to read all data from meal plans table
    Cursor readMealTableData(){
        String query = "SELECT * FROM " + TABLE_NAME_MEAL;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //method to read all data from meal plans table according to mealID
    Cursor SearchMeal(String mealID){

        String query = "SELECT * FROM " + TABLE_NAME_MEAL + " WHERE mealID LIKE " + "'" + mealID + "'";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }if (cursor.getCount() == 0){
            Toast.makeText(context,"Sorry no data found!",Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    //method to update row on meal plans table where matches the mealID
    void updateMealPlan(String mealID,String mealName, String bm01, String bm02, String bm03, String bm04, String lm01, String lm02, String lm03, String lm04, String dm01, String dm02, String dm03, String dm04){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("mealName", mealName);
        cv.put("bm01", bm01);
        cv.put("bm02", bm02);
        cv.put("bm03", bm03);
        cv.put("bm04", bm04);
        cv.put("lm01", lm01);
        cv.put("lm02", lm02);
        cv.put("lm03", lm03);
        cv.put("lm04", lm04);
        cv.put("dm01", dm01);
        cv.put("dm02", dm02);
        cv.put("dm03", dm03);
        cv.put("dm04", dm04);

        long result = db.update(TABLE_NAME_MEAL,cv,"mealID=?", new String[]{mealID});
        if(result == -1){
            Toast.makeText(context, "data update failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "data updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void readMealRow(String mealID){

    }

    //method to delete row on meal plans table where matches the mealID
    void deleteMealRow(String mealID){


        Log.d("data came",mealID);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_MEAL,"mealID=?",new String[]{mealID});

        if(result == -1){
            Toast.makeText(context, "data deletion failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "data deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MACRO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEAL);

    }

}
