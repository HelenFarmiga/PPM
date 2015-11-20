package com.example.mati.cambiaractividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Activity2 extends AppCompatActivity {
    Button pantalla1;
    TextView texto;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        texto = (TextView) findViewById(R.id.txP);
        pantalla1 = (Button) findViewById(R.id.btVolver);

        pantalla1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                String mensaje = "Volvemos a la pantalla 1, desde Pantalla 2";
                bundle.putString("text", mensaje);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
