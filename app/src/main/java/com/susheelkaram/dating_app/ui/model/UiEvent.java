package com.susheelkaram.dating_app.ui.model;

public class UiEvent<T> {
    public UiEventType uiEventType;
    public T data;

    public UiEvent(UiEventType uiEventType, T data) {
        this.uiEventType = uiEventType;
        this.data = data;
    }
}
