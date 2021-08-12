package com.susheelkaram.dating_app.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.ProfileResponse;
import com.susheelkaram.dating_app.ui.model.State;
import com.susheelkaram.dating_app.ui.model.UiEvent;
import com.susheelkaram.dating_app.ui.model.UiEventType;
import com.susheelkaram.dating_app.ui.model.UiState;
import com.susheelkaram.dating_app.util.SingleLiveEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class NotesViewModel extends ViewModel{
    public String token;

    MutableLiveData<UiState<ProfileResponse>> stateLiveData = new MutableLiveData<UiState<ProfileResponse>>();
    UserRepository userRepository;
    SingleLiveEvent<UiEvent<String>> eventsLiveData =new SingleLiveEvent<UiEvent<String>>();

    CompositeDisposable disposableBag = new CompositeDisposable();

    public NotesViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<UiState<ProfileResponse>> getState() {
        return (LiveData<UiState<ProfileResponse>>) stateLiveData;
    }

    public LiveData<UiEvent<String>> getEventsLiveData() {
        return (LiveData<UiEvent<String>>) eventsLiveData;
    }

    public void loadProfiles() {
        Disposable getProfilesDisposable = userRepository.getProfiles(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profileResponse -> {
                    stateLiveData.postValue(new UiState(State.Success, profileResponse, null));
                }, e -> {
                    stateLiveData.postValue(new UiState(State.Failure, null, e.getMessage()));
                    eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, e.getMessage()));

                });
        disposableBag.add(getProfilesDisposable);
    }

    @Override
    protected void onCleared() {
        disposableBag.clear();
        super.onCleared();
    }
}

