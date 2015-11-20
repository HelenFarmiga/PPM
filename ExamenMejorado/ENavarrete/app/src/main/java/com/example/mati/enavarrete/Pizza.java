package com.example.mati.enavarrete;

public class Pizza {
        private String nombre;
        private String descripcion;
        private String precio;
        private int imagen;


        public Pizza(String nombre, String descripcion, String precio, int imagen) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }
        public String getDescripcion() {

            return descripcion;
        }
        public String getPrecio() {
            return precio;
        }
        public int getImagen() {

            return imagen;
        }

        // SETTERS
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        public void setPrecio(String precio) {
            this.precio = precio;
        }
        public void setImagen(int imagen) {

            this.imagen = imagen;
        }

    }


