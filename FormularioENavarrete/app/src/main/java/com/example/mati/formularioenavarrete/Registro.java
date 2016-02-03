package com.example.mati.formularioenavarrete;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    Button nuevo, volverIniciar;
    EditText usuario, contra;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        nuevo = (Button)findViewById(R.id.btRegistrarse);
        volverIniciar = (Button)findViewById(R.id.btVuelve);
        usuario = (EditText)findViewById(R.id.etUsuario2);
        contra = (EditText)findViewById(R.id.etContrasena2);


        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });
        volverIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void registrarse() {
        if (!checkear()) {
            RegistroFail();
            return;
        }
        nuevo.setEnabled(false);
        String nom = usuario.getText().toString();
        String con = contra.getText().toString();

        InsertU(nom, con);
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                RegistroOk();
            }
        }, 4000);
    }


    public void RegistroOk() {
        nuevo.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }
    public void RegistroFail() {
        nuevo.setEnabled(true);
    }


    public boolean checkear() {
        boolean valido = true;
        String nom = usuario.getText().toString();
        String cla = this.contra.getText().toString();
        if(nom.equals(nom)){
            usuario.setError("Nombre de usuario ocupado");
        }
        if (nom.isEmpty() || nom.length() < 4) {
            usuario.setError("Minimo 4 carácteres alfanuméricos");
            valido = false;
        }
        else
            usuario.setError(null);

        if (cla.isEmpty() || contra.length() < 4) {
            contra.setError("Mínimo 4 carácteres");
            valido = false;
        }
        else
            contra.setError(null);

        return valido;
    }


//Métodoo para insertar usuarios
    public void InsertU(String nombreusario, String contra){
        SQLiteHelper admin = new SQLiteHelper(this,"cine.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreusuario" , nombreusario);
        contentValues.put("contra"   , contra);

        bd.insert("usuarios", null, contentValues);
        bd.close();
        Toast.makeText(this, "Su usuario ha sido creado correctamente", Toast.LENGTH_SHORT).show();
    }

}
