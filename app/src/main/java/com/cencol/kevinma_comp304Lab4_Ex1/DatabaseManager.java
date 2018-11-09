package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    //database name and version
    private static final String DATABASE_NAME = "HospitalDB";
    private static final int DATABASE_VERSION = 1;
    // table names and table creator strings (SQL statement to create the tables)
    // should be set from within calling activity
    private static String[] tableNames;
    private static String[] tableCreatorStrings;

    //
    // no-argument constructor
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time.
    // This is where the creation of tables and the initial population
    // of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {

        //comes here not on dbinit, but rather the first time programmatically select/insert into the db, is when the execution goes into this "onCreate" method
        for (String tableCreatorString : tableCreatorStrings) {
            //create the tables
            db.execSQL(tableCreatorString);
        }

    }

    //
    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables,
    // add tables, or do anything else it needs to upgrade
    // to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for (String tableName : tableNames) {
            //drop table if existed
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
        //recreate the table
        onCreate(db);
    }
    //
    //
    //
    // initialize table names and CREATE TABLE statement
    // called by activity to create a table in the database
    // The following arguments should be passed:
    // tableName - a String variable which holds the table name
    // tableCreatorString - a String variable which holds the CREATE Table statement

    public void dbInitialize(String[] tableNames, String[] tableCreatorStrings) {
        this.tableNames = tableNames;
        this.tableCreatorStrings = tableCreatorStrings;
    }

    //
    // CRUD Operations
    //
    //This method is called by the activity to add a row in the table
    // The following arguments should be passed:
    // values - a ContentValues object that holds row values
    public boolean addRow(String tableName, ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the row
        long nr = db.insert(tableName, null, values);
        db.close(); //close database connection
        return nr > -1;
    }

    public Nurse getNurseById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Nurse where nurseId='" + String.valueOf(id) + "'", null);
        Nurse nurse = new Nurse(); //create a new Nurse object
        if (cursor.moveToFirst()) { //if row exists, move to first row
            //initialize the instance variables of task object
            nurse.setNurseId(cursor.getInt(cursor.getColumnIndex("nurseId")));
            nurse.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            nurse.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            nurse.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
            nurse.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            cursor.close();

        } else {
            nurse = null;
        }
        db.close();
        return nurse;
    }

    public Doctor getDoctorById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Doctor where doctorId='" + String.valueOf(id) + "'", null);
        Doctor doctor = new Doctor(); //create a new Doctor object
        if (cursor.moveToFirst()) { //if row exists, move to first row
            //initialize the instance variables of task object
            doctor.setDoctorId(cursor.getInt(cursor.getColumnIndex("doctorId")));
            doctor.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            doctor.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            doctor.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
            doctor.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            cursor.close();

        } else {
            doctor = null;
        }
        db.close();
        return doctor;
    }

    public Doctor[] getAllDoctors() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Doctor", null);
        //array to contain all the Doctors stored in the database
        Doctor[] doctors = new Doctor[cursor.getCount()];

        //no need for further processing if there is no returned data
        if (doctors.length > 0) {

            Doctor doctor;

            int counter = 0;

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                doctor = new Doctor(); //create a new Patient object
                doctor.setDoctorId(cursor.getInt(cursor.getColumnIndex("doctorId")));
                doctor.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                doctor.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
                doctor.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
                doctor.setPassword(cursor.getString(cursor.getColumnIndex("password")));

                doctors[counter++] = doctor;
                cursor.moveToNext();
            }
        }

        cursor.close();

        db.close();
        return doctors;
    }

    public Patient getPatientById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Patient where patientId='" + String.valueOf(id) + "'", null);
        Patient patient = new Patient(); //create a new Patient object
        if (cursor.moveToFirst()) { //if row exists, move to first row
            //initialize the instance variables of task object
            patient.setPatientId(cursor.getInt(cursor.getColumnIndex("patientId")));
            patient.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            patient.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            patient.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
            patient.setDoctorId(cursor.getInt(cursor.getColumnIndex("doctorId")));
            patient.setRoom(cursor.getString(cursor.getColumnIndex("room")));

            cursor.close();

        } else {
            patient = null;
        }
        db.close();
        return patient;
    }

    public Patient[] getAllPatients() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Patient", null);
        //array to contain all the Patients stored in the database
        Patient[] patients = new Patient[cursor.getCount()];

        //no need for further processing if there is no returned data
        if (patients.length > 0) {

            Patient patient;

            int counter = 0;

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                patient = new Patient(); //create a new Patient object
                patient.setPatientId(cursor.getInt(cursor.getColumnIndex("patientId")));
                patient.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                patient.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
                patient.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
                patient.setDoctorId(cursor.getInt(cursor.getColumnIndex("doctorId")));
                patient.setRoom(cursor.getString(cursor.getColumnIndex("room")));

                patients[counter++] = patient;
                cursor.moveToNext();
            }
        }

        cursor.close();

        db.close();
        return patients;
    }

    //
    //
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    // values - a ContentValues object that holds row values
    public boolean editRow(String tableName, Integer id, String fieldName, ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        //
        int nr = db.update(tableName, values, fieldName + " = ? ", new String[]{String.valueOf(id)});
        return nr > 0;
    }
}
