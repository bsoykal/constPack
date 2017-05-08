package com.mobile.constpack.network.request;

import lombok.Data;

/**
 * Created by bsoykal on 08/12/2016.
 */


@Data
public class LoginRequest {
    String eposta;
    String password;

    public LoginRequest(String strEposta, String strPassword){
        setEposta(strEposta);
        setPassword(strPassword);
    }


}
