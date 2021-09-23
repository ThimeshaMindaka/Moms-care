package com.example.momscare.Medical.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MsDBHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "Moms.db";
    private static final int DATABASE_VERSION = 2;

    //Medical schedule list data base
    private static final String TABLE_NAME = "MedicalSchedule";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "list_name";
    private static final String COLUMN_DESCRIPTION = "list_description";

    public MsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

        @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
