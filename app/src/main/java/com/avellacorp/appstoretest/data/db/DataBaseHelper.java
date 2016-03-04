package com.avellacorp.appstoretest.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.entities.Aplicacion;
import com.avellacorp.appstoretest.entities.Categoria;
import com.avellacorp.appstoretest.entities.Configuracion;
import com.avellacorp.appstoretest.entities.Store;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Esta clase permite la creación, actualización y apertura de la base de datos
 * interna a la aplicación. Adicionalmente permite insertar valores iniciales a
 * las tablas de la base de datos.
 * 
 * @author Carlosas
 * 
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;


    static {
        // register our models
        /**
         * Este fragmento se debe cambiar dependiendo la aplicación
         */
        cupboard().register(Aplicacion.class);
        cupboard().register(Categoria.class);
        cupboard().register(Store.class);
        cupboard().register(Configuracion.class);

    }


	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


    // Este método se llama al momento en el que se crea la BD
	@Override
	public void onCreate(SQLiteDatabase database) {


        // this will ensure that all tables are created
        cupboard().withDatabase(database).createTables();
        // add indexes and other database tweaks

        //Se insertan los parametros iniciales a la tabla configuracion
        cupboard().withDatabase(database).put(new Configuracion(Constant.CONFIGURACION_VISUALIZACION,"true"));

	}


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work
    }


}
