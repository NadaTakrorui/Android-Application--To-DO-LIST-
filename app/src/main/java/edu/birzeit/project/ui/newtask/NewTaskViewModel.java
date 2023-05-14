package edu.birzeit.project.ui.newtask;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.birzeit.project.ui.ToDo;

public class NewTaskViewModel extends ViewModel {

    private final MutableLiveData<ToDo> NewToDo = new MutableLiveData<ToDo>();

    public void newTask(ToDo newTodo) {
        NewToDo.setValue(newTodo);
    }

    public LiveData<ToDo> getNewTodo() {
        return NewToDo;
    }
}

