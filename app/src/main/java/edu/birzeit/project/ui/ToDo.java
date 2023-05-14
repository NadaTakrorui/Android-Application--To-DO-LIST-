package edu.birzeit.project.ui;
import java.sql.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDo {
    private int ToDoId;
    private String ToDo;
    private String Email;
    private Date date;
    private int complete;

    public ToDo() {
    }

    public ToDo(String toDo, Date date, String Email, int complete) {
        this.ToDo = toDo;
        this.date = date;
        this.Email = Email;
        this.complete = complete;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getToDoId() {
        return ToDoId;
    }

    public void setToDoId(int toDoId) {
        ToDoId = toDoId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getToDo() {
        return ToDo;
    }

    public void setToDo(String toDo) {
        ToDo = toDo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
