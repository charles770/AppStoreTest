package com.avellacorp.appstoretest.entities;

/**
 * Esta clase es usada como entidad de un objeto Configuracion
 * 
 * @author Carlosas
 * 
 */
public class Configuracion {

    private String Parametro;
	private String Valor="";

    public Configuracion() {
    }

    public Configuracion(String parametro, String valor) {
        Parametro = parametro;
        Valor = valor;
    }

    public String getParametro() {
        return Parametro;
    }

    public void setParametro(String parametro) {
        Parametro = parametro;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}
