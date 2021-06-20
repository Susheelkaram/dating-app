package com.susheelkaram.dating_app.data.api;

import com.susheelkaram.dating_app.data.api.model.ProfileResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public interface ProfileService {
    @GET("users/test_profile_list")
    Single<ProfileResponse> getProfiles(@Header("Authorization") String authToken);
}
