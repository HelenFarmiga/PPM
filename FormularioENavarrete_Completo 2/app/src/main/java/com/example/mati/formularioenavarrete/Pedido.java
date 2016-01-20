package com.example.mati.formularioenavarrete;

public class Pedido {

    private int id;
    private String Nombre;
    private Double Precio;
    private String sesion;
    private int unidades;
    private String envio;
    private int imagen;

    public Pedido(){}

    public Pedido(int id, String Nombre, Double Precio, String sesion, int unidades, int imagen, String envio) {
        this.id = id;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.sesion = sesion;
        this.unidades = unidades;
        this.envio = envio;
        this.imagen = imagen;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return Nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public String getSesion() {
        return sesion;
    }
    public int getUnidades() {
        return unidades;
    }
    public int getImagen() {
        return imagen;
    }
    public String getEnvio(){
        return envio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }


    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setEnvio(String envio){
        this.envio = envio;
    }
}