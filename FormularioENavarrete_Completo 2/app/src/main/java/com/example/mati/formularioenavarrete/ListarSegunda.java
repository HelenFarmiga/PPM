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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListarSegunda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        this.setTitle("Lista de pedidos");

        llenarArray();

        Adaptador adaptador = new Adaptador(this);
        lista = (ListView)findViewById(R.id.listaP);
        lista.setAdapter(adaptador);
    }

    class ViewHolder{
        TextView tvId, tvNombre, tvPrecio, tvSesion, tvUnidades, tvEnvio;
        ImageView ivImagen;
    }

    SQLiteHelper sqliteHelper = new SQLiteHelper(this, "cine.sqlite", null, 1);

    Pedido[] pedidos;
    int num;
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

                holder.tvId = (TextView) item.findViewById(R.id.tId);
                holder.tvNombre = (TextView) item.findViewById(R.id.tNombre);
                holder.tvPrecio = (TextView) item.findViewById(R.id.tPrecio);
                holder.tvSesion = (TextView) item.findViewById(R.id.tSesion);
                holder.tvUnidades = (TextView) item.findViewById(R.id.tUnidades);
                holder.tvEnvio = (TextView) item.findViewById(R.id.tEnvio);
                holder.ivImagen = (ImageView) item.findViewById(R.id.iImagen);

                item.setTag(holder);
            }
            else
                holder = (ViewHolder)item.getTag();

            holder.tvId.setText("ID: " + pedidos[i].getId());
            holder.tvNombre.setText(pedidos[i].getNombre());
            holder.tvPrecio.setText(pedidos[i].getPrecio());
            holder.tvSesion.setText(pedidos[i].getSesion());
            holder.tvUnidades.setText(pedidos[i].getUnidades());
            holder.tvEnvio.setText(pedidos[i].getEnvio());
            holder.tvPrecio.setText(pedidos[i].getPrecio());

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                holder.ivImagen.setBackground(getDrawable(pedidos[i].getImagen()));
            else
                holder.ivImagen.setBackgroundDrawable(getResources().getDrawable(pedidos[i].getImagen()));


            num = i;
            ImageButton borrarPedido = (ImageButton)item.findViewById(R.id.borrarItem);
            borrarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarConfirmacion();
                }
            });

            return (item);
        }
    }

    public void llenarArray() {

        SQLiteHelper sqliteHelper = new SQLiteHelper(this, "cine.sqlite", null, 1);
        SQLiteDatabase bd = sqliteHelper.getReadableDatabase();

        if(bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM Pedidos", null);
            int cantidad = cursor.getCount();
            int i = 0;
            pedidos = new Pedido[cantidad];

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String nombre = cursor.getString(1);
                    String precio = cursor.getString(2);
                    String sesion = cursor.getString(3);
                    String unidades = cursor.getString(4);
                    String envio = cursor.getString(5);
                    int imagen = cursor.getInt(6);

                    pedidos[i] = new Pedido(id,nombre,precio,sesion,unidades,envio,imagen);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
    }

    public void mostrarToast(String texto){
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }




    public void mostrarConfirmacion() {
        VentanaDialogo appdialog = new VentanaDialogo();
        appdialog.Confirm(this, "Confirmación", "Seguro quieres eliminar?", "NO", "SI", aceptar(), rechazar());
    }

    public Runnable aceptar(){
        return new Runnable() {
            public void run() {
                borrarPedido();
            }
        };
    }
    public Runnable rechazar(){
        return new Runnable() {
            public void run() {
                mostrarToast("Operación cancelada");
            }
        };
    }

    public void borrarPedido(){
        final SQLiteDatabase bd = sqliteHelper.getReadableDatabase();
        bd.execSQL("DELETE FROM Pedidos WHERE id = " + pedidos[num].getId());
        mostrarToast("Pedido borrado correctamente");
        recargarLista();
    }



    public void recargarLista() {
        Intent home_intent = new Intent(getApplicationContext(), ListarSegunda.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}