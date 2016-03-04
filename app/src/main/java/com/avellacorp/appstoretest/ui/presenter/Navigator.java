package com.avellacorp.appstoretest.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

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

    public void openStoreActivity(final String tvShowId) {
        Intent intent = StoreActivity.getLaunchIntent(activityContext, tvShowId);
        startActivity(intent);
    }

    private void startActivity(Intent intent) {
        activityContext.startActivity(intent);
    }
}
