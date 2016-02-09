package com.example.mati.formularioenavarrete;

public class Usuario {

    private int id;
    String nombreusuario;
    String contra;
    String email;
    String ciudad;



    public Usuario(int id, String nombreusuario, String contra,String email, String ciudad ) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.contra = contra;
        this.email=email;
        this.ciudad= ciudad;


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
    public String getEmail(){return email;}
    public String getCiudad(){
        return ciudad;
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
    public void setEmail(String email){this.email= email;}
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
}