package com.avellacorp.appstoretest.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Esta clase es usada como entidad de un objeto Categoria
 *
 * @author Carlosas
 */
public class Categoria {


    private String Id;
    private String Nombre;
    private String Url;

    public Categoria() {
    }

    public Categoria(String id, String nombre, String url) {
        Id = id;
        Nombre = nombre;
        Url = url;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
