package com.avellacorp.appstoretest.bussines.categorias;

import android.content.Context;

import com.avellacorp.appstoretest.data.db.DBAdapter;
import com.avellacorp.appstoretest.data.db.DBManager;
import com.avellacorp.appstoretest.entities.Categoria;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Carlosas
 */
public class CategoriaBussines {

    private Context context;
    private DBAdapter dbAdapter;
    final SimpleDateFormat formatNotice = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssTZD");

    public CategoriaBussines(Context activity) {

        this.context = context;
        dbAdapter = dbAdapter = DBManager.getInstance(context);

    }


    /**
     * Método encargado de obtener las categorias
     *
     * @return Respuesta
     */
    public List<Categoria> ConsultarListaCategorias() {

        //Obtiene una lista

        List<Categoria> listaCats =   dbAdapter.fetchAllCategoria();

        return listaCats;
    }

    public Categoria ConsultarCategoriaDetalle(String idCat) {

        //Obtiene una lista

        Categoria cat =   dbAdapter.fetchCategoria(idCat);

        return cat;
    }


}
