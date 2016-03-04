package com.avellacorp.appstoretest.data.com;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Esta clase es un proxy que funciona como interfaz a los métodos que se van a
 * consumir en el servicio REST.
 *
 * @author Carlosas
 */
public interface IProxy {

    /**
     * Obtiene un string de la lista de aplicaciones
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws JSONException
     */
    public abstract String getApps() throws InterruptedException,
            ExecutionException, JSONException;


}