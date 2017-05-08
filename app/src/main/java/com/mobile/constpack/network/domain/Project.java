package com.mobile.constpack.network.domain;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by buraksoykal on 04/02/2017.
 */

@Data
@Parcel
public class Project implements Serializable{

    int projeId;
    String projeAdi;
    boolean result;
    int errorId;
    String errorMsg;

    @ParcelConstructor
    public Project(){

    }


}
