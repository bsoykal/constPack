package com.mobile.constpack.network.response;

/**
 * Created by bsoykal on 12/12/2016.
 */

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

@Parcel
@Data
public class BaseResponse {

    boolean result;
    int errorId;
    String errorMsg;

    @ParcelConstructor
    public BaseResponse() {
    }

}
