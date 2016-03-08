package com.avellacorp.appstoretest.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.entities.Aplicacion;
import com.avellacorp.appstoretest.entities.Categoria;
import com.avellacorp.appstoretest.ui.activities.AplicacionDialog;
import com.avellacorp.appstoretest.ui.activities.CategoriasActivity;
import com.avellacorp.appstoretest.ui.activities.SplashActivity;
import com.avellacorp.appstoretest.ui.activities.StoreActivity;


/**
 * Clase creada para navegar de una actividad a otra, adicionando transiciones y estilos
 * necesarios
 *
 * @author carlosas
 */
public class Navigator {

    private SplashActivity splashActivity;
    private StoreActivity storeActivity;

    private final Context activityContext;

    public Navigator(Context activityContext) {
        this.activityContext = activityContext;
    }

    private FragmentManager getFragmentManager() {
        return ((FragmentActivity) activityContext).getSupportFragmentManager();
    }

    private boolean isFragmentAvailable(Fragment fragment) {
        return fragment != null && fragment.isAdded();
    }

    public void openStoreActivity(final String idCategoria) {
        Intent intent = StoreActivity.getLaunchIntent(activityContext, idCategoria);
        startActivity(intent);
        ((Activity)activityContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openAppActivity(final Aplicacion aplicacion, final Categoria cat) {
        Intent intent = AplicacionDialog.getLaunchIntent(activityContext, aplicacion, cat);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        ((Activity)activityContext).overridePendingTransition(R.anim.zoom_in, R.anim.close_next);

    }

    public void openCategoriaActivity() {
        Intent intent = CategoriasActivity.getLaunchIntent(activityContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        ((Activity)activityContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void startActivity(Intent intent) {
        activityContext.startActivity(intent);
    }
}
