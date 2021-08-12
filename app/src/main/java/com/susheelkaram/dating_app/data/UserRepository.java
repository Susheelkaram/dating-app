package com.susheelkaram.dating_app.data;

import com.susheelkaram.dating_app.data.api.UserApi;
import com.susheelkaram.dating_app.data.api.model.AuthResponse;
import com.susheelkaram.dating_app.data.api.model.ConfirmOtpData;
import com.susheelkaram.dating_app.data.api.model.GenericResponse;
import com.susheelkaram.dating_app.data.api.model.LoginData;
import com.susheelkaram.dating_app.data.api.model.ProfileResponse;

import io.reactivex.Single;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class UserRepository {
    UserApi userApi;
    static UserRepository INSTANCE;

    private UserRepository() {
        userApi = UserApi.getInstance();
    }

    public static UserRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public Single<GenericResponse> requestOtp(LoginData loginData) {
        return userApi.authService.login(loginData);
    }

    public Single<AuthResponse> verifyOtp(ConfirmOtpData confirmOtpData) {
        return userApi.authService.verifyOtp(confirmOtpData);
    }

    public Single<ProfileResponse> getProfiles(String authToken) {
        return userApi.profileService.getProfiles(authToken);
    }
}
