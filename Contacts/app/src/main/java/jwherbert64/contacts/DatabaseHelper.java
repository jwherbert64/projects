package jwherbert64.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 4/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "contactManager";
    private static String TABLE_CONTACTS = "contacts";
    private static String KEY_ID = "id";
    private static String KEY_NAME = "name";
    private static String KEY_PHONE = "phone";
    private static String KEY_EMAIL = "email";
    private static String KEY_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                + KEY_PHONE + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_ADDRESS
                + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_ADDRESS, contact.getAddress());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    Contact getContact(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String strName = "";
        String strPhone = "";
        String strEmail = "";
        String strAddress = "";

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + "='" + name + "'", null);
            if(cursor.moveToFirst()) {
                do {
                    strName = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                    strPhone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                    strEmail = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
                    strAddress = cursor.getString(cursor.getColumnIndex(KEY_ADDRESS));
                } while(cursor.moveToNext());
            }
            Contact contact = new Contact(strName, strPhone, strEmail, strAddress);
            return contact;
        }finally {
            cursor.close();
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setAddress(cursor.getString(4));

                contactList.add(contact);
            } while(cursor.moveToNext());
        }

        return contactList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_ADDRESS, contact.getAddress());

        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[] {String.valueOf(contact.getId())});
    }

    public boolean deleteContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CONTACTS, KEY_NAME + "='" + name + "'", null) > 0;
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
        db.close();
    }


    public int getContactCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
