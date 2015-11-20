package com.example.mati.cambiaractividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    TextView texto;
    public static int COD_RESPUESTA = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
            texto = (TextView) findViewById(R.id.txP);
            final Button pantalla2 = (Button) findViewById(R.id.bt2);
            final Button pantalla3 = (Button) findViewById(R.id.bt3);
            pantalla2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Utilizando el Intent pueda abrir la pantalla 2
                    Intent intent = new Intent(Activity1.this, Activity2.class);
                    startActivityForResult(intent, COD_RESPUESTA);

                }
            });
            pantalla3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Utilizando el Intent pueda abrir la pantalla 3
                    Intent intent = new Intent(Activity1.this, Activity3.class);
                    startActivityForResult(intent, COD_RESPUESTA);
                }
            });
        }
        public void onActivityResult(int cod_resp, int cod_result,Intent intent){
            if (cod_result== RESULT_OK) {
                Bundle bundle = intent.getExtras();
                texto.setText(bundle.getString("TEXTO"));
            }
        }
    }
