package com.mobile.constpack.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.constpack.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bsoykal on 08/12/2016.
 */

public class RestManager {
    private static RestCalls instance;

    public static RestCalls getInstance(){
        if(instance==null){
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new OkHttpClient().newBuilder()
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS).build())
                    .build();

            instance = retrofit.create(RestCalls.class);
        }

        return instance;
    }

}
