package com.androidarchitecturecomponentsample.application;

import android.app.Application;

import com.androidarchitecturecomponentsample.interfaces.ApiClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static Retrofit sRetrofit;
    public static final int NETWORK_TIMEOUT = 60;
    public static final String DEFAULT_METADATA_TIME = "3661000";
    public static final String DEFAULT_LAST_METADATA = "0";
    private String BASE_URL = "";

    @Override
    public void onCreate() {
        super.onCreate();

        setRetrofitApiClient();
    }

    private void setRetrofitApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiClient getClient() {
        return sRetrofit.create(ApiClient.class);
    }
}
