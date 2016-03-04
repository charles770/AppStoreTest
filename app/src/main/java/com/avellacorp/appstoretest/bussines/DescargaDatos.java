package com.avellacorp.appstoretest.bussines;

import android.app.Activity;
import android.content.Context;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.base.GenericReturn;
import com.avellacorp.appstoretest.data.com.IProxy;
import com.avellacorp.appstoretest.data.com.Proxy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


/**
 * Clase que contiene la lógica de negocio relacionada con la carga inicial de la base de datos
 * de apps.
 *
 * @author Carlosas
 */
public class DescargaDatos {


    private IProxy ProxyRest = null;
    private Context mContext = null;

    public DescargaDatos(Context mContext) {
        this.mContext = mContext;
        ProxyRest = new Proxy(Constant.URL_SERVER_PERFIX);
    }

    /**
     * Esta función permite hacer el llamado a a los metodos del servicio manejando los posibles
     * errores y mensajes devueltos.
     *
     * @param idFunction Id constante de funciones
     * @param params     Strings de parametros ordenados
     * @return
     */
    public GenericReturn PeticionServicio(Integer idFunction, String... params) {

        GenericReturn r = new GenericReturn();
        String res = "";

        try {

            switch (idFunction) {
                case Constant.ID_GET_APPS:
                    res = ProxyRest.getApps();
                    break;

                default:
                    break;
            }

            if (!res.equals("")) {
                JSONObject result = new JSONObject(res);
                r.data = result;
                r.message = "";
                r.codOperation = Constant.OPERATION_SUCCESS;
                r.exception = false;
            } else {

                r.message = mContext.getString(R.string.errorDatos);
                r.codOperation = Constant.OPERATION_FAIL;
                r.exception = false;

            }


        } catch (JSONException JE) {
            r.message = mContext.getString(R.string.errorConversion);
            r.codOperation = Constant.OPERATION_FAIL;
            r.exception = true;
        } catch (Exception EE) {
            r.message = mContext.getString(R.string.errorDesconocido);
            r.codOperation = Constant.OPERATION_FAIL;
            r.exception = true;
        }

        return r;

    }

}
