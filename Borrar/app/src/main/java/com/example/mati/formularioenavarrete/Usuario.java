package com.example.mati.formularioenavarrete;

public class Usuario {

    private int id;
    String nombreusuario;
    String contra;



    public Usuario(int id, String nombreusuario, String contra) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.contra = contra;


    }

    public int getId() {
        return id;
    }
    public String getNombreusuario() {
        return nombreusuario;
    }
    public String getContra() {
        return contra;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }
    public void setContra(String contra) {
        this.contra = contra;
    }
}