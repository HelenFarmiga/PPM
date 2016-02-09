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
    RadioGroup radioGroup2;
    Button calcular;

    String gen;
    String hora;
    String nom;
    String tipo="En local";
    String tipo2="Segunda Sesión";

    int imagenPeli;
    int idIdentificado;


    Boolean enviar=false;
    Boolean ultima=false;
    String extra;

    double precioP = 0;
    double precioTotal = 0;
    double precioMenu = 0;
    double sum = 0;
    double unidades = 0;
    double PrecioPorEnvio = 0;
    double PrecioPorSesion=0;


    private Pelicula[] objetosSpinner = new Pelicula[]{
            new Pelicula("Pitch Perfect 2","|Comedia/Musical |","6", "€ |17:30|20:40|22:00", R.drawable.pelicula1),
            new Pelicula("Escuadrón Suicida","|Accion |","8","€ |18:15|20:00|23:45", R.drawable.pelicula2),
            new Pelicula("12 años de esclavitud", "|Drama|" ,"8", "€ |18:00|20:25|22:30", R.drawable.pelicula3),
            new Pelicula("Jennifer's Body", "|Terror|","8","€ |19:30|21:45|00:00",R.drawable.pelicula4),
            new Pelicula("Mientras seamos jovenes", "|Comedia|","6","€ |17:30|19:00|21:15",R.drawable.pelicula5),
            new Pelicula("Krampus","|Accion|","6" ,"€ |19:30|23:30|01:30",R.drawable.pelicula6),
            new Pelicula("Kill Bill", "|Accion|","8","€ |18:30|21:00|23:30",R.drawable.pelicula7),
            new Pelicula("Chicas Malas","|Comedia|","6", "€ |17:30|19:30|21:30",R.drawable.pelicula8),
            new Pelicula("The Rocky Horror Picture Show", "|Terror/Musical|","6","€ |19:45|22:00|01:30",R.drawable.pelicula9),
            new Pelicula("Un dios salvaje","|Comedia|","6", "€ |18:00|20:30|22:15",R.drawable.pelicula10),

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

        saladas = (CheckBox) findViewById(R.id.cbl);
        dulces = (CheckBox) findViewById(R.id.cbm);
        nachos = (CheckBox) findViewById(R.id.cbx);

        Cantidad = (EditText) findViewById(R.id.cant);
        Bundle bundle= getIntent().getExtras();
        idIdentificado= bundle.getInt("IDUSER");


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
        radioGroup2 =(RadioGroup) findViewById(R.id.grupoRadio2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (R.id.rbb1 == radioGroup2.getCheckedRadioButtonId()) {
                        tipo2 = "Primera Sesión";
                        ultima = false;
                    } else {
                        if(R.id.rbb2 ==radioGroup2.getCheckedRadioButtonId()) {
                            tipo2 = "Segunda Sesión";
                            ultima= false;
                        }
                        else{
                        tipo2="Tercera Sesión";
                            ultima= true;
                            PrecioPorSesion = 0.9;
                        }
                    }
                }
        });
        /*
        precioMenu = 0;
        saladas.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (saladas.isChecked()) {
                    extra += "saladas";
                    precioMenu += 1;
                }

                else{

                    precioMenu -=1;
                }

            }
        });
        dulces.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (dulces.isChecked()) {
                    extra += "dulces";
                    precioMenu += 1;
                }
                else{
                    precioMenu -=1;
                }

            }
        });
        nachos.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (nachos.isChecked()) {
                    extra += "nachos";
                    precioMenu += 1;
                }
                else{
                    precioMenu -=1;
                }

            }
        })*/



        calcular = (Button) findViewById(R.id.btCal);
        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Cantidad.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Inserta el número de entradas", Toast.LENGTH_SHORT).show();
                } else {
                        //CHECKBOX
                        if (saladas.isChecked()) {
                            extra += " Saladas. ";
                            precioMenu += 2;
                        }
                        if (dulces.isChecked()) {
                            extra += " Dulces. ";
                            precioMenu += 2;
                        }
                        if (nachos.isChecked()) {
                            extra += " Nachos. ";
                            precioMenu += 2;
                        }

                        int unidades = Integer.parseInt(Cantidad.getText().toString());
                    precioTotal = 0;
                    sum = precioP * unidades;
                    precioTotal = sum + precioMenu;
                    if(enviar ==true) {
                        precioTotal *= PrecioPorEnvio;
                    }
                    if(ultima==true){
                        precioTotal *=PrecioPorSesion;
                    }
                    Intent intent = new Intent(MainActivity.this, SegundaPantalla.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("Nombre", nom);
                    bundle.putDouble("precioP", precioP);
                    bundle.putString("Extra", extra);
                    bundle.putInt("Cantidad", unidades);
                    bundle.putDouble("PRECIO", precioTotal);
                    bundle.putString("Envio", tipo);
                    bundle.putString("Hora", tipo2);
                    bundle.putInt("IMAGEN", imagenPeli);
                    bundle.putInt("IDUSER",idIdentificado);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        extra="";
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
                Intent acerca = new Intent(this, AcercaDe.class);
                startActivity(acerca);
                return true;
            case R.id.Opc3:
                Intent Listar = new Intent(this, ListarSegunda.class);
                Bundle b = new Bundle();
                b.putInt("ID", idIdentificado);
                Listar.putExtras(b);
                startActivity(Listar);
                return true;

            case R.id.Opc4:
                if(idIdentificado==1) {
                    Intent usuarios = new Intent(this, ListarUsu.class);
                    Bundle d = new Bundle();
                    d.putInt("ID", idIdentificado);
                    usuarios.putExtras(d);
                    startActivity(usuarios);
                    item.setVisible(true);
                    return true;
                }
                else{
                    item.setVisible(false);
                    return true;
                }
            case R.id.Opc5:
                startActivity(new Intent(this, Inicio.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
                }

    }

}



