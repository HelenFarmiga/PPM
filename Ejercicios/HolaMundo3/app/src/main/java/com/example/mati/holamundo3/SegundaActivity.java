package com.example.mati.holamundo3;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);


        final TextView otroSaludo= (TextView)findViewById(R.id.tv1);
        final ImageButton botonVolver = (ImageButton)findViewById(R.id.bt1);


        // recogemos el bundle del intent
        Bundle  bundle = getIntent().getExtras();

        // metemos en el textview el texto de bundle
        otroSaludo.setText(bundle.getString("TEXTO"));

        final String completarSaludo = bundle.getString("TEXTO");

        // control de click del botonVolver
        botonVolver.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                // creamos el string con texto + el mensaje de bundle
                String mensajeDevuelto= "Devuelvo a Principal " + completarSaludo;
                // metemos en el bundle el string con nombre devuelto
                bundle.putString("DEVUELTO", mensajeDevuelto);
                // metemos en el intent nuestro bundle
                intent.putExtras(bundle);
                // ponemos RESULT_OK al intent para que la primera actividad sepa que recoge el mensaje de la segunda actividad
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}