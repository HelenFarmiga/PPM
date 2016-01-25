package com.example.mati.formularioenavarrete;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    EditText usuario, clave;
    Button iniciar;
    TextView enlaceRegistro;

    Usuario[] listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity);
        this.setTitle("Iniciar sesion");

        usuario = (EditText)findViewById(R.id.etUsuario);
        clave = (EditText)findViewById(R.id.etContrasena);
        iniciar = (Button)findViewById(R.id.btIniciar);
        enlaceRegistro = (TextView)findViewById(R.id.tvEnlaceRegistro);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        enlaceRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
            }
        });
    }


    public void iniciarSesion() {

        String usu = usuario.getText().toString();
        String con = clave.getText().toString();

        Usuario[] usuarios = listaUsuarios();

        boolean existe = false;

        for(int i=0; i<usuarios.length; i++){
            if(usuarios[i].getUsuario().equals(usu) && usuarios[i].getClave().equals(con))
                existe = true;
        }


        if (usu.equals("admin") && usu.equals("admin")) {
            Toast.makeText(this, "INICIANDO COMO ADMIN", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), adminisActivity.class);
            startActivity(intent);
        }
        else {

            if (validar() && existe) {
                iniciar.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(Inicio.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autentificando...");
                progressDialog.show();

                // TODO: propias comprobaciones

                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            } else
                Toast.makeText(this, "Datos inexistentes/erroneos", Toast.LENGTH_SHORT).show();
        }



    }


    public boolean validar() {
        boolean valido = true;

        String usu = usuario.getText().toString();
        String con = clave.getText().toString();

        if (usu.isEmpty()) {
            usuario.setError("Introduce un usuario");
            valido = false;
        }
        else
            usuario.setError(null);

        if (con.isEmpty() || con.length() < 4 || con.length() > 10) {
            clave.setError("Entre 4-10 caracteres alfanuméricos");
            valido = false;
        }
        else
            clave.setError(null);

        return valido;
    }

    public Usuario[] listaUsuarios() {

        SQLiteHelper usuariosSqliteHelper = new SQLiteHelper(this, "cine.sqlite", null, 1);
        SQLiteDatabase bd = usuariosSqliteHelper.getReadableDatabase();

        if(bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM usuarios", null);
            int cantidad = cursor.getCount();
            int i = 0;
            listaUsuarios = new Usuario[cantidad];

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String usuario = cursor.getString(1); // 0 = id
                    String clave   = cursor.getString(2);

                    listaUsuarios[i] = new Usuario(id, usuario, clave);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }

        return listaUsuarios;
    }
}