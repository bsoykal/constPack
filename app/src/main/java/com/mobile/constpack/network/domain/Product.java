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
public class Product implements Serializable{

    int urunId;
    int katId;
    String urunAdi;
    boolean durum;

    @ParcelConstructor
    public Product(){

    }


}
