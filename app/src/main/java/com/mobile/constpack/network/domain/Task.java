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
public class Task implements Serializable{

    int id;
    int isId;
    String isAdi;
    String sorumlu;
    String ozellik;
    int miktar;
    int mahalId;
    boolean tamamlandi;

    @ParcelConstructor
    public Task(){

    }


}
