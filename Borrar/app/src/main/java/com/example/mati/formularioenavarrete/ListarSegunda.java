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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListarSegunda extends AppCompatActivity {
    int idUsuario;
    class ViewHolder{
        TextView tvId, tvNombre, tvPrecio, tvSesion, tvUnidades, tvEnvio;
        ImageView bBorrar;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Bundle b = getIntent().getExtras();
        idUsuario =b.getInt("ID");
        Cargar();
        Adaptador adaptador = new Adaptador(this);
        lista = (ListView)findViewById(R.id.listaP);
        lista.setAdapter(adaptador);
    }
    SQLiteHelper sqliteHelper = new SQLiteHelper(this, "cine.sqlite", null, 1);
    ViewHolder holder;
    Pedido[] listape;
    int dn;
    public ListView lista;

    class Adaptador extends ArrayAdapter<Pedido> {
        Activity context;

        public Adaptador(Activity context) {
            super(context, R.layout.lista_segunda, listape);
            this.context = context;
        }
        public View getView(int i, View convertView, ViewGroup parent) {
            View item = convertView;
            if(item==null) {
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.lista_segunda, null);
                holder = new ViewHolder();
                holder.tvId = (TextView) item.findViewById(R.id.tId);
                holder.tvNombre = (TextView) item.findViewById(R.id.tNombre);
                holder.tvPrecio = (TextView) item.findViewById(R.id.tPrecio);
                holder.tvSesion = (TextView) item.findViewById(R.id.tSesion);
                holder.tvUnidades = (TextView) item.findViewById(R.id.tUnidades);
                holder.tvEnvio = (TextView) item.findViewById(R.id.tEnvio);
                holder.bBorrar = (ImageButton) item.findViewById(R.id.borrarPedido);
                item.setTag(holder);
            }
            else
                holder = (ViewHolder)item.getTag();
                holder.tvId.setText("ID: " + listape[i].getId());
                holder.tvNombre.setText(listape[i].getNombre());
                holder.tvPrecio.setText(listape[i].getPrecio());
                holder.tvSesion.setText(listape[i].getSesion());
                holder.tvUnidades.setText(listape[i].getUnidades());
                holder.tvEnvio.setText(listape[i].getEnvio());
                holder.tvPrecio.setText(listape[i].getPrecio());
                holder.bBorrar.setTag(String.valueOf(listape[i].getId()));
                dn = i;
                holder.bBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   borrarPedido();
                }
            });
                return (item);
                }
         }
    public void Cargar() {
        SQLiteHelper sqliteHelper = new SQLiteHelper(this, "cine.sqlite", null, 1);
        SQLiteDatabase bd = sqliteHelper.getReadableDatabase();
        if(bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM Pedidos p WHERE p.iduser= "+ idUsuario, null);
            int cantidad = cursor.getCount();
            int i = 0;
            listape = new Pedido[cantidad];
            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String nombre = cursor.getString(1);
                    String precio = cursor.getString(2);
                    String sesion = cursor.getString(3);
                    String unidades = cursor.getString(4);
                    String envio = cursor.getString(5);
                    int imagen = cursor.getInt(6);

                    listape[i] = new Pedido(id,nombre,precio,sesion,unidades,envio,imagen);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
    }
    public void verToast(String texto){
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }
    public void recargarLista() {
        Intent home_intent = new Intent(getApplicationContext(), ListarSegunda.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle b= new Bundle();
        b.putInt("ID",idUsuario);
        home_intent.putExtras(b);
        startActivity(home_intent);
    }
    public void borrarPedido(){
        final SQLiteDatabase bd = sqliteHelper.getReadableDatabase();
        bd.execSQL("DELETE FROM Pedidos WHERE id = " + listape[dn].getId());
        verToast("El pedido se ha borrado");
        recargarLista();
    }
}