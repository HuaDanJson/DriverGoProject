package com.driver.go.control;

import android.content.Intent;

import com.driver.go.base.DriverGoApplication;

/**
 * Created by Administrator on 2016/12/1.
 */
public class IntentManager {

    public static void startActivity(Class clazz){
        Intent i = new Intent(DriverGoApplication.sContext,clazz);
        DriverGoApplication.sContext.startActivity(i);
    }
}
