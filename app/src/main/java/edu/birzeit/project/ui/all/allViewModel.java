package edu.birzeit.project.ui.all;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class allViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();

    public void getALL(String s) {
        mText.setValue(s);
    }

    public LiveData<String> getALLText() {
        return mText;
    }
}
