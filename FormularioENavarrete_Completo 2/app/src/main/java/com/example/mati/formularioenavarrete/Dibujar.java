package com.example.mati.formularioenavarrete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


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
                Intent sonido = new Intent(this, Sonido.class);
                startActivity(sonido);
                return true;
            case R.id.ctx2:
                Intent info = new Intent(this, Info.class);
                startActivity(info);
                return true;
            default:
                return true;
        }
    }
    }

