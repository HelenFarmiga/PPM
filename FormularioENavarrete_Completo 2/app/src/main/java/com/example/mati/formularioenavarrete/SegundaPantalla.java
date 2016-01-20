package com.example.mati.formularioenavarrete;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        final TextView tvSesion =(TextView)findViewById(R.id.TSesion);

        final Button BD = (Button)findViewById(R.id.btDB);
        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("Nombre");
        Double precio = bundle.getDouble("PRECIO");
        String ex= bundle.getString("Extra");
        int img = bundle.getInt("IMAGEN");
        String sesion = bundle.getString("Hora");
        int unidades = bundle.getInt("Cantidad");
        String envio = bundle.getString("Envio");


        nombre.setText( name);
        extra.setText(" Sesión del " + ex);
        tUnidades.setText("Entradas: " +unidades);
        tvPrecio.setText("Precio total : " + Double.toString(precio) + " €");
        tvEnvio.setText("Envio: " + envio);
        tvSesion.setText("Hora: " +sesion);
        ivImagen.setImageResource(img);
        Reloj.setVisibility(ImageView.VISIBLE);

        BD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarPedido();
            }
        });
    }
    public void insertarPedido(){

        SQLiteHelper admin = new SQLiteHelper(this,"pedidos.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Nombre     = this.Nombre.getText()    .toString();
        String tamano     = this.tamano.getText()    .toString();
        String aperativo  = this.aperativo.getText() .toString();
        String unidades   = this.unidades.getText()  .toString();
        String precio     = this.precio.getText()    .toString();

        ContentValues contentValues = new ContentValues();

        contentValues.put("bebida"    , bebida);
        contentValues.put("tamano"    , tamano);
        contentValues.put("aperativo" , aperativo);
        contentValues.put("unidades"  , unidades);
        contentValues.put("precio"    , precio);
        contentValues.put("imagen"    , numImagen);

        bd.insert("Pedidos", null, contentValues);
        bd.close();

        Toast.makeText(this, "Pedido guardado correctamente", Toast.LENGTH_SHORT).show();
    }
}


    }








