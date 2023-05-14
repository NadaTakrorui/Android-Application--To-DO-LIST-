package edu.birzeit.project;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<String> CheckPerson = new MutableLiveData<String>();
    private final MutableLiveData<Person> AddPerson = new MutableLiveData<Person>();

    public void setAddPerson(Person Item){
        AddPerson.setValue(Item);

    }
    public LiveData<Person> getSelectedPerson(){

        return AddPerson;
    }

    public void setCheckPerson(String Item){
        CheckPerson.setValue(Item);

    }
    public LiveData<String> getSelectedCheckPerson(){

        return CheckPerson;
    }
}
