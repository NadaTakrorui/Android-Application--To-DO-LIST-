package edu.birzeit.project.ui.today;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class todayViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public todayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is today fragment");
    }

    public LiveData<String> getText() {
        return mText;

    }
}
