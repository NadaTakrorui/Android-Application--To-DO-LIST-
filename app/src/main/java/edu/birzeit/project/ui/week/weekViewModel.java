package edu.birzeit.project.ui.week;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class weekViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public weekViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is week fragment");
    }

    public LiveData<String> getText() {
        return mText;

    }
}
