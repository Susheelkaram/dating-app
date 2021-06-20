package com.susheelkaram.dating_app.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.susheelkaram.dating_app.data.UserRepository;

import org.jetbrains.annotations.NotNull;

public class VerifyOtpViewModelFactory implements ViewModelProvider.Factory {
    UserRepository userRepository;

    public VerifyOtpViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VerifyOtpViewModel.class)) {
            return (T) new VerifyOtpViewModel(userRepository);
        }
        throw new IllegalArgumentException("Unknown Viewmodel class ${modelClass.simpleName}");
    }
}
