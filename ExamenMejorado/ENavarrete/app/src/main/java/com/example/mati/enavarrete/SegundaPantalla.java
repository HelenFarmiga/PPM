package com.example.mati.enavarrete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SegundaPantalla extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.segunda_activity);
            final TextView nombre = (TextView)findViewById(R.id.TPizza);
            final TextView TipoPrecio = (TextView)findViewById(R.id.TPrecioPizza);
            final TextView tUnidades = (TextView)findViewById(R.id.tUnidades);
            final TextView tEnvio = (TextView)findViewById(R.id.TEnvio);
            final TextView tvPrecio = (TextView)findViewById(R.id.tvPrecio);
            final ImageView ivImagen = (ImageView)findViewById(R.id.ivPizza);


            Bundle  bundle = getIntent().getExtras();

            String name= bundle.getString("Nombre");
            int tprecio = bundle.getInt("TipoPrecio");
            int precio = bundle.getInt("PRECIO");
            int img = bundle.getInt("IMAGEN");
             int unidades = bundle.getInt("Cantidad");
            Boolean envio = bundle.getBoolean("ENVIO");

            nombre.setText("Nombre: "  + name);
            if(envio == true)
                tEnvio.setText("A domicilio");
            if(envio==false)
                tEnvio.setText("Local");
            TipoPrecio.setText(("Tipo pizza precio: " + Integer.toString(tprecio)+ "€"));
            tUnidades.setText("Unidades: " + Integer.toString(unidades));
            tvPrecio.setText("Precio: " + Integer.toString(precio) + " €");
            ivImagen.setImageResource(img);

        }
    }


