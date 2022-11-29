package com.ridm.eduRIDM.screen.addextraclass;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClass;

public class AddExtraclassViewModel extends ViewModel {

    ExtraClass extraClass = new ExtraClass();
    private final MutableLiveData<Boolean> navigateToProfile = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToProfile() {
        return navigateToProfile;
    }

    public void onSubmit() {
        insertExtraClass();

        navigateToProfile.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToProfile() {
        navigateToProfile.setValue(Boolean.FALSE);
    }

    public void insertExtraClass() {
        MainActivity.roomRepository.insertExtraClass(extraClass);
    }
}
