package com.susheelkaram.dating_app.ui.model;

import androidx.annotation.Nullable;

public class UiState<T> {
    State state;
    T data;
    String error;

    public State getState() {
        return state;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public UiState(State state, @Nullable T data, @Nullable String error) {
        this.state = state;
        this.data = data;
        this.error = error;
    }
}

//class Loading extends UiState {
//
//}
//
//class Success<T> extends UiState {
//    T data;
//    Success(T data) {
//        this.data = data;
//    }
//}
//
//class Failure extends UiState {
//    Exception exception;
//    Failure(Exception exception) {
//        this.exception = exception;
//    }
//}
