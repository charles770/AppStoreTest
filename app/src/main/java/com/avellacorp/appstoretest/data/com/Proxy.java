package com.avellacorp.appstoretest.data.com;


import com.avellacorp.appstoretest.util.RestServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;


/**
 * Clase responsable de obtener la comunicación con el servicio REST
 *
 * @author carlosas
 */
public class Proxy implements IProxy {

    private String server;

    /**
     * Esta Constante se utiliza para los metodos donde no es necesario la IP
     * real del dispositivo
     */

    public Proxy(String URIServices) {
        this.server = URIServices;

    }


    @Override
    public String getApps() throws InterruptedException, ExecutionException, JSONException {


        RestServices rest = new RestServices();
        String result = rest.makeHttpRequestGetEntity(server);
        return result;

    }



}
