package com.susheelkaram.dating_app.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.susheelkaram.dating_app.data.UserRepository;

import org.jetbrains.annotations.NotNull;

public class NotesViewModelFactory implements ViewModelProvider.Factory {
    UserRepository userRepository;

    public NotesViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NotesViewModel.class)) {
            return (T) new NotesViewModel(userRepository);
        }
        throw new IllegalArgumentException("Unknown Viewmodel class ${modelClass.simpleName}");
    }
}
