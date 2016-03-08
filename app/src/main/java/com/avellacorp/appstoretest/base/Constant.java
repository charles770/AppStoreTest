package com.avellacorp.appstoretest.base;

/**
 * Esta clase se usa para establecer los datos que son constantes en la
 * aplicación.
 *
 * @author Carlosas
 */
public final class Constant {

    /**
     * Usa la aplicacion en modo Debug para visualizar errores
     */
    public final static Boolean DEBUG = false;

    /**
     * Tipos de dispositivos
     */
    public final static int TYPE_DEVICE_PHONE = 1;
    public final static int TYPE_DEVICE_TABLET = 2;

    /**
     * Tipos de codigos de operacion
     */
    public final static int OPERATION_SUCCESS = 0;
    public final static int OPERATION_FAIL = -1;

    /**
     * Prefijo y sufijo del servicio
     */
    public static final String URL_SERVER_PERFIX = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";

    /**
     * Códigos peticiones
     */
    public final static int ID_GET_APPS = 1;

    /**
     * timer splash
     */
    public static final int SPLASH_TIME_OUT = 4000;

    /**
     * enum roboto
     */
    public static final int ROBOTO_BLACK = 0;
    public static final int ROBOTO_BLACK_ITALIC = 1;
    public static final int ROBOTO_BOLD = 2;
    public static final int ROBOTO_BOLD_ITALIC = 3;
    public static final int ROBOTO_BOLD_CONDENSED = 4;
    public static final int ROBOTO_BOLD_CONDENSED_ITALIC = 5;
    public static final int ROBOTO_CONDENSED = 6;
    public static final int ROBOTO_CONDENSED_ITALIC = 7;
    public static final int ROBOTO_ITALIC = 8;
    public static final int ROBOTO_LIGHT = 9;
    public static final int ROBOTO_LIGHT_ITALIC = 10;
    public static final int ROBOTO_MEDIUM = 11;
    public static final int ROBOTO_MEDIUM_ITALIC = 12;
    public static final int ROBOTO_REGULAR = 13;
    public static final int ROBOTO_THIN = 14;
    public static final int ROBOTO_THIN_ITALIC = 15;

    /**
     * Códigos constantes para resultados de otras actividades
     */
    public static final String CATEGORIA = "CATEGORIA";
    public static final String APLICACION = "APLICACION";

    /**
     * Códigos constantes para extras de otras actividades
     */
    public static final String IMAGEN_EX = "imagen";
    public static final String NOMBRE_EX = "nombre";
    public static final String VALOR_EX = "valor";
    public static final String MONEDA_EX = "moneda";
    public static final String CATEGORIA_EX = "categoria";
    public static final String AUTOR_EX = "autor";
    public static final String DERECHOS_EX = "derechos";
    public static final String RESUMEN_EX = "resumen";
    public static final String URL_APP_EX = "urlApp";
    public static final String URL_AUTOR_EX = "urlAutor";
    public static final String URL_CATEGORIA_EX = "urlCategoria";

    public static final String ID_CATEGORIA_EX = "idCategoria";


    /**
     * Constantes para diálogos
     */
    public static final int PROGRESS_DIALOG = 1;
    public static final int DLG_APP_EXIT = 2;

    /**
     * Funciones usadas por el servicio
     */
    public static final String FUNCTION_FEED = "feed";

    /**
     * Nombre de carpetas usadas
     */
    public final static String FOLDER_FILES = "StoreTest";
    public final static String FOLDER_IMAGE = "Images";
    public final static String FILE_DATABASE = "database.db";

    /**
     * Nombres de parámetros usados en el servicio
     */
    public final static String PARAM_AUTHOR = "author";
    public final static String PARAM_NOMBRE = "name";
    public final static String PARAM_LABEL = "label";
    public final static String PARAM_ATRIBUTOS = "attributes";
    public final static String PARAM_FECHA_ACTUALIZACION = "updated";
    public final static String PARAM_DERECHOS = "rights";
    public final static String PARAM_TITULO = "title";
    public final static String PARAM_ID = "id";
    public final static String PARAM_IM_ID = "im:id";
    public final static String PARAM_IM_NOMBRE = "im:name";
    public final static String PARAM_RESUMEN = "summary";
    public final static String PARAM_ALTO_IMG = "height";
    public final static String PARAM_PRECIO = "im:price";
    public final static String PARAM_VALOR = "amount";
    public final static String PARAM_MONEDA = "currency";
    public final static String PARAM_TIPO_APP = "im:contentType";
    public final static String PARAM_ARTISTA = "im:artist";
    public final static String PARAM_ARTISTA_URL = "href";
    public final static String PARAM_FECHA_APP = "im:releaseDate";
    public final static String PARAM_CATEGORIA = "category";
    public final static String PARAM_CATEGORIA_URL = "scheme";

    /**
     * Nombre de los diccionarios retornados por el servicio
     */
    public final static String DICTIONARY_APLICACIONES = "entry";
    public final static String DICTIONARY_IMAGENES = "im:image";

    /**
     * Constantes de llaves para los parámetros de configuración
     */
    public final static String CONFIGURACION_VISUALIZACION = "Visualizacion";


}
