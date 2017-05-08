package com.mobile.constpack.network.response;

import com.google.gson.annotations.SerializedName;
import com.mobile.constpack.network.domain.Floor;

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
public class FloorsResponse extends BaseResponse  {

    @SerializedName("data")
    Floor[] floors;

    @ParcelConstructor
    public FloorsResponse(){
    }
}

