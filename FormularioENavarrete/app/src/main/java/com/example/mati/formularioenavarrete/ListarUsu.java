package com.example.mati.formularioenavarrete;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
class ViewHolder{
    TextView tId, tUser, tContra, tEmail, tCiudad;
}
public class ListarUsu extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        ListView list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
        CargarArray();
        Adaptador adaptador = new Adaptador(this);
        list = (ListView) findViewById(R.id.lsUser);
        list.setAdapter(adaptador);
    }
    ViewHolder holder;
    Usuario[] users;
    int dn;

    class Adaptador extends ArrayAdapter<Usuario> {
        Activity context;
        public Adaptador(Activity context) {
            super(context, R.layout.listar_usuarios, users);
            this.context = context;
        }
        public View getView(int i, View convertView, ViewGroup parent) {
            View item = convertView;
            if (item == null) {
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.listar_usuarios, null);
                holder = new ViewHolder();
                holder.tId = (TextView) item.findViewById(R.id.tId);
                holder.tUser = (TextView) item.findViewById(R.id.tUsuario);
                holder.tContra = (TextView) item.findViewById(R.id.tContra);
                holder.tEmail = (TextView)item.findViewById(R.id.tEmail);
                holder.tCiudad = (TextView)item.findViewById(R.id.tCiudad);
                item.setTag(holder);
            } else
                holder = (ViewHolder) item.getTag();
                holder.tId.setText(String.valueOf(users[i].getId()));
                holder.tUser.setText(users[i].getNombreusuario());
                holder.tContra.setText(users[i].getContra());
                holder.tEmail.setText(users[i].getEmail());
                holder.tCiudad.setText(users[i].getCiudad());
                dn = i;

                ImageButton borrarUsuario = (ImageButton)item.findViewById(R.id.borrarUsuario);
                borrarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    borrar();
                }
            });
                return (item);
            }
        }
    public void CargarArray() {
        SQLiteHelper sq = new SQLiteHelper(this, "cine.sqlite", null, 1);
        SQLiteDatabase bd = sq.getReadableDatabase();
        if (bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM usuarios", null);
            int cantidad = cursor.getCount();
            int i = 0;
            users = new Usuario[cantidad];
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String usuario = cursor.getString(1);
                    String contra = cursor.getString(2);
                    String email = cursor.getString(3);
                    String ciu  = cursor.getString(4);
                    users[i] = new Usuario(id, usuario, contra,email,ciu);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
    }
    public void verToast(String texto) {
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }
    public void Recargar() {
        Intent home_intent = new Intent(getApplicationContext(), ListarUsu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
    public void borrar(){
        SQLiteHelper sq = new SQLiteHelper(this, "cine.sqlite", null, 1);
        final SQLiteDatabase bd = sq.getReadableDatabase();
        bd.execSQL("DELETE FROM usuarios WHERE id = " + users[dn].getId());
        verToast("Se ha borrado el user");
        Recargar();
    }

}


