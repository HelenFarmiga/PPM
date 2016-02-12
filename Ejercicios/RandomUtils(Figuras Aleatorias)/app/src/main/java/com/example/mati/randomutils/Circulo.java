package com.example.mati.randomutils;

import android.graphics.Color;


public class Circulo extends Figura {
    protected double radio;

    //Constructor con el color y radio por defecto
    public Circulo() {
        super(Color.YELLOW, "circulo");
        radio = 20.0;
    }
    public Circulo(int color, double radio) {
        super(color, "circulo");
        this.radio = radio;
    }
//Setters y getters
    public double getRadio() {
        return this.radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double area() {
        return Math.PI * radio * radio;
    }

    public String toString(){
        return "Circulo " + color + " de radio " + radio;
    }
}
