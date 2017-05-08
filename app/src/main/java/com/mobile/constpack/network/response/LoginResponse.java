package com.mobile.constpack.network.response;

import com.google.gson.annotations.SerializedName;
import com.mobile.constpack.network.domain.User;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by bsoykal on 08/12/2016.
 */

@Data
@Parcel
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends BaseResponse {

    @SerializedName("data")
    User user;

    @ParcelConstructor
    public LoginResponse(){
    }
}