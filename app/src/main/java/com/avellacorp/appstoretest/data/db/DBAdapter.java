package com.avellacorp.appstoretest.data.db;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.avellacorp.appstoretest.entities.Aplicacion;
import com.avellacorp.appstoretest.entities.Categoria;
import com.avellacorp.appstoretest.entities.Configuracion;
import com.avellacorp.appstoretest.entities.Store;

import java.util.ArrayList;
import java.util.List;


/**
 * Esta clase permite realizar todas las operaciones en la base de datos que se
 * crea interna a la aplicación, principalmente consultas, actualización y
 * borrado de datos.
 *
 * @author Carlosas
 */
public class DBAdapter {

    public SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context context;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public DBAdapter open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //region Campos de la BD
    public static final String KEY_ID = "Id";
    public static final String KEY_NOMBRE = "Nombre";
    public static final String KEY_DERECHOS = "Derechos";

    //tabla store
    public static final String KEY_FECHA = "Fecha";
    public static final String KEY_AUTOR = "Autor";

    //tabla categoria
    public static final String KEY_URL = "Url";

    //tabla aplicacion
    public static final String KEY_RESUMEN = "Resumen";
    public static final String KEY_URL_IMAGEN = "UrlImagen";
    public static final String KEY_TIPO_MONEDA = "TipoMoneda";
    public static final String KEY_TIPO_APLICACION = "TipoAplicacion";
    public static final String KEY_TITULO = "Titulo";
    public static final String KEY_URL_APLICACION = "UrlAplicacion";
    public static final String KEY_ARTISTA = "Artista";
    public static final String KEY_URL_ARTISTA = "UrlArtista";
    public static final String KEY_FECHA_CREACION = "FechaCreacion";
    public static final String KEY_ID_CATEGORIA = "IdCategoria";


    //tabla configuracion
    public static final String KEY_PARAMETRO = "Parametro";
    public static final String KEY_VALOR = "Valor";


    //endregion

    //region Tablas de la BD
    public static final String DATABASE_CONFIGURACION = "Configuracion";
    public static final String DATABASE_APLICACION = "Aplicacion";
    public static final String DATABASE_CATEGORIA = "Categoria";
    public static final String DATABASE_STORE = "Store";

    //endregion

    //region CRUD CONFIGURACION

    public void createConfiguracion(List<Configuracion> configuracionList) {
        String sql = "insert into " + DATABASE_CONFIGURACION + " (" + KEY_PARAMETRO + ", " +
                KEY_VALOR + ") values (?, ?);";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            for (Configuracion configuracion : configuracionList) {

                stmt.bindString(1, configuracion.getParametro());
                stmt.bindString(2, configuracion.getValor());

                Configuracion config = fetchConfiguracion(configuracion.getParametro());

                if (config.getParametro() == null) {
                    stmt.executeInsert();
                    stmt.clearBindings();
                } else {
                    //Se actualiza
                    updateConfiguracion(configuracion);
                }
            }

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public void updateConfiguracion(Configuracion configuracion) {

        String sql = "update " + DATABASE_CONFIGURACION
                + " set " + KEY_VALOR + "=?"
                + " where " + KEY_PARAMETRO + "=? ;";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            stmt.bindString(1, configuracion.getValor());
            stmt.bindString(2, configuracion.getParametro());

            stmt.execute();
            stmt.clearBindings();

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public void deleteConfiguracion(Configuracion configuracion) {

        String sql = "delete from " + DATABASE_CONFIGURACION
                + " where " + KEY_PARAMETRO + "=?  ;";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            stmt.bindString(1, configuracion.getParametro());

            stmt.execute();
            stmt.clearBindings();

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public void deleteAllConfiguracion() {

        String sql = "delete from " + DATABASE_CONFIGURACION + " ;";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {


            stmt.execute();
            stmt.clearBindings();

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public Configuracion fetchConfiguracion(String parametro) {

        Cursor mCursor = database.query(DATABASE_CONFIGURACION, new String[]{KEY_PARAMETRO,
                        KEY_VALOR}, KEY_PARAMETRO + "='" + parametro + "'", null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToConfiguracion(mCursor);

    }

    private Configuracion cursorToConfiguracion(Cursor cursor) {

        //Convierte un cursor de configuración a un objeto de configuración

        Configuracion configuracion = new Configuracion();

        try {
            if (cursor.getCount() > 0) {

                //Obtiene los indices de las columnas de la tabla seccion

                int codParametroIndex = cursor.getColumnIndex(KEY_PARAMETRO);
                int codValorIndex = cursor.getColumnIndex(KEY_VALOR);

                while (!cursor.isAfterLast()) {

                    String Parametro = cursor.getString(codParametroIndex);
                    String Valor = cursor.getString(codValorIndex);

                    Configuracion config = new Configuracion(Parametro, Valor);
                    configuracion = config;

                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return configuracion;

    }

    //endregion

    //region CRUD APLICACION

    public void createAplicacion(List<Aplicacion> appList) {
        String sql = "insert into " + DATABASE_APLICACION + " (" + KEY_ID + ", " +
                KEY_NOMBRE + ", " + KEY_RESUMEN + ", " + KEY_URL_IMAGEN + ", " +
                KEY_TIPO_MONEDA + ", " + KEY_VALOR + ", " + KEY_TIPO_APLICACION + ", " +
                KEY_DERECHOS + ", " + KEY_TITULO + ", " + KEY_URL_APLICACION + ", " +
                KEY_ARTISTA + ", " + KEY_URL_ARTISTA + ", " + KEY_FECHA_CREACION + ", " +
                KEY_ID_CATEGORIA + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            for (Aplicacion app : appList) {

                stmt.bindString(1, app.getId());
                stmt.bindString(2, app.getNombre());
                stmt.bindString(3, app.getResumen());
                stmt.bindString(4, app.getUrlImagen());
                stmt.bindString(5, app.getTipoMoneda());
                stmt.bindString(6, app.getValor());
                stmt.bindString(7, app.getTipoAplicacion());
                stmt.bindString(8, app.getDerechos());
                stmt.bindString(9, app.getTitulo());
                stmt.bindString(10, app.getUrlAplicacion());
                stmt.bindString(11, app.getArtista());
                stmt.bindString(12, app.getUrlArtista());
                stmt.bindString(13, app.getFechaCreacion());
                stmt.bindString(14, app.getIdCategoria());

                stmt.executeInsert();
                stmt.clearBindings();


            }

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }


    public void deleteAllAplicacion() {

        String sql = "delete from " + DATABASE_APLICACION + " ;";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            stmt.execute();
            stmt.clearBindings();

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public Aplicacion fetchAplicacion(String idApp) {

        Cursor mCursor = database.query(DATABASE_APLICACION, new String[]{KEY_ID,
                        KEY_NOMBRE, KEY_RESUMEN, KEY_URL_IMAGEN,
                        KEY_TIPO_MONEDA, KEY_VALOR, KEY_TIPO_APLICACION,
                        KEY_DERECHOS, KEY_TITULO, KEY_URL_APLICACION + ", " +
                        KEY_ARTISTA + ", " + KEY_URL_ARTISTA + ", " + KEY_FECHA_CREACION + ", " +
                        KEY_ID_CATEGORIA}, KEY_ID + "='" + idApp + "'", null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToAplicacion(mCursor).size() > 0 ? cursorToAplicacion(mCursor).get(0) : null;

    }

    public List<Aplicacion> fetchAllAplicacion() {

        Cursor mCursor = database.query(DATABASE_APLICACION, new String[]{KEY_ID,
                        KEY_NOMBRE, KEY_RESUMEN, KEY_URL_IMAGEN,
                        KEY_TIPO_MONEDA, KEY_VALOR, KEY_TIPO_APLICACION,
                        KEY_DERECHOS, KEY_TITULO, KEY_URL_APLICACION + ", " +
                        KEY_ARTISTA + ", " + KEY_URL_ARTISTA + ", " + KEY_FECHA_CREACION + ", " +
                        KEY_ID_CATEGORIA}, null, null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToAplicacion(mCursor);

    }

    public List<Aplicacion> fetchAplicacionByCategory(String idCat) {

        Cursor mCursor = database.query(DATABASE_APLICACION, new String[]{KEY_ID,
                        KEY_NOMBRE, KEY_RESUMEN, KEY_URL_IMAGEN,
                        KEY_TIPO_MONEDA, KEY_VALOR, KEY_TIPO_APLICACION,
                        KEY_DERECHOS, KEY_TITULO, KEY_URL_APLICACION + ", " +
                        KEY_ARTISTA + ", " + KEY_URL_ARTISTA + ", " + KEY_FECHA_CREACION + ", " +
                        KEY_ID_CATEGORIA}, KEY_ID_CATEGORIA + "='" + idCat + "'", null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToAplicacion(mCursor);

    }

    private List<Aplicacion> cursorToAplicacion(Cursor cursor) {

        //Convierte un cursor de Lista a un objeto de Apliacacion

        List<Aplicacion> apps = new ArrayList<>();

        try {
            if (cursor.getCount() > 0) {

                //Obtiene los indices de las columnas de la tabla app

                int _Id = cursor.getColumnIndex(KEY_ID);
                int _Nombre = cursor.getColumnIndex(KEY_NOMBRE);
                int _Resumen = cursor.getColumnIndex(KEY_RESUMEN);
                int _UrlImagen = cursor.getColumnIndex(KEY_URL_IMAGEN);
                int _TipoMoneda = cursor.getColumnIndex(KEY_TIPO_MONEDA);
                int _Valor = cursor.getColumnIndex(KEY_VALOR);
                int _TipoAplicacion = cursor.getColumnIndex(KEY_TIPO_APLICACION);
                int _Derechos = cursor.getColumnIndex(KEY_DERECHOS);
                int _Titulo = cursor.getColumnIndex(KEY_TITULO);
                int _UrlAplicacion = cursor.getColumnIndex(KEY_URL_APLICACION);
                int _Artista = cursor.getColumnIndex(KEY_ARTISTA);
                int _UrlArtista = cursor.getColumnIndex(KEY_URL_ARTISTA);
                int _FechaCreacion = cursor.getColumnIndex(KEY_FECHA_CREACION);
                int _IdCategoria = cursor.getColumnIndex(KEY_ID_CATEGORIA);

                while (!cursor.isAfterLast()) {

                    String Id = cursor.getString(_Id);
                    String Nombre = cursor.getString(_Nombre);
                    String Resumen = cursor.getString(_Resumen);
                    String UrlImagen = cursor.getString(_UrlImagen);
                    String TipoMoneda = cursor.getString(_TipoMoneda);
                    String Valor = cursor.getString(_Valor);
                    String TipoAplicacion = cursor.getString(_TipoAplicacion);
                    String Derechos = cursor.getString(_Derechos);
                    String Titulo = cursor.getString(_Titulo);
                    String UrlAplicacion = cursor.getString(_UrlAplicacion);
                    String Artista = cursor.getString(_Artista);
                    String UrlArtista = cursor.getString(_UrlArtista);
                    String FechaCreacion = cursor.getString(_FechaCreacion);
                    String IdCategoria = cursor.getString(_IdCategoria);

                    Aplicacion app = new Aplicacion(Artista, Derechos, FechaCreacion, Id,
                            IdCategoria, Nombre, Resumen, TipoAplicacion,
                            TipoMoneda, Titulo, UrlAplicacion, UrlArtista,
                            UrlImagen, Valor);
                    apps.add(app);

                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return apps;

    }

    //endregion

    //region CRUD CATEGORIA

    public void createCategoria(List<Categoria> catList) {
        String sql = "insert into " + DATABASE_CATEGORIA + " (" + KEY_ID + ", " +
                KEY_NOMBRE + ", " + KEY_URL + ") values (?, ?, ?);";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            for (Categoria cat : catList) {

                stmt.bindString(1, cat.getId());
                stmt.bindString(2, cat.getNombre());
                stmt.bindString(3, cat.getUrl());

                stmt.executeInsert();
                stmt.clearBindings();

            }

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }


    public void deleteAllCategoria() {

        String sql = "delete from " + DATABASE_CATEGORIA + " ;";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            stmt.execute();
            stmt.clearBindings();

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public Categoria fetchCategoria(String idCat) {

        Cursor mCursor = database.query(DATABASE_CATEGORIA, new String[]{KEY_ID,
                        KEY_NOMBRE, KEY_URL}, KEY_ID + "='" + idCat + "'", null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToCategoria(mCursor).size() > 0 ? cursorToCategoria(mCursor).get(0) : null;

    }

    public List<Categoria> fetchAllCategoria() {

        Cursor mCursor = database.query(DATABASE_CATEGORIA, new String[]{KEY_ID,
                        KEY_NOMBRE, KEY_URL}, null, null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToCategoria(mCursor);

    }

    private List<Categoria> cursorToCategoria(Cursor cursor) {

        //Convierte un cursor de Lista a un objeto de Categoria

        List<Categoria> cats = new ArrayList<>();

        try {
            if (cursor.getCount() > 0) {

                //Obtiene los indices de las columnas de la tabla app

                int _Id = cursor.getColumnIndex(KEY_ID);
                int _Nombre = cursor.getColumnIndex(KEY_NOMBRE);
                int _Url = cursor.getColumnIndex(KEY_URL);

                while (!cursor.isAfterLast()) {

                    String Id = cursor.getString(_Id);
                    String Nombre = cursor.getString(_Nombre);
                    String Url = cursor.getString(_Url);

                    Categoria cat = new Categoria(Id, Nombre, Url);
                    cats.add(cat);

                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return cats;

    }

    //endregion

    //region CRUD STORE

    public void createStore(List<Store> storeList) {
        String sql = "insert into " + DATABASE_STORE + " (" + KEY_NOMBRE + ", " +
                KEY_DERECHOS + ", " + KEY_FECHA + ", " + KEY_AUTOR + ") values (?, ?, ?, ?);";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            for (Store store : storeList) {

                stmt.bindString(1, store.getNombre());
                stmt.bindString(2, store.getDerechos());
                stmt.bindString(3, store.getFecha());
                stmt.bindString(4, store.getAutor());

                stmt.executeInsert();
                stmt.clearBindings();

            }

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public void deleteAllStore() {

        String sql = "delete from " + DATABASE_STORE + " ;";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);

        try {

            stmt.execute();
            stmt.clearBindings();

            // maybe we should do this in a transaction...OK!
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

    }

    public Store fetchStore() {

        Cursor mCursor = database.query(DATABASE_STORE, new String[]{KEY_NOMBRE,
                        KEY_DERECHOS, KEY_FECHA, KEY_AUTOR}, null, null, null,
                null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return cursorToStore(mCursor);

    }

    private Store cursorToStore(Cursor cursor) {

        //Convierte un cursor de Lista a un objeto de store

        Store store = new Store();

        try {
            if (cursor.getCount() > 0) {

                //Obtiene los indices de las columnas de la tabla store

                int _Nombre = cursor.getColumnIndex(KEY_NOMBRE);
                int _Derechos = cursor.getColumnIndex(KEY_DERECHOS);
                int _Fecha = cursor.getColumnIndex(KEY_FECHA);
                int _Autor = cursor.getColumnIndex(KEY_AUTOR);

                while (!cursor.isAfterLast()) {

                    String Nombre = cursor.getString(_Nombre);
                    String Derechos = cursor.getString(_Derechos);
                    String Fecha = cursor.getString(_Fecha);
                    String Autor = cursor.getString(_Autor);

                    store = new Store(Autor, Derechos, Fecha, Nombre);


                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return store;

    }


    //endregion
}