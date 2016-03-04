package com.avellacorp.appstoretest.bussines.aplicaciones;

import android.content.Context;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.base.GenericReturn;
import com.avellacorp.appstoretest.data.db.DBAdapter;
import com.avellacorp.appstoretest.data.db.DBManager;
import com.avellacorp.appstoretest.entities.Aplicacion;
import com.avellacorp.appstoretest.entities.Categoria;
import com.avellacorp.appstoretest.entities.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlosas
 */
public class AplicacionBussines {

    private Context context;
    private DBAdapter dbAdapter;
    final SimpleDateFormat formatNotice = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public AplicacionBussines(Context context) {

        this.context = context;
        dbAdapter =  DBManager.getInstance(context);

    }

    /**
     * Esta función se encarga de actualizar la información  de las tienda
     *
     * @param data Objeto JSON para actualizar
     */
    public GenericReturn ActualizarStore(JSONObject data, String fechaAnterior) {

        GenericReturn r = new GenericReturn();

        try {
            JSONObject feed;
            JSONObject primero;
            JSONObject segundo;
            JSONObject tercero;

            feed = data.getJSONObject(Constant.FUNCTION_FEED);

            //Si el feed está vacío hubo un error en el servicio

            if (feed.length() == 0) {
                r.message = context.getString(R.string.errorActualización);
                r.codOperation = Constant.OPERATION_FAIL;
                r.exception = true;
            } else {


                List<Aplicacion> aplicacionList = new ArrayList<>();
                List<Categoria> categoriaList = new ArrayList<>();
                List<Store> storeList = new ArrayList<>();
                List<String> tempCat = new ArrayList<>();

                //Inserta

                Store storeEntity = new Store();

                if (feed.has(Constant.PARAM_TITULO)) {
                    primero = feed.getJSONObject(Constant.PARAM_TITULO);
                    if (primero.has(Constant.PARAM_LABEL))
                        storeEntity.setNombre(primero.getString(Constant.PARAM_LABEL));
                }

                if (feed.has(Constant.PARAM_DERECHOS)) {
                    primero = feed.getJSONObject(Constant.PARAM_DERECHOS);
                    if (primero.has(Constant.PARAM_LABEL))
                        storeEntity.setDerechos(primero.getString(Constant.PARAM_LABEL));
                }

                if (feed.has(Constant.PARAM_FECHA_ACTUALIZACION)) {
                    primero = feed.getJSONObject(Constant.PARAM_FECHA_ACTUALIZACION);
                    if (primero.has(Constant.PARAM_LABEL))
                        storeEntity.setFecha(primero.getString(Constant.PARAM_LABEL));
                }

                if (feed.has(Constant.PARAM_AUTHOR)) {
                    primero = feed.getJSONObject(Constant.PARAM_AUTHOR);
                    if (primero.has(Constant.PARAM_NOMBRE)) {
                        segundo = primero.getJSONObject(Constant.PARAM_NOMBRE);
                        if (segundo.has(Constant.PARAM_LABEL))
                            storeEntity.setAutor(segundo.getString(Constant.PARAM_LABEL));
                    }
                }

                storeList.add(storeEntity);


                //valida si la fecha obtenida es posterior a la fecha anterior

                //si es posterior continua guardando

                if (fechaAnterior == null || formatNotice.parse(storeEntity.getFecha()).getTime() > formatNotice.parse(fechaAnterior).getTime()) {

                    //aplicacion

                    JSONArray aplicaciones = feed.getJSONArray(Constant.DICTIONARY_APLICACIONES);

                    for (int i = 0; i < aplicaciones.length(); i++) {

                        primero = aplicaciones.getJSONObject(i);
                        Aplicacion aplicacionEntity = new Aplicacion();
                        Categoria categoriaEntity = new Categoria();

                        if (primero.has(Constant.PARAM_ID)) {

                            segundo = primero.getJSONObject(Constant.PARAM_ID);

                            if (segundo.has(Constant.PARAM_LABEL))
                                aplicacionEntity.setUrlAplicacion(segundo.getString(Constant.PARAM_LABEL));

                            if (segundo.has(Constant.PARAM_ATRIBUTOS)) {
                                tercero = segundo.getJSONObject(Constant.PARAM_ATRIBUTOS);
                                if (tercero.has(Constant.PARAM_IM_ID))
                                    aplicacionEntity.setId(tercero.getString(Constant.PARAM_IM_ID));
                            }
                        }

                        if (primero.has(Constant.PARAM_IM_NOMBRE)) {

                            segundo = primero.getJSONObject(Constant.PARAM_IM_NOMBRE);

                            if (segundo.has(Constant.PARAM_LABEL))
                                aplicacionEntity.setNombre(segundo.getString(Constant.PARAM_LABEL));

                        }

                        if (primero.has(Constant.PARAM_RESUMEN)) {

                            segundo = primero.getJSONObject(Constant.PARAM_RESUMEN);

                            if (segundo.has(Constant.PARAM_LABEL))
                                aplicacionEntity.setResumen(segundo.getString(Constant.PARAM_LABEL));

                        }

                        if (primero.has(Constant.PARAM_PRECIO)) {

                            segundo = primero.getJSONObject(Constant.PARAM_PRECIO);

                            if (segundo.has(Constant.PARAM_ATRIBUTOS)) {
                                tercero = segundo.getJSONObject(Constant.PARAM_ATRIBUTOS);
                                if (tercero.has(Constant.PARAM_MONEDA))
                                    aplicacionEntity.setTipoMoneda(tercero.getString(Constant.PARAM_MONEDA));
                                if (tercero.has(Constant.PARAM_VALOR))
                                    aplicacionEntity.setValor(tercero.getString(Constant.PARAM_VALOR));
                            }
                        }

                        if (primero.has(Constant.PARAM_TIPO_APP)) {

                            segundo = primero.getJSONObject(Constant.PARAM_TIPO_APP);

                            if (segundo.has(Constant.PARAM_ATRIBUTOS)) {
                                tercero = segundo.getJSONObject(Constant.PARAM_ATRIBUTOS);
                                if (tercero.has(Constant.PARAM_LABEL))
                                    aplicacionEntity.setTipoAplicacion(tercero.getString(Constant.PARAM_LABEL));

                            }

                        }

                        if (primero.has(Constant.PARAM_DERECHOS)) {

                            segundo = primero.getJSONObject(Constant.PARAM_DERECHOS);

                            if (segundo.has(Constant.PARAM_LABEL))
                                aplicacionEntity.setDerechos(segundo.getString(Constant.PARAM_LABEL));


                        }


                        if (primero.has(Constant.PARAM_TITULO)) {

                            segundo = primero.getJSONObject(Constant.PARAM_TITULO);

                            if (segundo.has(Constant.PARAM_LABEL))
                                aplicacionEntity.setTitulo(segundo.getString(Constant.PARAM_LABEL));


                        }

                        if (primero.has(Constant.PARAM_ARTISTA)) {

                            segundo = primero.getJSONObject(Constant.PARAM_ARTISTA);

                            if (segundo.has(Constant.PARAM_LABEL))
                                aplicacionEntity.setArtista(segundo.getString(Constant.PARAM_LABEL));

                            if (segundo.has(Constant.PARAM_ATRIBUTOS)) {
                                tercero = segundo.getJSONObject(Constant.PARAM_ATRIBUTOS);
                                if (tercero.has(Constant.PARAM_ARTISTA_URL))
                                    aplicacionEntity.setUrlArtista(tercero.getString(Constant.PARAM_ARTISTA_URL));
                            }
                        }

                        if (primero.has(Constant.PARAM_FECHA_APP)) {

                            segundo = primero.getJSONObject(Constant.PARAM_FECHA_APP);

                            if (segundo.has(Constant.PARAM_ATRIBUTOS)) {
                                tercero = segundo.getJSONObject(Constant.PARAM_ATRIBUTOS);
                                if (tercero.has(Constant.PARAM_LABEL))
                                    aplicacionEntity.setFechaCreacion(tercero.getString(Constant.PARAM_LABEL));
                            }

                        }

                        if (primero.has(Constant.DICTIONARY_IMAGENES)) {

                            final JSONArray imagenes = primero.getJSONArray(Constant.DICTIONARY_IMAGENES);

                            //Solo se guarda una, la de mayor res

                            if (imagenes.length() > 0) {
                                segundo = imagenes.getJSONObject(imagenes.length() - 1);
                                if (segundo.has(Constant.PARAM_LABEL))
                                    aplicacionEntity.setUrlImagen(segundo.getString(Constant.PARAM_LABEL));

                            }

                        }

                        //para la categoria se va creando el arreglo

                        if (primero.has(Constant.PARAM_CATEGORIA)) {

                            segundo = primero.getJSONObject(Constant.PARAM_CATEGORIA);

                            if (segundo.has(Constant.PARAM_ATRIBUTOS)) {
                                tercero = segundo.getJSONObject(Constant.PARAM_ATRIBUTOS);
                                if (tercero.has(Constant.PARAM_IM_ID))
                                    aplicacionEntity.setIdCategoria(tercero.getString(Constant.PARAM_IM_ID));

                                if (!tempCat.contains(tercero.getString(Constant.PARAM_IM_ID))) {
                                    categoriaEntity.setId(tercero.getString(Constant.PARAM_IM_ID));
                                    if (tercero.has(Constant.PARAM_LABEL))
                                        categoriaEntity.setNombre(tercero.getString(Constant.PARAM_LABEL));
                                    if (tercero.has(Constant.PARAM_CATEGORIA_URL))
                                        categoriaEntity.setUrl(tercero.getString(Constant.PARAM_CATEGORIA_URL));

                                    tempCat.add(tercero.getString(Constant.PARAM_IM_ID));
                                    categoriaList.add(categoriaEntity);
                                }

                            }

                        }

                        aplicacionList.add(aplicacionEntity);

                    }

                    //borra las tablas de store, aplicaciones y categorias y las recrea
                    dbAdapter.deleteAllAplicacion();
                    dbAdapter.deleteAllCategoria();
                    dbAdapter.deleteAllStore();

                    dbAdapter.createStore(storeList);
                    dbAdapter.createCategoria(categoriaList);
                    dbAdapter.createAplicacion(aplicacionList);

                }

                r.message = "";
                r.codOperation = Constant.OPERATION_SUCCESS;
                r.data = data;
                r.exception = false;
            }

        } catch (JSONException e) {
            r.message = context.getString(R.string.errorConversion);
            r.codOperation = Constant.OPERATION_FAIL;
            r.exception = true;

        } catch (Exception e) {
            r.message = context.getString(R.string.errorDesconocido);
            r.codOperation = Constant.OPERATION_FAIL;
            r.exception = true;

        }

        return r;

    }

    /**
     * Método encargado de obtener las aplicaciones
     *
     * @return Respuesta
     */
    public List<Aplicacion> ConsultarListaAplicaciones() {

        //Obtiene una lista

        List<Aplicacion> listaApps =   dbAdapter.fetchAllAplicacion();

        return listaApps;
    }

    public List<Aplicacion> ConsultarListaAplicacionesByCategoria(String idCategoria) {

        //Obtiene una lista

        List<Aplicacion> listaApps =   dbAdapter.fetchAplicacionByCategory(idCategoria);

        return listaApps;
    }

    public Aplicacion ConsultarAplicacionDetalle(String idAplicacion) {

        //Obtiene una lista

       Aplicacion app =   dbAdapter.fetchAplicacion(idAplicacion);

        return app;
    }

}
