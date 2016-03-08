package com.avellacorp.appstoretest.ui.activities;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.base.ManagerApplication;
import com.avellacorp.appstoretest.util.Util;


/**
 * Esta clase extiende de la clase Activity, FragmentActivity o
 * ActionBarActivity. Se usa como actividad padre a todas las actividades de la
 * aplicación. Aquí se establecen los parámetros y comportamientos similares a
 * todas las actividades como por ejemplo la configuración de rotación,
 * detección del tamaño de pantalla, si se trata de un dispositivo tipo tablet o
 * smartphone y acciones comunes en alguno de los métodos del ciclo de vida de
 * la actividad o funciones usadas en presenter (como creación de
 * diálogos).
 *
 * @author Carlosas
 */
public class MasterActivity extends AppCompatActivity {

    public ProgressDialog pDialog;
    public Boolean smartphone;
    protected ManagerApplication appContext;
    protected Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        try {

            Integer tamano = getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK;

            Integer ancho = 0;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
                // only for gingerbread and newer versions
                ancho = getResources().getConfiguration().smallestScreenWidthDp;
            }
            if (tamano == 4 || ancho >= 600)// Tablets
            {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                smartphone = false;
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                smartphone = true;
            }
            appContext = (ManagerApplication) this
                    .getApplication();
            util = new Util(MasterActivity.this);
            // Verificar la Conexión a Internet

        } catch (Exception e) {
            if (Constant.DEBUG)
                showAlert("MasterActivity.onCreate" + e.getMessage()
                        + e.getStackTrace().toString(), false);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // do nothing
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void showAlert(String strMessage,
                             android.content.DialogInterface.OnClickListener btnOKListener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("");
        alert.setMessage(strMessage);
        alert.setPositiveButton(getString(R.string.aceptar), btnOKListener);
        alert.show();
    }

    public void showAlert(String strMessage, final boolean finalizar) {
        showAlert(strMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (finalizar)
                    finish();
            }
        });
    }

    public void showProgressDialog() {
        if (pDialog != null && pDialog.isShowing())
            return;
        pDialog = new ProgressDialog(MasterActivity.this);
        pDialog.setMessage(getString(R.string.actualizando));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }



}
