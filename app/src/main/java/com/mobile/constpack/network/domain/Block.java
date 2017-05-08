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
public class Block implements Serializable{

    int blokId;
    int projeId;
    String blokAdi;

    @ParcelConstructor
    public Block(){

    }


}
