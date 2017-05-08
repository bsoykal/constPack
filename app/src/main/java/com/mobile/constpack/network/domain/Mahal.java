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
public class Mahal implements Serializable{

   int mahalId;
   int urunId;
   int mahalTipId;
   String mahalAdi;
   boolean durum;

    @ParcelConstructor
    public Mahal(){

    }


}
