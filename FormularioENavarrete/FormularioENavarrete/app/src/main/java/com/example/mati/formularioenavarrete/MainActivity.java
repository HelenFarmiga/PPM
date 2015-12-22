package com.example.mati.formularioenavarrete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox saladas, dulces, nachos;
    EditText Cantidad;
    Spinner miSpinner;
    RadioGroup radioGroup;
    Button calcular;
    TextView lblMensaje;

    String gen;
    String hora;
    String nom;
    String tipo="En local";
    int imagenPeli;


    Boolean enviar=false;
    String extra;

    double precioP = 0;
    double precioTotal = 0;
    double precioMenu = 0;
    double sum = 0;
    double unidades = 0;
    double PrecioPorEnvio = 0;


    private Pelicula[] objetosSpinner = new Pelicula[]{
            new Pelicula("Pitch Perfect 2","|Comedia/Musical |","6", "€ |17:00-18:55", R.drawable.pelicula1),
            new Pelicula("Escuadrón Suicida","|Accion |","8","€ |20:00-22:45", R.drawable.pelicula2),
            new Pelicula("12 años de esclavitud", "|Drama|" ,"8", "€ |18:00-20:25", R.drawable.pelicula3),
            new Pelicula("Jennifer's Body", "|Terror|","8","€ |22:30-00:00",R.drawable.pelicula4),
            new Pelicula("Mientras seamos jovenes", "|Comedia|","6","€ |17:30-19:00",R.drawable.pelicula5),
            new Pelicula("Krampus","|Accion|","6" ,"€ |22:00-23:30",R.drawable.pelicula6),
            new Pelicula("Kill Bill", "|Accion|","8","€ |19:00-21:00",R.drawable.pelicula7),
            new Pelicula("Chicas Malas","|Comedia|","6", "€ |16:00-17:30",R.drawable.pelicula8),
            new Pelicula("The Rocky Horror Picture Show", "|Musical|","6","€ |22:00-00:00",R.drawable.pelicula9),
            new Pelicula("Un dios salvaje","|Accion|","6", "€ |19:00-21:00",R.drawable.pelicula10),

    };

    class MiAdaptador extends ArrayAdapter<Pelicula> {

        Activity context;

        MiAdaptador(Activity context) {
            super(context, R.layout.items_spinner, objetosSpinner);
            this.context = context;

        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            convertView = inflater.inflate(R.layout.items_spinner, null);

            TextView nombre = (TextView) convertView.findViewById(R.id.TvPizza);
            nombre.setText(objetosSpinner[position].getNombre());

            TextView genero = (TextView) convertView.findViewById(R.id.tvGenero);
            genero.setText(objetosSpinner[position].getGenero());

            TextView precio = (TextView) convertView.findViewById(R.id.tvPrecio);
            precio.setText(objetosSpinner[position].getPrecio());
            TextView horario = (TextView) convertView.findViewById(R.id.tvHorario);
            horario.setText(objetosSpinner[position].getHorario());

            ImageView imagen = (ImageView) convertView.findViewById(R.id.ivImagen);
            imagen.setImageResource(objetosSpinner[position].getImagen());

            nom = objetosSpinner[position].getNombre();
            gen = objetosSpinner[position].getGenero();
            precioP = Integer.parseInt(objetosSpinner[position].getPrecio());
            hora = objetosSpinner[position].getHorario();
            imagenPeli = objetosSpinner[position].getImagen();
            return convertView;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saladas = (CheckBox) findViewById(R.id.cbsaladas);
        dulces = (CheckBox) findViewById(R.id.cbdulces);
        nachos = (CheckBox) findViewById(R.id.cbnachos);
        Cantidad = (EditText) findViewById(R.id.cant);


        MiAdaptador adaptador = new MiAdaptador(this);
        miSpinner = (Spinner) findViewById(R.id.spin);
        miSpinner.setAdapter(adaptador);


        radioGroup = (RadioGroup) findViewById(R.id.grupoRadio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.rb1 == radioGroup.getCheckedRadioButtonId()) {
                    enviar = false;
                    tipo = "En local";
                } else {
                    enviar = true;
                    tipo="Por email";
                    PrecioPorEnvio = 0.9;

                }
            }
        });
        precioMenu = 0;
        saladas.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (saladas.isChecked()) {
                    extra = "Palomitas Saladas";
                    precioMenu += 1;
                } else {
                    extra = "Palomitas saladas ";
                    precioMenu -= 1;
                }

            }
        });
        dulces.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (dulces.isChecked()) {
                    extra = " Palomitas dulces";
                    precioMenu += 2;
                } else {
                    extra = "Palomitas dulces ";
                    precioMenu -= 2;
                }
            }
        });
        nachos.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (nachos.isChecked()) {
                    extra = "Nachos con queso ";
                    precioMenu += 3;
                } else {
                    extra = "Nachos con queso ";
                    precioMenu -= 3;
                }
            }
        });

        calcular = (Button) findViewById(R.id.btCal);
        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Cantidad.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Inserta el número de entradas", Toast.LENGTH_SHORT).show();
                } else {
                    int unidades = Integer.parseInt(Cantidad.getText().toString());
                    precioTotal = 0;
                    sum = precioP * unidades;
                    precioTotal = sum + precioMenu;
                    if(enviar ==true) {
                        precioTotal *= PrecioPorEnvio;
                    }
                    Intent intent = new Intent(MainActivity.this, SegundaPantalla.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("Nombre", nom);
                    bundle.putDouble("precioP", precioP);
                    bundle.putString("Extra", extra);
                    bundle.putDouble("Cantidad", unidades);
                    bundle.putDouble("PRECIO", precioTotal);
                    bundle.putString("Envio", tipo);
                    bundle.putInt("IMAGEN", imagenPeli);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Opc1:
                Intent activityIntent = new Intent(this, Dibujar.class);
                startActivity(activityIntent);
                return true;
            case R.id.Opc2:
                Toast.makeText(getApplicationContext(), "Derechos de autor, Elena Navarrete Contreras", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}




