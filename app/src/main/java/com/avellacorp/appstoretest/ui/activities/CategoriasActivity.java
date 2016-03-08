package com.avellacorp.appstoretest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.bussines.categorias.CategoriaBussines;
import com.avellacorp.appstoretest.ui.presenter.adapter.CategoriasAdapter;

public class CategoriasActivity extends MasterActivity {

    private RecyclerView mListaCats;
    private GridLayoutManager mLayoutManagerListaCats;


    private FloatingActionButton btnBack;

    private CategoriaBussines categ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        categ = new CategoriaBussines(this);

        CargarControles();

    }

    private void CargarControles() {


        btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        mListaCats = (RecyclerView) findViewById(R.id.rvListaCats);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


        //revisa el intent


        final CategoriasAdapter mAdapter = new CategoriasAdapter(this, categ.ConsultarListaCategorias());


        //si no cambia el layout
        mListaCats.setHasFixedSize(true);

        // para sm es grid con 2 cols
        if (smartphone)
            mLayoutManagerListaCats = new GridLayoutManager(this, 2);
        else
            mLayoutManagerListaCats = new GridLayoutManager(this, 4);
        mListaCats.setLayoutManager(mLayoutManagerListaCats);
        //  mListaCats.addItemDecoration(new MarginDecoration(this));


        mListaCats.setAdapter(mAdapter);

        mLayoutManagerListaCats.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.isHeader(position) ? mLayoutManagerListaCats.getSpanCount() : 1;
            }
        });


    }




    public static Intent getLaunchIntent(Context context) {

        Intent i = new Intent(new Intent(context, CategoriasActivity.class));
        return i;
    }
}
