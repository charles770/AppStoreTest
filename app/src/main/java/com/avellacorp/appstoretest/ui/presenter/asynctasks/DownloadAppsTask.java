package com.avellacorp.appstoretest.ui.presenter.asynctasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.base.GenericReturn;
import com.avellacorp.appstoretest.bussines.ActualizacionStore;
import com.avellacorp.appstoretest.bussines.DescargaDatos;
import com.avellacorp.appstoretest.ui.activities.SplashActivity;
import com.avellacorp.appstoretest.ui.activities.StoreActivity;
import com.avellacorp.appstoretest.ui.presenter.Navigator;


/**
 * Clase encargada de realizar alguna operación en una tarea asíncrona. Se encarga de descargar la
 * la data
 *
 * @author Carlosas
 */
public class DownloadAppsTask extends AsyncTask<String, Integer, GenericReturn> {


    private Context context;

    public DownloadAppsTask(Context context) {
        this.context = context;

    }


    @Override
    protected GenericReturn doInBackground(String... params) {

        ActualizacionStore act = new ActualizacionStore(context);

        return act.ActualizarStore();
    }


    @Override
    protected void onPostExecute(GenericReturn ret) {
        super.onPostExecute(ret);

        //valida el mensaje de retorno y muestra


        if (ret.codOperation == Constant.OPERATION_FAIL) {
            Toast.makeText(context, ret.message, Toast.LENGTH_SHORT).show();

        }
        // lanza actividad principal

        Navigator nav = new Navigator(context);
        nav.openStoreActivity(null);

        ((SplashActivity) context).finish();


    }

}
