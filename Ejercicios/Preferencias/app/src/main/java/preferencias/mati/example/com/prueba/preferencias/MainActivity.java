package preferencias.mati.example.com.prueba.preferencias;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button btPreferencias;
    private Button btObtenerPreferencias;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPreferencias = (Button)findViewById(R.id.BtPreferen);
        btObtenerPreferencias = (Button)findViewById(R.id.BtObtenerPreferen);

        btPreferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        PantallaOpciones.class));
            }	});

        btObtenerPreferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Log.i("", "OpciÛn 1: " + pref.getBoolean("opcion1", false));
                Log.i("", "OpciÛn 2: " + pref.getString("opcion2", ""));
                Log.i("", "OpciÛn 3: " + pref.getString("opcion3", ""));
            } });
    }
}


