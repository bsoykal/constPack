package com.mobile.constpack.network.request;

import lombok.Data;

/**
 * Created by bsoykal on 08/12/2016.
 */


@Data
public class LoginRequest {
    String strEposta;
    String strPassword;

    public LoginRequest(String strEposta, String strPassword){
        setStrEposta(strEposta);
        setStrPassword(strPassword);
    }


}
