package com.example.mati.formularioenavarrete;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaPantalla extends AppCompatActivity {
    int numImagen;
    TextView nombre;
    TextView precio;
    TextView sesion;
    TextView unidades;
    TextView envio;
    TextView extra;
    ImageView Imagen;
    int idUsuario;
    int idRecibido;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda);

        nombre = (TextView) findViewById(R.id.TPeli);
        extra = (TextView) findViewById(R.id.TExtra);
        unidades = (TextView) findViewById(R.id.tUnidades);
        precio = (TextView) findViewById(R.id.tvPrecio);
        Imagen = (ImageView) findViewById(R.id.ivPeli);
        DigitalClock Reloj = (DigitalClock) findViewById(R.id.reloj);
        envio = (TextView) findViewById(R.id.TEnvio);
        sesion = (TextView) findViewById(R.id.TSesion);

        Button BD = (Button) findViewById(R.id.btDB);

        Bundle bundle = getIntent().getExtras();

        idRecibido = bundle.getInt("IDUSER");

        String NNombre = bundle.getString("Nombre");
        Double TPrecio = bundle.getDouble("PRECIO");
        String ex = bundle.getString("Extra");
        int img = bundle.getInt("IMAGEN");
        String Ssesion = bundle.getString("Hora");
        int UUnidades = bundle.getInt("Cantidad");
        String Eenvio = bundle.getString("Envio");
        idUsuario = bundle.getInt("IDUSER");


        nombre.setText(NNombre);
        extra.setText("Aperitivo: " + ex);
        unidades.setText("Entradas: " + UUnidades);
        precio.setText("Precio total : " + Double.toString(TPrecio) + " €");
        envio.setText("Envio: " + Eenvio);
        sesion.setText("Hora: " + Ssesion);
        Imagen.setImageResource(img);
        Reloj.setVisibility(ImageView.VISIBLE);
        BD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertP();
            }
        });
    }

    public void insertP() {
        SQLiteHelper admin = new SQLiteHelper(this, "cine.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = this.nombre.getText().toString();
        String precio = this.precio.getText().toString();
        String sesion = this.sesion.getText().toString();
        String unidades = this.unidades.getText().toString();
        String envio = this.envio.getText().toString();


        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("precio", precio);
        contentValues.put("sesion", sesion);
        contentValues.put("unidades", unidades);
        contentValues.put("envio", envio);
        contentValues.put("imagen", numImagen);
        contentValues.put("iduser", idUsuario);

        bd.insert("Pedidos", null, contentValues);
        bd.close();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.segunda_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Opc1:
                startActivity(new Intent(this, CarteleraActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}


