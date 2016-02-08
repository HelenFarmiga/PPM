package mati.example.com.prueba.contentprovider2;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentProvider2 extends AppCompatActivity {

    Button btnInsertar;
    Button btnConsultar;
    Button btnEliminar;
    Button btnLlamadas;
    TextView txtResultados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResultados = (TextView) findViewById(R.id.TxtResultados);
        btnConsultar = (Button) findViewById(R.id.BtnConsultar);
        btnInsertar = (Button) findViewById(R.id.BtnInsertar);
        btnEliminar = (Button) findViewById(R.id.BtnEliminar);
        btnLlamadas = (Button) findViewById(R.id.BtnLlamadas);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String[] proyeccion = new String[]{"_id", "usuario", "password", "email"};

                String uri = "content://mati.example.com.prueba.contentprovider2/usuario";
                Uri usuariosUri = Uri.parse(uri);
                Log.e("ContentProvider2", "LLEGAMOS");

                ContentResolver cr = getContentResolver();

                Cursor cur = cr.query(usuariosUri, proyeccion, null, null, null);

                if (cur.moveToFirst()) {
                    String usuario;
                    String password;
                    String email;

                    int colUsuario = cur.getColumnIndex("usuario");
                    int colPassword = cur.getColumnIndex("password");
                    int colEmail = cur.getColumnIndex("email");

                    txtResultados.setText("");

                    do {
                        usuario = cur.getString(colUsuario);
                        password = cur.getString(colPassword);
                        email = cur.getString(colEmail);

                        txtResultados.append(usuario + " - " + password + " - " + email + "\n");

                    } while (cur.moveToNext());
                }
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ContentValues values = new ContentValues();
                values.put("usuario", "UsuarioN");
                values.put("password", "PasswordXXX");
                values.put("email", "nuevo@cefire.com");

                String uri = "content://mati.example.com.prueba.contentprovider2/usuario";
                Uri usuariosUri = Uri.parse(uri);
                ContentResolver cr = getContentResolver();

                cr.insert(usuariosUri, values);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String uri = "content://mati.example.com.prueba.contentprovider2/usuario";
                Uri usuariosUri = Uri.parse(uri);
                ContentResolver cr = getContentResolver();

                cr.delete(usuariosUri, "usuario" + " = 'UsuarioN'", null);
            }
        });

        btnLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String[] proyeccion = new String[]{CallLog.Calls.TYPE, CallLog.Calls.NUMBER};

                Uri llamadasUri = CallLog.Calls.CONTENT_URI;
                ContentResolver cr = getContentResolver();
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)) {
                    return;
                }
                Cursor cur = cr.query(llamadasUri, proyeccion, null, null, null);


                if (cur.moveToFirst()) {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;

                    int colTipo = cur.getColumnIndex(CallLog.Calls.TYPE);
                    int colTelefono = cur.getColumnIndex(CallLog.Calls.NUMBER);

                    txtResultados.setText("");

                    do {

                        tipo = cur.getInt(colTipo);
                        telefono = cur.getString(colTelefono);

                        if(tipo == CallLog.Calls.INCOMING_TYPE)
                            tipoLlamada = "ENTRADA";
                        else if(tipo == CallLog.Calls.OUTGOING_TYPE)
                            tipoLlamada = "SALIDA";
                        else if(tipo == CallLog.Calls.MISSED_TYPE)
                            tipoLlamada = "PERDIDA";

                        txtResultados.append(tipoLlamada + " - " + telefono + "\n");

                    } while (cur.moveToNext());
                }
            }
        });
    }
}