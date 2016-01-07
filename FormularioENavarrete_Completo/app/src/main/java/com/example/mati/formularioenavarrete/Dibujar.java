package com.example.mati.formularioenavarrete;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Dibujar extends Activity {
    private static Dibujando AreaDibujo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujo);
        AreaDibujo = (Dibujando) findViewById(R.id.drawing_area);
        registerForContextMenu(AreaDibujo);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dibujo_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ctx1:
                Toast.makeText(this, "Estamos aquí para tu servicio", Toast.LENGTH_LONG).show();
                return true;
            case R.id.ctx2:
                Toast.makeText(this,"¡Te esperamos de vuelta!",Toast.LENGTH_LONG).show();
                return true;
            default:
                return true;
        }
    }
    }

