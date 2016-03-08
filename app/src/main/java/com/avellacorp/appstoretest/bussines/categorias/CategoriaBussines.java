package com.avellacorp.appstoretest.bussines.categorias;

import android.content.Context;

import com.avellacorp.appstoretest.data.db.DBAdapter;
import com.avellacorp.appstoretest.data.db.DBManager;
import com.avellacorp.appstoretest.entities.Categoria;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

/**
 * Created by Carlosas
 */
public class CategoriaBussines {

    private Context context;
    private DBAdapter dbAdapter;
    final SimpleDateFormat formatNotice = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public CategoriaBussines(Context context) {

        this.context = context;
        dbAdapter  = DBManager.getInstance(context);

    }


    /**
     * Método encargado de obtener las categorias
     *
     * @return Respuesta
     */
    public Vector<Categoria> ConsultarListaCategorias() {

        //Obtiene una lista

        Vector<Categoria> listaCats =   dbAdapter.fetchAllCategoria();

        return listaCats;
    }

    public Categoria ConsultarCategoriaDetalle(String idCat) {

        //Obtiene una lista

        Categoria cat =   dbAdapter.fetchCategoria(idCat);

        return cat;
    }


}
