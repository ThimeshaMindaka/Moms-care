package com.example.momscare.ToDo_List.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserManagementDBHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "Moms.db";
    private static final int DATABASE_VERSION = 2;

    //To do list data base
    private static final String TABLE_NAME = "TodoList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "list_name";
    private static final String COLUMN_DESCRIPTION = "list_description";

    //Sign Up page data base
    private static final String TABLE_NAME_USER = "User";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_AGE = "age";

    //image upload data base
    private static final String TABLE_NAME_IMAGE = "ImageUpload";
    private static final String COLUMN_USERNAMEIMAGE = "username";
    private static final String COLUMN_IMAGE = " image";



    //create a helper object to create, open, and/or manage a database
    public UserManagementDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create To do list table
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);

        //create user table
        String USER_QUERY = " CREATE TABLE " + TABLE_NAME_USER +
                " (" + COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_GENDER + " TEXT," +
                COLUMN_HEIGHT + " FLOAT," +
                COLUMN_WEIGHT + " FLOAT," +
                COLUMN_AGE + " INTEGER);";

        db.execSQL(USER_QUERY);


        //create upload image data base
        String queryImage = " CREATE TABLE " + TABLE_NAME_IMAGE +
                " (" + COLUMN_USERNAMEIMAGE + " TEXT PRIMARY KEY," +
                COLUMN_IMAGE + " TEXT);";
        db.execSQL(queryImage);

    }

    //upgrading the data base when we make changes to the schema
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_IMAGE);
        onCreate(db);

    }

    //Insert -> To do list insert to the data base
    public void addlist(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME, null, values);

        if(result ==  -1){
            Toast.makeText(context, "Failed to added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,  title + " Added successfully", Toast.LENGTH_SHORT).show();
        }

    }

    //fetch data from the result sets of the queries. You can create Cursor object using the cursor() method of the Connection object/class.

    //View -> To do list retrieve as list method
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //Update -> To do list update in data base method
    public void updateData(String row_id, String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);


        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //Delete One Row -> To do list delete one row from data base
    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    //Delete All -> To do list delete all data from data base
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


    //Insert -> insert user data into database table
    public boolean insertData(String username, String email, String password, String gender, float height,float weight, int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME,username);
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASSWORD,password);
        values.put(COLUMN_GENDER,gender);
        values.put(COLUMN_HEIGHT,height);
        values.put(COLUMN_WEIGHT,weight);
        values.put(COLUMN_AGE,age);

        long result = db.insert(TABLE_NAME_USER,null,values);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    //Authentication Username -> check username from data base in login page
    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_NAME_USER + " where " + COLUMN_USERNAME + " = ? " , new String[] {username});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    //Authentication Password -> check password from data base in login page
    public Boolean checkpassword(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_NAME_USER + " where " + COLUMN_USERNAME + " =  ?  AND " +COLUMN_PASSWORD + " = ? ", new String[] {username,password});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    //fetch data from the result sets of the queries. You can create Cursor object using the cursor() method of the Connection object/class.

    //View -> retrieve user data in user profile page
    public Cursor retriveDataUserProfile(String username){
        String query = "SELECT * FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_USERNAME + " LIKE " + "'" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }if(cursor.getCount() == 0){
            Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    //Update -> update user details
    public void updateUserProfile(String username, String email , String gender, float height, float weight, int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,username);
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_GENDER,gender);
        values.put(COLUMN_HEIGHT,height);
        values.put(COLUMN_WEIGHT,weight);
        values.put(COLUMN_AGE,age);

        long result = db.update(TABLE_NAME_USER, values, "username=?", new String[]{username});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //Delete -> delete user account from data base
    public void deleteAccount(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_USER, "username=?", new String[]{username});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    //Insert -> insert image into data base
    public boolean insertimage(String username, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAMEIMAGE,username);
        values.put(COLUMN_IMAGE,image);

        long result = db.insert(TABLE_NAME_IMAGE,null,values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //fetch data from the result sets of the queries. You can create Cursor object using the cursor() method of the Connection object/class.
    //View -> retrieve user data in user profile page
    public Cursor retrieveimage(String username){
        String imageretirveQUERY = " SELECT * FROM " + TABLE_NAME_IMAGE + " WHERE " + COLUMN_USERNAMEIMAGE + " LIKE " + "'" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(imageretirveQUERY, null);
        }if(cursor.getCount() == 0){
            //Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    //update profile pic
    public void update_photo(String username, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAMEIMAGE,username);
        values.put(COLUMN_IMAGE,image);

        long result = db.update(TABLE_NAME_IMAGE, values, "username=?", new String[]{username});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    //Delete -> delete raw table name image
    public void deletimage(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_IMAGE, "username=?", new String[]{username});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }

    }


}


