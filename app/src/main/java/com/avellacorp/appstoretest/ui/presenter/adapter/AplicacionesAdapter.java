package com.avellacorp.appstoretest.ui.presenter.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.entities.Aplicacion;
import com.avellacorp.appstoretest.entities.Categoria;
import com.avellacorp.appstoretest.entities.Store;
import com.avellacorp.appstoretest.ui.activities.AplicacionDialog;
import com.avellacorp.appstoretest.ui.activities.StoreActivity;
import com.avellacorp.appstoretest.ui.customviews.RobotoTextView;
import com.avellacorp.appstoretest.ui.presenter.Navigator;
import com.avellacorp.appstoretest.ui.presenter.transitions.PaletteTransformation;
import com.avellacorp.appstoretest.ui.presenter.transitions.PaletteTransformation.PaletteCallback;
import com.avellacorp.appstoretest.ui.presenter.transitions.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Vector;

/**
 * Created by Carlos on 04/03/2016.
 */
public class AplicacionesAdapter extends
        RecyclerView.Adapter<AplicacionesAdapter.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private LayoutInflater inflador; //Crea Layouts a partir del XML
    protected Vector<Aplicacion> vectorApps;
    protected Vector<Categoria> vectorCats;
    protected Store store;
    private Context context;
    private int lastPosition = -1;

    public AplicacionesAdapter(Context contexto, Vector<Aplicacion> vectorApps, Vector<Categoria> vectorCats, Store store) {
        inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vectorApps = vectorApps;
        this.vectorCats = vectorCats;
        this.store = store;
        this.context = contexto;
    }

    public AplicacionesAdapter(Context contexto, Vector<Aplicacion> vectorApps, Categoria categoria) {

        inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vectorApps = vectorApps;
        this.vectorCats= new Vector<>();
        this.vectorCats.add(categoria);
        this.context = contexto;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Nombre;
        TextView Categoria;
        TextView Precio;
        ImageView Imagen;
        RobotoTextView Titulo;
        View.OnClickListener onClickListener;


        public ViewHolder(View itemView, boolean isHeader) {
            super(itemView);
            if (isHeader) {
                Titulo = (RobotoTextView) itemView.findViewById(R.id.txtTitulo);

            } else {
                Imagen = (ImageView) itemView.findViewById(R.id.ivImagen);
                cv = (CardView) itemView.findViewById(R.id.cv);
                Nombre = (TextView) itemView.findViewById(R.id.txtNombre);
                Categoria = (TextView) itemView.findViewById(R.id.txtCategoria);
                Precio = (TextView) itemView.findViewById(R.id.txtPrecio);
                itemView.setOnClickListener(onClickListener);
            }
        }
    }

    // Creamos el ViewHolder con las vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View header = inflador.inflate(R.layout.aplicacion_header, parent, false);

        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return new ViewHolder(header, true);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aplicacion_item, parent, false);

        return new ViewHolder(view, false);



    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(final ViewHolder holder, int posicion) {

        if (isHeader(posicion)) {

            if (vectorCats.size() == 1)
                holder.Titulo.setText(vectorCats.get(posicion).getNombre());
            else
                holder.Titulo.setText(store.getNombre());
            holder.Titulo.setRobotoTypeface(Constant.ROBOTO_MEDIUM);
        } else {

            final Aplicacion app = vectorApps.elementAt(posicion - 1);
            holder.Nombre.setText(app.getNombre());
            Categoria categoria = null;

            for (Categoria cat : vectorCats) {
                if (cat.getId().equals(app.getIdCategoria())) {
                    holder.Categoria.setText(cat.getNombre());
                    categoria = cat;
                    break;
                }
            }

            final Double cost = Double.parseDouble(app.getValor());
            holder.Precio.setText(cost == 0 ? "Free" : String.valueOf(cost) + app.getTipoMoneda());
            final Uri url = Uri.parse(app.getUrlImagen());
            final Transformation transformation = new RoundedCornersTransformation(20, 0);

            Picasso.with(holder.Imagen.getContext())
                    .load(url)
                    .transform(transformation)
                    .transform(PaletteTransformation.instance())
                    .into(holder.Imagen, new PaletteTransformation.PaletteCallback(holder.Imagen) {
                        @Override
                        public void onError() {

                        }

                        @Override public void onSuccess(Palette palette) {

                            Palette.Swatch swatch= palette.getLightMutedSwatch();

                            if( swatch != null ) {
                                holder.Nombre.setTextColor(swatch.getTitleTextColor());
                                holder.cv.setCardBackgroundColor(swatch.getRgb());
                                holder.Categoria.setTextColor(swatch.getBodyTextColor());
                                holder.Precio.setTextColor(swatch.getBodyTextColor());
                            }else {
                                swatch= palette.getDarkMutedSwatch();

                                if( swatch != null ) {
                                    holder.Nombre.setTextColor(swatch.getTitleTextColor());
                                    holder.cv.setCardBackgroundColor(swatch.getRgb());
                                    holder.Categoria.setTextColor(swatch.getBodyTextColor());
                                    holder.Precio.setTextColor(swatch.getBodyTextColor());
                                }

                            }

                        }
                    });

            //ponerle colores aleatorios de una paleta
            final Categoria cat = categoria;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Navigator(context).openAppActivity(app, cat);


                }
            });


        }

        setAnimation(holder.itemView, posicion);


    }


    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ?
                ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    // Indicamos el número de elementos de la lista
    @Override
    public int getItemCount() {
        return vectorApps.size() + 1;
    }
}