package edu.birzeit.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper{

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Person(Email text primary key, FirstName text, LastName text, Password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPerson(Person person) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", person.getmEmail());
        contentValues.put("FirstName", person.getmFirstName());
        contentValues.put("LastName", person.getmLastName());
        contentValues.put("Password", person.getmPassword());
        sqLiteDatabase.insert("Person", null, contentValues);
    }

    public Cursor searchPerson(String Email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Person WHERE Email='" +Email+"'", null);
    }
    public void UpdatePerson(Person person){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.rawQuery("UPDATE Person set FirstName = '" + person.getmFirstName()+"', LastName = '"
                + person.getmLastName()+ "', Password = '" + person.getmPassword()+"' WHERE Email = '" + person.getmEmail()+"'", null);
    }
}
