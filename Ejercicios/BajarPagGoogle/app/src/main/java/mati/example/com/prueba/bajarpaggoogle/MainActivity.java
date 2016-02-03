package mati.example.com.prueba.bajarpaggoogle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText campoUrl;
    private TextView datos;
    // String link = "http://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoUrl = (EditText)findViewById(R.id.editText1);
        datos = (TextView)findViewById(R.id.textView2);
    }

    public void download(View view){
        String url = "http://" + campoUrl.getText().toString();
        DescargarPaginaWeb descargarPaginaWeb = new DescargarPaginaWeb(this, datos);
        descargarPaginaWeb.execute(url);
    }
}
