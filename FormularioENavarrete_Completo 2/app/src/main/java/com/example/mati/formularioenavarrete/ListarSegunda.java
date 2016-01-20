package com.example.mati.formularioenavarrete;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListarSegunda  extends AppCompatActivity {

    Pedido[] pedidos;
    public ListView lista;

    ViewHolder holder;

    class Adaptador extends ArrayAdapter<Pedido> {
        Activity context;

        public Adaptador(Activity context) {
            super(context, R.layout.lista_segunda, pedidos);
            this.context = context;
        }

        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;

            if(item==null) {
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.lista_segunda, null);

                holder = new ViewHolder();
            }
                holder.tvId = (TextView) item.findViewById(R.id.tId);
                holder.tvNombre = (TextView) item.findViewById(R.id.tNombre);
                holder.tvPrecio = (TextView) item.findViewById(R.id.tPrecio);
                holder.tvSesion = (TextView) item.findViewById(R.id.TSesion);
                holder.tvUnidades = (TextView) item.findViewById(R.id.tUnidades);
                holder.tvEnvio = (TextView) item.findViewById(R.id.tPrecio);
                holder.ivImagen = (ImageView) item.findViewById(R.id.iImagen);

                item.setTag(holder);
            }
            else
                holder = (ViewHolder)item.getTag();

            holder.tvId.setText("ID: " + pedidos[i].getId());
            holder.tvBebida.setText(pedidos[i].getBebida());
            holder.tvTamano.setText(pedidos[i].getTamano());
            holder.tvAperativo.setText(pedidos[i].getAperativo());
            holder.tvUnidades.setText(pedidos[i].getUnidades());
            holder.tvPrecio.setText(pedidos[i].getPrecio());

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                holder.ivImagen.setBackground(getDrawable(pedidos[i].getImagen()));
            else
                holder.ivImagen.setBackgroundDrawable(getResources().getDrawable(pedidos[i].getImagen()));

            return (item);
        }
    }

    public void refrescar(){
        Intent refresh = new Intent(this, ListarActivity.class);
        startActivity(refresh);
        this.finish();
    }

    public void llenarArray() {

        SQLiteHelper sqliteHelper = new SQLiteHelper(this, "pedidos.sqlite", null, 1);
        SQLiteDatabase bd = sqliteHelper.getReadableDatabase();

        if(bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM Pedidos", null);
            int cantidad = cursor.getCount();
            int i = 0;
            pedidos = new Pedido[cantidad];

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String bebida = cursor.getString(1);
                    String tamano = cursor.getString(2);
                    String aperativo = cursor.getString(3);
                    String unidades = cursor.getString(4);
                    String precio = cursor.getString(5);
                    int imagen = cursor.getInt(6);

                    pedidos[i] = new Pedido(id,bebida,tamano,aperativo,unidades,precio,imagen);
                    i++;
                } while (cursor.moveToNext());
            }

            cursor.close();
            bd.close();
        }
    }

    public void mostrarToast(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        llenarArray();

        Adaptador adaptador = new Adaptador(this);
        lista = (ListView)findViewById(R.id.lsPedidos);
        lista.setAdapter(adaptador);

        final SQLiteHelper sqliteHelper = new SQLiteHelper(this, "pedidos.sqlite", null, 1);
        final SQLiteDatabase bd = sqliteHelper.getReadableDatabase();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int i, long id) {
                bd.execSQL("DELETE FROM Pedidos WHERE id = " + pedidos[i].getId());
                mostrarToast("Pedido eliminado correctamente");
                refrescar();
            }
        });
    }

    class ViewHolder{
        TextView tvId, tvNombre, tvPrecio, tvSesion, tvUnidades, tvEnvio;
        ImageView ivImagen;
    }

