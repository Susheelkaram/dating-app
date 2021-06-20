package com.susheelkaram.dating_app.data.api;

import com.susheelkaram.dating_app.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class UserApi {
    static UserApi INSTANCE;

    private Retrofit retrofit;
    public ProfileService profileService;
    public AuthService authService;

    private UserApi() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();

        authService = retrofit.create(AuthService.class);
        profileService = retrofit.create(ProfileService.class);
    }

    public static UserApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserApi();
        }
        return INSTANCE;
    }

    private OkHttpClient getClient() {
        return new OkHttpClient().newBuilder().
                addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Request newRequest = request.newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Cookie", "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }
}
