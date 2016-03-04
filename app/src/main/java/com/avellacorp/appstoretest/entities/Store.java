package com.avellacorp.appstoretest.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Esta clase es usada como entidad de un objeto Store
 *
 * @author Carlosas
 */
public class Store {

    private String Nombre;
    private String Derechos;
    private String Fecha;
    private String Autor;

    public Store() {
    }

    public Store(String autor, String derechos, String fecha, String nombre) {
        Autor = autor;
        Derechos = derechos;
        Fecha = fecha;
        Nombre = nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getDerechos() {
        return Derechos;
    }

    public void setDerechos(String derechos) {
        Derechos = derechos;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
