package com.example.mati.formularioenavarrete;
public class Pedido {

    private int id;
    private String nombre;
    private String precio;
    private String sesion;
    private String unidades;
    private String envio;
    private int imagen;


    public Pedido(){}

    public Pedido(int id,String nombre, String precio, String sesion, String unidades,  String envio, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.sesion = sesion;
        this.unidades = unidades;
        this.envio = envio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getPrecio() {
        return precio;
    }

    public String getSesion() {
        return sesion;
    }

    public String getUnidades() {
        return unidades;
    }
    public String getEnvio(){
        return envio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public void setEnvio(String envio){
        this.envio= envio;
    }
}
