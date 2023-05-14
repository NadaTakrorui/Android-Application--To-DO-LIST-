package edu.birzeit.project.ui;

import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DataBaseToDo extends android.database.sqlite.SQLiteOpenHelper{

    private static final String TOAST_TEXT = "Congratulations";

    public DataBaseToDo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table ToDoList(ToDoID INTEGER primary key AUTOINCREMENT, Email text, ToDo text, Date DATE, Complete INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertToDo(ToDo todo) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ToDo", todo.getToDo());
        contentValues.put("Date", todo.getDate().toString());
        contentValues.put("Email", todo.getEmail());
        contentValues.put("Complete", todo.getComplete());
        sqLiteDatabase.insert("ToDoList", null, contentValues);
    }

    public List<ToDo> getAllToDo(String email) {
        List<ToDo> TasksList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ToDoList where Email = '" + email + "' ORDER BY Date ASC", null);
        while(cursor.moveToNext()){
            int ID = Integer.parseInt(cursor.getString(0));
            String Email = cursor.getString(1);
            String ToDoName = cursor.getString(2);
            String ToDoDate = cursor.getString(3);
            int Comp = Integer.parseInt(cursor.getString(4));
            ToDo task = new ToDo(ToDoName, Date.valueOf(ToDoDate), Email, Comp);
            task.setToDoId(ID);
            TasksList.add(task);
        }
        return TasksList;
    }

    public List<ToDo> getToDo(String email, Date dates){
        List<ToDo> TasksList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ToDoList where Email = '"+email+"' and Date = '" + dates + "'",null);
        while(cursor.moveToNext()){
            int ID = Integer.parseInt(cursor.getString(0));
            String Email = cursor.getString(1);
            String ToDoName = cursor.getString(2);
            String ToDoDate = cursor.getString(3);
            int Comp = Integer.parseInt(cursor.getString(4));
            ToDo task = new ToDo(ToDoName, Date.valueOf(ToDoDate), Email, Comp);
            task.setToDoId(ID);
            TasksList.add(task);
        }
        return TasksList;
    }
    public List<ToDo> getWeekToDo(String email, Date dates, Date afterWeek){
        List<ToDo> TasksList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ToDoList where Email = '"+email+"' and Date between '" + dates + "' and '" + afterWeek + "' ORDER BY Date ASC",null);
        while(cursor.moveToNext()){
            int ID = Integer.parseInt(cursor.getString(0));
            String Email = cursor.getString(1);
            String ToDoName = cursor.getString(2);
            String ToDoDate = cursor.getString(3);
            int Comp = Integer.parseInt(cursor.getString(4));
            ToDo task = new ToDo(ToDoName, Date.valueOf(ToDoDate), Email, Comp);
            task.setToDoId(ID);
            TasksList.add(task);
        }
        return TasksList;
    }
    public void UpdateComplete(int id, int Comp){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put("Complete",Comp);
        sqLiteDatabase.update("ToDoList", data, "ToDoID =" + id, null);
    }
    public void deleteTask(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("ToDoList", "ToDoID =" + id, null);
    }

    public void changeTextOfTask(int id, String text) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put("ToDo",text);
        sqLiteDatabase.update("ToDoList", data, "ToDoID =" + id, null);
    }
}
