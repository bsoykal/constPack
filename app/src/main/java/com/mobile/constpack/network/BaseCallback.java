package com.mobile.constpack.network;

import android.content.Context;

import com.mobile.constpack.helpers.Constants;
import com.mobile.constpack.network.response.BaseResponse;

import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class BaseCallback<T extends BaseResponse> implements Callback<T> {
    Context context;

    public BaseCallback(Context context) {
        this.context = context;
    }




    public abstract void onSuccess(T response);
    public abstract void onFailure(int errorId,String error);

    public void onResponse(Call<T> call, Response<T> response) {
        T baseResponse = response == null ? null : response.body();

        if (baseResponse == null ){
            onFailure(Constants.INTERNAL_SERVER_ERROR,"SISTEM HATASI");
        }else if(!response.isSuccessful() || !response.body().isResult()) {
            onFailure(response.body().getErrorId(),response.body().getErrorMsg());
        } else
            onSuccess(baseResponse);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        if (t instanceof ConnectException)
            onFailure(Constants.NETWORK_ERROR,"BAĞLANTI HATASI");
        else if (t instanceof TimeoutException)
            onFailure(Constants.NETWORK_ERROR,"İSTEK ZAMAN AŞIMINA UĞRADI");
        else
            onFailure(Constants.INTERNAL_SERVER_ERROR,"GENEL SUNUCU HATASI");
    }
}
