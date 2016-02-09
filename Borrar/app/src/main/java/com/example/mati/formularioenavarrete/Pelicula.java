package com.example.mati.formularioenavarrete;

import android.support.v7.app.AppCompatActivity;

public class Pelicula extends AppCompatActivity {
        private String nombre;
        private String precio;
        private String genero;
        private String horario;
        private int imagen;


        public Pelicula(String nombre, String genero, String precio, String horario, int imagen) {
            this.nombre = nombre;
            this.precio = precio;
            this.genero = genero;
            this.horario = horario;
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }
    public String getPrecio() {
        return precio;
    }
        public String getGenero() {
            return genero;
        }
        public String getHorario() {
            return horario;
        }
        public int getImagen() {
            return imagen;
        }


        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
        public void setGenero(String genero) {
            this.genero = genero;
        }
        public void setHorario(String horario) {
            this.horario = horario;
        }
        public void setImagen(int imagen) {

            this.imagen = imagen;
        }

    }