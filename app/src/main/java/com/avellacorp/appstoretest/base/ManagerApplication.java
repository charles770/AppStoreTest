package com.avellacorp.appstoretest.base;

import android.app.Application;

/**
 * Esta clase permite guardar variables de aplicación que luego son usadas en
 * varias partes de la aplicación.
 *
 * @author Carlos Avella
 */
public class ManagerApplication extends Application {

    private static ManagerApplication singleton;

    public ManagerApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }



}
