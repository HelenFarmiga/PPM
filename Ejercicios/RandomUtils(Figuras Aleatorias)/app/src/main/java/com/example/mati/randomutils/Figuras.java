package com.example.mati.randomutils;

public class Figuras {
    private String nombre;
    private int imagen;
    private Figura figura;
    public Figuras(String nom, int image, Figura fig){
        nombre = nom;
        imagen = image;
        figura = fig;
    }
    //setters y getters
    public String getNombre(){return nombre;}

    public int getImagen(){ return imagen;}

    public Figura getFigura(){ return figura;}

    public String toString(){
        return (nombre +". ");
    }


}
