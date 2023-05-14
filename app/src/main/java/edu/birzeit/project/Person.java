package edu.birzeit.project;
import java.util.ArrayList;

public class Person {
    private String mEmail;
    private String mFirstName;
    private String mLastName;
    private String mPassword;

    public Person() {
    }

    public Person(String mEmail, String mFirstName, String mLastName, String mPassword) {
        this.mEmail = mEmail;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mPassword = mPassword;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setmPassword(String password) {
        mPassword = password;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public String getmPassword() {
        return mPassword;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mEmail='" + mEmail + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", Password='" + mPassword + '\'' +
                '}';
    }
}