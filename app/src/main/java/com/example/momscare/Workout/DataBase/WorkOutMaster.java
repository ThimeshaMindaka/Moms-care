package com.example.momscare.Workout.DataBase;

import android.provider.BaseColumns;

public class WorkOutMaster {

    public WorkOutMaster() {
    }

    //define the table name and column names

    public static class WorkOuts implements BaseColumns{

        public static final String TABLE_NAMES = "Exercise";
        public static final String COLUMN_NAME_WORKOUT_ID = "WorkOutID";
        public static final String COLUMN_NAME_WORKOUT_NAME = "WorkOutName";
        public static final String COLUMN_NAME_WORKOUT_PACKAGE = "PackageType";
        public static final String COLUMN_NAME_WORKOUT_CALORY = "CaloryBurnt";
        public static final String COLUMN_NAME_WORKOUT_DURATION = "Duration";
        public static final String COLUMN_NAME_WORKOUT_STEPS = "Steps";



    }

}
