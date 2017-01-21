package com.mobile.constpack.network.response;

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


    int intKullaniciId;
    String strKullaniciAdi;
    String strUnvan;
    int intRol;
    String strEposta;
    String dtSonGiriş;
    boolean blOturum;
    boolean blDurum;
    String strPassword;


    @ParcelConstructor
    public LoginResponse(){
    }
}

