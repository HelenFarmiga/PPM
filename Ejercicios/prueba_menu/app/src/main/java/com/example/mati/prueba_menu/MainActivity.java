package com.example.mati.prueba_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //OnCreate del menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Toast.makeText(this, "opción 1 pulsada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnuOpc2:
                Toast.makeText(this, "opción 2 pulsada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnuOpc3:
                Toast.makeText(this, "opción 3 pulsada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SubMnuOpc1:
                Toast.makeText(this, "opción 3.1 pulsada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SubMnuOpc2:
                Toast.makeText(this, "opción 3.2 pulsada", Toast.LENGTH_SHORT).show();
                return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }

        }
    }

