package com.susheelkaram.dating_app.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.AuthResponse;
import com.susheelkaram.dating_app.data.api.model.ConfirmOtpData;
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
public class VerifyOtpViewModel extends ViewModel {
    public String otp;
    public String phone;

    MutableLiveData<UiState<AuthResponse>> stateLiveData = new MutableLiveData<UiState<AuthResponse>>();
    UserRepository userRepository;
    SingleLiveEvent<UiEvent<String>> eventsLiveData = new SingleLiveEvent<UiEvent<String>>();

    CompositeDisposable disposableBag = new CompositeDisposable();


    public VerifyOtpViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<UiState<AuthResponse>> getState() {
        return (LiveData<UiState<AuthResponse>>) stateLiveData;
    }

    public LiveData<UiEvent<String>> getEventsLiveData() {
        return (LiveData<UiEvent<String>>) eventsLiveData;
    }

    public void verifyOtp() {
        if (otp != null && otp.length() == 4) {
            stateLiveData.postValue(new UiState(State.Loading, null, null));

            Disposable verifyOtpDisposable = userRepository.verifyOtp(new ConfirmOtpData(phone, otp))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(authResponse -> {
                        stateLiveData.postValue(new UiState(State.Success, authResponse, null));
                        if (authResponse.token != null) {
                            eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, "Logged in successfully!"));
                            eventsLiveData.setValue(new UiEvent<String>(UiEventType.Navigate, authResponse.token));
                        } else {
                            eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, "Invalid OTP"));
                        }
                    }, e -> {
                        stateLiveData.postValue(new UiState(State.Failure, null, e.getMessage()));
                        eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, e.getMessage()));
                    });
            disposableBag.add(verifyOtpDisposable);
        } else {
            eventsLiveData.setValue(new UiEvent<String>(UiEventType.Toast, "Enter valid OTP"));
        }
    }

    @Override
    protected void onCleared() {
        disposableBag.clear();
        super.onCleared();
    }
}

