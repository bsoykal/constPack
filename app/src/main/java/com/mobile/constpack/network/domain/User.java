package com.mobile.constpack.network.domain;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

/**
 * Created by buraksoykal on 05/02/2017.
 */

@Data
@Parcel
public class User {

    int kullaniciId;
    String kullaniciAdi;
    String unvan;
    int rolId;
    String eposta;
    boolean durum;

    @ParcelConstructor
    public User(){

    }

}
