package com.susheelkaram.dating_app.ui.viewmodel;

import android.media.MediaRouter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.GenericResponse;
import com.susheelkaram.dating_app.data.api.model.LoginData;
import com.susheelkaram.dating_app.ui.model.State;
import com.susheelkaram.dating_app.ui.model.UiEvent;
import com.susheelkaram.dating_app.ui.model.UiEventType;
import com.susheelkaram.dating_app.ui.model.UiState;
import com.susheelkaram.dating_app.util.SingleLiveEvent;

import org.jetbrains.annotations.NotNull;

import java.util.Observer;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class MainViewModel extends ViewModel {
    public String countryCode = "+91";
    public String phone;
    CompositeDisposable disposableBag = new CompositeDisposable();

    MutableLiveData<UiState<GenericResponse>> stateLiveData = new MutableLiveData<UiState<GenericResponse>>();
    UserRepository userRepository;
    SingleLiveEvent<UiEvent<String>> eventsLiveData = new SingleLiveEvent<UiEvent<String>>();

    public LiveData<UiState<GenericResponse>> getState() {
        return (LiveData<UiState<GenericResponse>>) stateLiveData;
    }

    public LiveData<UiEvent<String>> getEventsLiveData() {
        return (LiveData<UiEvent<String>>) eventsLiveData;
    }

    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void requestOtp() {
        stateLiveData.postValue(new UiState(State.Loading, null, null));

        String fullPhone = (countryCode + phone).replace(" ", "");
        Disposable requestOtpDisposable = userRepository.requestOtp(new LoginData(fullPhone))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genericResponse -> {
                    stateLiveData.postValue(new UiState(State.Success, genericResponse, null));
                    if (genericResponse.status == true) {
                        eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, "OTP sent successfully!"));
                        eventsLiveData.setValue(new UiEvent<String>(UiEventType.Navigate, fullPhone));
                    } else {
                        eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, "Enter valid phone"));
                    }
                }, error -> {
                    stateLiveData.postValue(new UiState(State.Failure, null, error.getMessage()));
                    eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, error.getMessage()));

                });
        disposableBag.add(requestOtpDisposable);
    }

    @Override
    protected void onCleared() {
        disposableBag.clear();
        super.onCleared();
    }
}

