package com.example.mati.formularioenavarrete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.TextView;

public class SegundaPantalla extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda);
        final TextView nombre = (TextView) findViewById(R.id.TPeli);
        final TextView extra = (TextView) findViewById(R.id.TExtra);
        final TextView tUnidades = (TextView) findViewById(R.id.tUnidades);
        final TextView tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        final ImageView ivImagen = (ImageView) findViewById(R.id.ivPeli);
        final AnalogClock Reloj = (AnalogClock)findViewById(R.id.reloj);
        final TextView tvEnvio = (TextView)findViewById(R.id.TEnvio);


        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("Nombre");
        Double precio = bundle.getDouble("PRECIO");
        String ex = bundle.getString("Extra");
        int img = bundle.getInt("IMAGEN");
        Double unidades = bundle.getDouble("Cantidad");
        String envio = bundle.getString("Envio");




        nombre.setText( name);
        extra.setText("   Aperitivo " + ex);
        tUnidades.setText("Entradas: " +Double.toString(unidades));
        tvPrecio.setText("Precio total : " + Double.toString(precio) + " €");
        tvEnvio.setText("Envio: " + envio);
        ivImagen.setImageResource(img);
        Reloj.setVisibility(ImageView.VISIBLE);

    }
}

