package com.example.armfluke.pleasesavetang; /**
 * Created by Armfluke on 5/21/2017.
 */

import java.util.ArrayList;
import java.util.List;

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
    private static final String DATABASE_NAME = "Information.db";

    // Contacts table name
    private static final String TABLE_CONTACTS = "ID";

    // Contacts Table Columns names
    private static final String KEY_Username = "Username";
    private static final String KEY_Password = "Password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_Username + " TEXT PRIMARY KEY," + KEY_Password + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+TABLE_CONTACTS+" VALUES ('"+contact.getUsername()+"','"+contact.getPassword()+"')");
        /*ContentValues values = new ContentValues();
        values.put(KEY_Username, contact.getUsername());
        values.put(KEY_Password, contact.getPassword());*/

        // Inserting Row
        //db.insert(TABLE_CONTACTS, null, values);
        //db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Contact contact;
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CONTACTS+" WHERE "+KEY_Username+"='"+username+"'",null);//query(TABLE_CONTACTS, new String[] { KEY_Username, KEY_Password }, KEY_Username + "=?", new String[] { username }, null, null, null);
        if (cursor!=null && cursor.getCount()>0) {
            cursor.moveToFirst();
            contact = new Contact(cursor.getString(0), cursor.getString(1));
        }else{
            contact = new Contact("","");
        }
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setUsername(cursor.getString(0));
                contact.setPassword(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Contact contact,String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        //ContentValues values = new ContentValues();
        //values.put(KEY_Username, contact.getUsername());
        //values.put(KEY_Password, contact.getPassword());

        // updating row
        db.execSQL("UPDATE "+TABLE_CONTACTS+" SET +"+KEY_Username+"='"+username+"' AND "+KEY_Password+"='"+contact.getPassword()+"' WHERE Username='"+contact.getUsername()+"'");//db.update(TABLE_CONTACTS, values, KEY_Username + " = ?", new String[] { contact.getUsername() });
        return 0;
    }

    // Deleting single contact
    public void deleteContact(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_CONTACTS+" WHERE "+KEY_Username+"='"+username+"'");
        //db.delete(TABLE_CONTACTS, KEY_Username + " = ?", new String[] { username });
        //db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
