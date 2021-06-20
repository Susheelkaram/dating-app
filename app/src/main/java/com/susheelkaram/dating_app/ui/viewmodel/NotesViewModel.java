package com.susheelkaram.dating_app.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.AuthResponse;
import com.susheelkaram.dating_app.data.api.model.ConfirmOtpData;
import com.susheelkaram.dating_app.data.api.model.ProfileResponse;
import com.susheelkaram.dating_app.ui.model.State;
import com.susheelkaram.dating_app.ui.model.UiEvent;
import com.susheelkaram.dating_app.ui.model.UiEventType;
import com.susheelkaram.dating_app.ui.model.UiState;
import com.susheelkaram.dating_app.util.SingleLiveEvent;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
        userRepository.getProfiles(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProfileResponse>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NotNull ProfileResponse profileResponse) {
                        stateLiveData.postValue(new UiState(State.Success, profileResponse, null));
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        stateLiveData.postValue(new UiState(State.Failure, null, e.getMessage()));
                        eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, e.getMessage()));
                    }
                });
    }
}

