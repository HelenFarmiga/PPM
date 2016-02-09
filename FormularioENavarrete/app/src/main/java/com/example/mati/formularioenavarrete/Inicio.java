package com.example.mati.formularioenavarrete;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {
    EditText usuario;
    EditText contra;
    Button registro;
    Button iniciar;
    Usuario[] listaUsuarios;
    int id;

    String usersel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity);
        usuario = (EditText) findViewById(R.id.tUsuario);
        registro = (Button) findViewById(R.id.btnRegistrar);
        contra = (EditText) findViewById(R.id.tContra);
        iniciar = (Button) findViewById(R.id.btnIngresar);


        iniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
            }
        });
    }
    public void login() {
        boolean exist = false;
        String user = usuario.getText().toString();
        String con = contra.getText().toString();




        Usuario[] users = listaUsuarios();

        for (Usuario userdefecto : users) {
            if (userdefecto.getNombreusuario().equals(user) && userdefecto.getContra().equals(con)){
                exist = true;
                id = userdefecto.getId();

            }
        }
        if (checkUsuario() && exist) {
            iniciar.setEnabled(false);
            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putInt("IDUSER", id);
                    bundle.putString("NOMUSUARIO", usersel);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        } else
            Toast.makeText(this, "No existe el usuario o la contraseña no es la correcta o la ciudad no es correcta", Toast.LENGTH_SHORT).show();
    }
//Método que sirve para registrar un usuario y ver si deja los campos incompletos o en blanco
    public boolean checkUsuario() {
        boolean checkeado = true;
        String user = usuario.getText().toString();
        String con = contra.getText().toString();

        if(id==1){
            verToast("Bienvenido Administrador");
        }
        if (con.isEmpty() || con.length() < 4) {
            Toast.makeText(getApplicationContext(), "Debe contener como mínimo 4 carácteres", Toast.LENGTH_SHORT).show();
            checkeado = false;
        } else
            contra.setError(null);
        if (user.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nombre vacío", Toast.LENGTH_SHORT).show();
            checkeado = false;
        } else
            usuario.setError(null);
        return checkeado;
    }

    public Usuario[] listaUsuarios() {
        SQLiteHelper usuariosSqliteHelper = new SQLiteHelper(this, "cine.sqlite", null, 1);
        SQLiteDatabase basedatos = usuariosSqliteHelper.getReadableDatabase();
        if (basedatos != null) {
            Cursor cursor = basedatos.rawQuery("SELECT * FROM usuarios", null);
            int cantidad = cursor.getCount();
            int i = 0;
            listaUsuarios = new Usuario[cantidad];
            //utilizamos el cursor
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String usuario = cursor.getString(1);
                    String contra = cursor.getString(2);
                    String email = cursor.getString(3);
                    String ciudad = cursor.getString(4);
                    //los agrega a la listaUsuarios
                    listaUsuarios[i] = new Usuario(id, usuario, contra,email, ciudad);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            basedatos.close();
        }
//se debe cerrar el cursor y devolver la lista de usuarios
        return listaUsuarios;
    }
    public void verToast(String texto) {
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }

        }

