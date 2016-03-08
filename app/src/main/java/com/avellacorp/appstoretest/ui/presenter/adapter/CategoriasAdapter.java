package com.avellacorp.appstoretest.ui.presenter.adapter;

import android.content.Context;
import android.net.Uri;
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
import com.avellacorp.appstoretest.ui.customviews.RobotoTextView;
import com.avellacorp.appstoretest.ui.presenter.Navigator;
import com.avellacorp.appstoretest.ui.presenter.transitions.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Vector;

/**
 * Created by Carlos on 04/03/2016.
 */
public class CategoriasAdapter extends
        RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private LayoutInflater inflador; //Crea Layouts a partir del XML
    protected Vector<Categoria> vectorCats;
    private Context context;
    private int lastPosition = -1;

    public CategoriasAdapter(Context contexto,  Vector<Categoria> vectorCats) {
        inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.vectorCats = vectorCats;

        this.context = contexto;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Nombre;
        RobotoTextView Titulo;
        View.OnClickListener onClickListener;

        public ViewHolder(View itemView, boolean isHeader) {
            super(itemView);
            if (isHeader) {
                Titulo = (RobotoTextView) itemView.findViewById(R.id.txtTitulo);

            } else {

                cv = (CardView) itemView.findViewById(R.id.cv);
                Nombre = (TextView) itemView.findViewById(R.id.txtNombre);
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
                .inflate(R.layout.categoria_item, parent, false);
        return new ViewHolder(view, false);

    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(final ViewHolder holder, int posicion) {

        if (isHeader(posicion)) {

            holder.Titulo.setText(context.getString(R.string.categorias));
            holder.Titulo.setRobotoTypeface(Constant.ROBOTO_CONDENSED);
        } else {

            final Categoria cat = vectorCats.elementAt(posicion - 1);
            holder.Nombre.setText(cat.getNombre());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Navigator(context).openStoreActivity(cat.getId());

                }
            });


        }

        setAnimation(holder.itemView, posicion);


    }


    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_up);
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
        return vectorCats.size() + 1;
    }
}