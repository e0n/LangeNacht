package gogo.gadgeto.car.helper;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "android_api";
 
    // Table name
    private static final String TABLE_LOGIN = "login";
 
    // Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CARGROUPID = "cargroupid";
    private static final String KEY_CREATED_AT = "created_at";
     
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE PRIMARY KEY,"
                + KEY_CARGROUPID + " TEXT,"
                + KEY_CREATED_AT + " TIMESTAMP" + ")";

        db.execSQL(CREATE_LOGIN_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existent
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        
        // Recreate tables
        onCreate(db);
    }
 
    /**
     * Storing user details in database
     * */
    public void addUser(String name, String email, String cargroupid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_CARGROUPID, cargroupid); // CarGroupId
        values.put(KEY_CREATED_AT, created_at); // Created At
 
        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection
    }
    
    /**
     * Recreate database
     * Delete login table;
     * */
    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }
   
    /**
     * Updating carGroup details in database
     * */
    public void joinCarGroup(String cargroupid) {
    	
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_CARGROUPID, cargroupid); // ID
 
        // Updating Row
        db.update(TABLE_LOGIN, values, null, null);
        db.close(); // Closing database connection
    }
    
    /**
     * Updating carGroup details in database
     * */
    public void leaveCarGroup() {
    	
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_CARGROUPID, getUserDetails().get(KEY_CARGROUPID)); // ID
 
        // Updating Row
        db.update(TABLE_LOGIN, values, null, null);
        db.close(); // Closing database connection
    }
    
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put(KEY_NAME, cursor.getString(1));
            user.put(KEY_EMAIL, cursor.getString(2));
            user.put(KEY_CARGROUPID, cursor.getString(3));            
            user.put(KEY_CREATED_AT, cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }
 
    /**
     * Get row count of login table
     * @return row count
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
 
        // return row count
        return rowCount;
    }
 

 
}