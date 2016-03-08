package com.avellacorp.appstoretest.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.entities.Aplicacion;
import com.avellacorp.appstoretest.entities.Categoria;
import com.avellacorp.appstoretest.ui.presenter.transitions.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class AplicacionDialog extends MasterActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicacion_dialog);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        ((View) findViewById(R.id.cv).getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finishAfterTransition();
                onBackPressed();
            }
        });
        findViewById(R.id.cv).setOnClickListener(null);

        final Intent prevInt = getIntent();

        final Uri url = Uri.parse(prevInt.getStringExtra(Constant.IMAGEN_EX));
        final Transformation transformation = new RoundedCornersTransformation(20, 0);

        Picasso.with(findViewById(R.id.ivImagen).getContext())
                .load(url)
                .transform(transformation)
                .into(((ImageView) findViewById(R.id.ivImagen)));

        final Double cost = Double.parseDouble(prevInt.getStringExtra(Constant.VALOR_EX));

        ((TextView) findViewById(R.id.txtNombre)).setText(prevInt.getStringExtra(Constant.NOMBRE_EX));
        ((TextView) findViewById(R.id.txtResumen)).setText(prevInt.getStringExtra(Constant.RESUMEN_EX));
        ((TextView) findViewById(R.id.txtPrecio)).setText(cost == 0 ? "Free" : String.valueOf(cost) + prevInt.getStringExtra(Constant.MONEDA_EX));
        ((TextView) findViewById(R.id.txtCategoria)).setText(prevInt.getStringExtra(Constant.CATEGORIA_EX));
        ((TextView) findViewById(R.id.txtAutor)).setText(prevInt.getStringExtra(Constant.AUTOR_EX));
        ((TextView) findViewById(R.id.txtDerechos)).setText(prevInt.getStringExtra(Constant.DERECHOS_EX));

        findViewById(R.id.ivImagen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse(prevInt.getStringExtra(Constant.URL_APP_EX)));
                startActivity(i);
            }
        });

        findViewById(R.id.txtAutor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse(prevInt.getStringExtra(Constant.URL_AUTOR_EX)));
                startActivity(i);
            }
        });

        findViewById(R.id.txtCategoria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse(prevInt.getStringExtra(Constant.URL_CATEGORIA_EX)));
                startActivity(i);
            }
        });


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    public static Intent getLaunchIntent(Context context, Aplicacion aplicacion, Categoria categoria) {


        Intent i = new Intent(new Intent(context, AplicacionDialog.class));
        i.putExtra(Constant.IMAGEN_EX, aplicacion.getUrlImagen());
        i.putExtra(Constant.NOMBRE_EX, aplicacion.getNombre());
        i.putExtra(Constant.VALOR_EX, aplicacion.getValor());
        i.putExtra(Constant.MONEDA_EX, aplicacion.getTipoMoneda());
        i.putExtra(Constant.CATEGORIA_EX, categoria.getNombre());
        i.putExtra(Constant.AUTOR_EX, aplicacion.getArtista());
        i.putExtra(Constant.DERECHOS_EX, aplicacion.getDerechos());
        i.putExtra(Constant.RESUMEN_EX, aplicacion.getResumen());
        i.putExtra(Constant.URL_APP_EX, aplicacion.getUrlAplicacion());
        i.putExtra(Constant.URL_AUTOR_EX, aplicacion.getUrlArtista());
        i.putExtra(Constant.URL_CATEGORIA_EX, categoria.getUrl());


        return i;

    }

}
