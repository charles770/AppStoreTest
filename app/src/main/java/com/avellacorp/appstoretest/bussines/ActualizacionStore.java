package com.avellacorp.appstoretest.bussines;

import android.content.Context;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.base.GenericReturn;
import com.avellacorp.appstoretest.bussines.aplicaciones.AplicacionBussines;
import com.avellacorp.appstoretest.data.db.DBAdapter;
import com.avellacorp.appstoretest.data.db.DBManager;
import com.avellacorp.appstoretest.entities.Configuracion;
import com.avellacorp.appstoretest.entities.Store;
import com.avellacorp.appstoretest.util.Util;


/**
 * Clase que contiene la lógica de negocio relacionada con la carga inicial de la base de datos
 * de la tienda.
 *
 * @author Carlosas
 */
public class ActualizacionStore {

    private Context context = null;
    private Util util;
    private DBAdapter dbAdapter = null;


    public ActualizacionStore(Context context) {

        this.context = context;
        util = new Util(context);
        dbAdapter = DBManager.getInstance(context);
    }


    /**
     * Método encargado de descargar y actualizar la base de datos
     *
     * @return Respuesta
     */

    public GenericReturn ActualizarStore() {
        GenericReturn ret = new GenericReturn();
        DescargaDatos descarga = new DescargaDatos(context);

        //valida que la base de datos exista una tienda

        //si no existe la descarga. Si no hay conexión a internet muestra mensaje al usuario

        //si existe la actualiza. Si no hay conexion muestra mensaje de no actualizado

        Store tienda = ConsultarStore();

        if (util.isNetworkAvailable()) {

            //Si ocurrió un error en la descarga se muestra un mensaje de error

            ret = descarga.PeticionServicio(Constant.ID_GET_APPS);

            if (!ret.exception) {

                AplicacionBussines actualiza = new AplicacionBussines(context);
                ret = actualiza.ActualizarStore(ret.data, tienda.getFecha());

            }


        } else {

            if (tienda.getNombre() == null) {

                //Si no hay internet y es la carga inicial, se muestra un mensaje al usuario que se debe conectar
                ret.exception = false;
                ret.codOperation = Constant.OPERATION_FAIL;
                ret.message = context.getString(R.string.errorConexionInternet);

            } else {
                //No actualizado puede entrar a la tienda
                ret.exception = false;
                ret.codOperation = Constant.OPERATION_SUCCESS;
                ret.message = context.getString(R.string.errorActualizacion);

            }

        }


        return ret;
    }


    public Store ConsultarStore() {

        //Obtiene una tienda

        Store st = dbAdapter.fetchStore();

        return st;
    }


    public Configuracion ConsultarConfiguracion(String parametro) {

        //Obtiene una parametro de configuracion

        Configuracion conf = dbAdapter.fetchConfiguracion(parametro);

        return conf;
    }


}
