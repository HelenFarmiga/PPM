package com.example.mati.formularioenavarrete;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText usuario, clave;
    Button crearCuenta;
    TextView enlaceIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        this.setTitle("Registrarse");

        usuario = (EditText)findViewById(R.id.etUsuario2);
        clave = (EditText)findViewById(R.id.etContrasena2);
        crearCuenta = (Button)findViewById(R.id.btRegistrarse);
        enlaceIniciarSesion = (TextView)findViewById(R.id.tvEnlaceIniciar);

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });

        enlaceIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void registrarse() {

        if (!validar()) {
            registroFallado();
            return;
        }

        crearCuenta.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Registro.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creando cuenta...");
        progressDialog.show();

        String nom = usuario.getText().toString();
        String cla = clave.getText().toString();

        // TODO: propias validaciones
        insertarUsuario(nom, cla);



        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                registroCorrecto();
                progressDialog.dismiss();
            }
        }, 3000);
    }


    public void registroCorrecto() {
        crearCuenta.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void registroFallado() {
        crearCuenta.setEnabled(true);
    }


    public boolean validar() {

        boolean valido = true;

        String nom = usuario.getText().toString();
        String cla = this.clave.getText().toString();

        if (nom.isEmpty() || nom.length() < 4) {
            usuario.setError("Minimo 4 carácteres alfanuméricos");
            valido = false;
        }
        else
            usuario.setError(null);

        if (cla.isEmpty() || clave.length() < 4) {
            clave.setError("Minimo 4 carácteres");
            valido = false;
        }
        else
            clave.setError(null);

        return valido;
    }


    public void insertarUsuario(String usuario, String clave){

        SQLiteHelper admin = new SQLiteHelper(this,"cine.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("usuario" , usuario);
        contentValues.put("clave"   , clave);

        bd.insert("usuarios", null, contentValues);
        bd.close();

        Toast.makeText(this, "Usuario creado Correctamente", Toast.LENGTH_SHORT).show();
    }

}
