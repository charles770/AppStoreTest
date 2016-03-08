package com.avellacorp.appstoretest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.bussines.ActualizacionStore;
import com.avellacorp.appstoretest.bussines.aplicaciones.AplicacionBussines;
import com.avellacorp.appstoretest.bussines.categorias.CategoriaBussines;
import com.avellacorp.appstoretest.ui.presenter.Navigator;
import com.avellacorp.appstoretest.ui.presenter.adapter.AplicacionesAdapter;

public class StoreActivity extends MasterActivity {

    private RecyclerView mListaApps;
    private RecyclerView.LayoutManager mLayoutManagerListaApps;

    private FloatingActionButton btnCategorias;
    private FloatingActionButton btnBack;
    private AplicacionBussines apps;
    private CategoriaBussines categ;
    private ActualizacionStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        apps = new AplicacionBussines(this);
        categ = new CategoriaBussines(this);
        store = new ActualizacionStore(this);
        CargarControles();

    }

    private void CargarControles() {


        btnCategorias = (FloatingActionButton) findViewById(R.id.btnCategorias);
        btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        mListaApps = (RecyclerView) findViewById(R.id.rvListaApps);

        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigator nav = new Navigator(StoreActivity.this);
                nav.openCategoriaActivity();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


        //revisa el intent

        Intent intent = getIntent();
        // specify an adapter (see also next example)
        final AplicacionesAdapter mAdapter;

        if (intent.getStringExtra(Constant.ID_CATEGORIA_EX) == null) {
            btnCategorias.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.GONE);

            mAdapter = new AplicacionesAdapter(this, apps.ConsultarListaAplicaciones(), categ.ConsultarListaCategorias(), store.ConsultarStore());

        } else {

            btnCategorias.setVisibility(View.GONE);
            btnBack.setVisibility(View.VISIBLE);

            mAdapter = new AplicacionesAdapter(this, apps.ConsultarListaAplicacionesByCategoria(intent.getStringExtra(Constant.ID_CATEGORIA_EX)), categ.ConsultarCategoriaDetalle(intent.getStringExtra(Constant.ID_CATEGORIA_EX)));
        }

        //si no cambia el layout
        mListaApps.setHasFixedSize(true);

        // para tsm es linear
        if (smartphone) {
            mLayoutManagerListaApps = new LinearLayoutManager(this);
        } else {
            mLayoutManagerListaApps = new GridLayoutManager(this, 4);
            ((GridLayoutManager) mLayoutManagerListaApps).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return mAdapter.isHeader(position) ? ((GridLayoutManager) mLayoutManagerListaApps).getSpanCount() : 1;
                }
            });
        }

        mListaApps.setLayoutManager(mLayoutManagerListaApps);

        mListaApps.setAdapter(mAdapter);


    }


    public static Intent getLaunchIntent(Context context, String idCategoria) {

        Intent i = new Intent(new Intent(context, StoreActivity.class));
        i.putExtra(Constant.ID_CATEGORIA_EX, idCategoria);


        return i;

    }
}
