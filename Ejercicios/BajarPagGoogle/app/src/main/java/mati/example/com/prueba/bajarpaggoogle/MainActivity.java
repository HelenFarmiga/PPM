package mati.example.com.prueba.bajarpaggoogle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText pag;
    TextView aux;
    String link = "http://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pag = (EditText)findViewById(R.id.url);
        aux = (TextView)findViewById(R.id.aux);
    }
    public void download(View view){
        String url = "http://" + pag.getText().toString();
        Descargar down = new Descargar(this, aux);
        down.execute(url);
    }
}










