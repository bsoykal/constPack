package com.mobile.constpack.base;

import android.app.Application;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by buraksoykal on 04/02/2017.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ConstPack extends Application {
    static ConstPack instance;
    int userId;


    @Override
    public void onCreate() {
        super.onCreate();
        ConstPack.get();
    }

    public static ConstPack get(){
        if(instance == null){
            instance = new ConstPack();
        }
        return instance;
    }
}
