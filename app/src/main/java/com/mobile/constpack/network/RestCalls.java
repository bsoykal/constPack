package com.mobile.constpack.network;

import com.mobile.constpack.network.request.LoginRequest;
import com.mobile.constpack.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



/**
 * Created by bsoykal on 26/08/16.
 */
public interface RestCalls {

    @POST("Login")
    Call<LoginResponse> requestLogin(@Body LoginRequest loginResponse);


}
