package com.example.mati.formularioenavarrete;

import android.app.Activity;
import android.os.Bundle;


public class Dibujar extends Activity {
    private static Dibujando AreaDibujo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujo);
        AreaDibujo = (Dibujando) findViewById(R.id.drawing_area);
    }

    }

