package com.mobile.constpack.network.request;

import lombok.Data;

/**
 * Created by bsoykal on 08/12/2016.
 */


@Data
public class UpdateTaskRequest {
    int id;
    boolean tamamlandi;

    public UpdateTaskRequest(int id, boolean tamamlandi){
        setId(id);
        setTamamlandi(tamamlandi);
    }


}
