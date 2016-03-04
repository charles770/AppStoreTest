package com.avellacorp.appstoretest.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;


/**
 * Esta clase se encarga de las tareas de comunicación con un servicio de tipo
 * REST. Posee las funciones para comunicación con un servicio tanto con el
 * método GET como con el método POST. Adicionalmente, las peticiones get pueden
 * devolver un objeto String o pueden realizar alguna descarga que se guarda en
 * disco. La petición POST se hace por medio de formato JSON.
 *
 * @author Carlosas
 */
public class RestServices {

    public final static int TIMEOUT = 5000;
    static String jsonString = "";

    public RestServices() {

    }

    /**
     * Este método permite saber si existe realmente una conexión a internet con
     * salida de datos.
     *
     * @param URL
     * @return Bool si tiene o no conexión
     */
    public boolean GetInternetConection(String URL) {

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters,
                TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);

        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = null;
        try {
            URL url = new URL(URL);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(),
                    url.getHost(), url.getPort(), url.getPath(),
                    url.getQuery(), url.getRef());
            httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();

            if (entity == null)
                return false;
        } catch (Exception e) {
            return false;
        }

        return true;

    }


    public String makeHttpRequestGetEntity(String URL) {

        HttpParams httpParameters = new BasicHttpParams();
        int timeoutConnection = 5000;
        HttpConnectionParams.setConnectionTimeout(httpParameters,
                timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutConnection);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);

        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = null;
        try {
            URL url = new URL(URL);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(),
                    url.getHost(), url.getPort(), url.getPath(),
                    url.getQuery(), url.getRef());
            httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();
            //Si no es, retorna un string json
            jsonString = EntityUtils.toString(entity, "UTF-8");

            if (entity == null)
                return "";
        } catch (Exception e) {
            return "";
        }

        return jsonString;
    }


    /**
     * Metodo POST usando StringEntity y formato JSON
     *
     * @param server url de servidor
     * @param params datos en formato JSON
     * @return respuesta del servidor
     */
    public String makeHttpRequestPostEntity(String server, String params) {
        try {

            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 15000;
            HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
            HttpConnectionParams
                    .setSoTimeout(httpParameters, timeoutConnection);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(server);
            StringEntity entity = new StringEntity(params, HTTP.UTF_8);
            //entity.setContentEncoding(HTTP.UTF_8);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.addHeader("Accept", "application/json");


            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            //Si no es, retorna un string json
            jsonString = EntityUtils.toString(httpEntity, "UTF-8");


            // is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;

    }

}
