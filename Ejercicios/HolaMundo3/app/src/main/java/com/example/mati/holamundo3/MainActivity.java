package com.example.mati.holamundo3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    // definimos las variables
    public static int COD_RESPUESTA=0;
    TextView elSaludo;

    // solo para el mensaje de dialogo
    final Context context = this;

    @Override protected void onStart() {
        super.onStart();
        MediaPlayer miMusica = MediaPlayer.create(getApplicationContext(),R.raw.invisible);

    }
    /*
    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // asignamos valor a variables
        final EditText miTexto = (EditText) findViewById(R.id.et1);
        final Button miBoton = (Button) findViewById(R.id.bt1);
        elSaludo = (TextView) findViewById(R.id.tv1);

        final Button botonToast = (Button) findViewById(R.id.btToast);
        final Button botonDialogo = (Button) findViewById(R.id.btDialog);
        final Button botonMusica = (Button) findViewById(R.id.btMusica);


        //Si regreso de otra actividad
        if (savedInstanceState != null) {
            String mensajePasado = savedInstanceState.getString("TEXTO");
            elSaludo.setText(mensajePasado);
        }


        //Borrar el texto inicial del EditText
        miTexto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if (b) miTexto.setText("");
            }
        });


        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // definimos intent con las dos clases como parámetro
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                // definimos bundle (que encapsula datos)
                Bundle bundle = new Bundle();
                // creamos el mensaje de string donde metemos un texto + el texto de editText
                String mensajePaso = "Te saludo " + miTexto.getText();
                // guardamos en el bundle el texto con nombre TEXTO, pasando la cadena
                bundle.putString("TEXTO", mensajePaso);
                // añadimos el bundle al intent
                intent.putExtras(bundle);
                //
                startActivityForResult(intent, COD_RESPUESTA);

            }

        });


        botonToast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Texto de ejemplo de Toast", Toast.LENGTH_SHORT).show();
            }
        });


        botonDialogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(context).setMessage("Texto de ejemplo de Dialogo").setPositiveButton(android.R.string.ok, null).show();

            }
        });
        final MediaPlayer miMusica = MediaPlayer.create(getApplicationContext(),R.raw.invisible);
        miMusica.start();
        botonMusica.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                    if (miMusica.isPlaying()) {
                        miMusica.pause();
                    } else {
                        miMusica.start();
                    }
            }
            });
        }

        public void onActivityResult(int cod_resp, int cod_result,Intent intent){
        if (cod_result== RESULT_OK) {
            // recogemos el bundle de la segunda actividad
            Bundle bundle = intent.getExtras();
            // mostramos el mensaje del bundle de la segunda actividad
            elSaludo.setText(bundle.getString("DEVUELTO"));
        }
    }

}