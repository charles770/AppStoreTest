package com.avellacorp.appstoretest.entities;

/**
 * Esta clase es usada como entidad de un objeto Aplicacion
 *
 * @author Carlosas
 */
public class Aplicacion {

    private String Id;
    private String Nombre;
    private String Resumen;
    private String UrlImagen;
    private String TipoMoneda;
    private String Valor;
    private String TipoAplicacion;
    private String Derechos;
    private String Titulo;
    private String UrlAplicacion;
    private String Artista;
    private String UrlArtista;
    private String FechaCreacion;
    private String IdCategoria;

    public Aplicacion() {
    }

    public Aplicacion(String artista, String derechos, String fechaCreacion, String id,
                      String idCategoria, String nombre, String resumen, String tipoAplicacion,
                      String tipoMoneda, String titulo, String urlAplicacion, String urlArtista,
                      String urlImagen, String valor) {
        Artista = artista;
        Derechos = derechos;
        FechaCreacion = fechaCreacion;
        Id = id;
        IdCategoria = idCategoria;
        Nombre = nombre;
        Resumen = resumen;
        TipoAplicacion = tipoAplicacion;
        TipoMoneda = tipoMoneda;
        Titulo = titulo;
        UrlAplicacion = urlAplicacion;
        UrlArtista = urlArtista;
        UrlImagen = urlImagen;
        Valor = valor;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String artista) {
        Artista = artista;
    }

    public String getDerechos() {
        return Derechos;
    }

    public void setDerechos(String derechos) {
        Derechos = derechos;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getResumen() {
        return Resumen;
    }

    public void setResumen(String resumen) {
        Resumen = resumen;
    }

    public String getTipoAplicacion() {
        return TipoAplicacion;
    }

    public void setTipoAplicacion(String tipoAplicacion) {
        TipoAplicacion = tipoAplicacion;
    }

    public String getTipoMoneda() {
        return TipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        TipoMoneda = tipoMoneda;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getUrlAplicacion() {
        return UrlAplicacion;
    }

    public void setUrlAplicacion(String urlAplicacion) {
        UrlAplicacion = urlAplicacion;
    }

    public String getUrlArtista() {
        return UrlArtista;
    }

    public void setUrlArtista(String urlArtista) {
        UrlArtista = urlArtista;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        UrlImagen = urlImagen;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}
