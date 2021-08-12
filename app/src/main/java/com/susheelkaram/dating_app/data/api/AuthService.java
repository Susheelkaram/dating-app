package com.susheelkaram.dating_app.data.api;

import com.susheelkaram.dating_app.data.api.model.AuthResponse;
import com.susheelkaram.dating_app.data.api.model.ConfirmOtpData;
import com.susheelkaram.dating_app.data.api.model.GenericResponse;
import com.susheelkaram.dating_app.data.api.model.LoginData;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public interface AuthService {
    @POST("users/phone_number_login")
    Single<GenericResponse> login(@Body LoginData loginData);

    @POST("users/verify_otp")
    Single<AuthResponse> verifyOtp(@Body ConfirmOtpData confirmOtpData);
}
