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

    CheckBox lunes, martes, miercoles, jueves, viernes, sabado, domingo;
    EditText Cantidad;
    Spinner miSpinner;
    RadioGroup radioGroup;
    RadioGroup radioGroup2;
    Button calcular;
    TextView lblMensaje;

    String gen;
    String hora;
    String nom;
    String tipo="En local";
    String tipo2="Segunda Sesión";
    int imagenPeli;


    Boolean enviar=false;
    Boolean ultima=false;
    String extra="Lunes";

    double precioP = 0;
    double precioTotal = 0;
    double precioMenu = 0;
    double sum = 0;
    int unidades = 0;
    double PrecioPorEnvio = 0;
    double PrecioPorSesion=0;
    ViewHolder holder;

    private Pelicula[] Peliculas = new Pelicula[]{
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
            super(context, R.layout.items_spinner, Peliculas);
            this.context = context;

        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);

        }
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;

            if(item==null){
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.items_spinner, null);

                holder = new ViewHolder();

                holder.nombre = (TextView)item.findViewById(R.id.TvPizza);
                holder.genero = (TextView)item.findViewById(R.id.tvPrecio);
                holder.precio = (TextView)item.findViewById(R.id.tvPrecio);
                holder.horario = (TextView)item.findViewById(R.id.tvPrecio);
                holder.imagen = (ImageView)item.findViewById(R.id.ivImagen);

                item.setTag(holder);
            }
            else {
                holder = (ViewHolder)item.getTag();
            }
            holder.nombre.setText(Peliculas[position].getNombre());
            holder.genero.setText(Peliculas[position].getGenero());
            holder.precio.setText(String.valueOf(Peliculas[position].getPrecio()));
            holder.horario.setText(Peliculas[position].getHorario());
            holder.imagen.setImageResource(Peliculas[position].getImagen());
            return (item);

        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lunes = (CheckBox) findViewById(R.id.cbl);
        martes = (CheckBox) findViewById(R.id.cbm);
        miercoles = (CheckBox) findViewById(R.id.cbx);
        jueves=(CheckBox)findViewById(R.id.cbj);
        viernes=(CheckBox)findViewById(R.id.cbv);
        sabado=(CheckBox)findViewById(R.id.cbs);
        domingo=(CheckBox)findViewById(R.id.cbd);

        Cantidad = (EditText) findViewById(R.id.cant);


       final MiAdaptador adaptador = new MiAdaptador(this);
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
        precioMenu = 0;
        lunes.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (lunes.isChecked()) {
                    extra= "Lunes";
                }

            }
        });
        martes.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (martes.isChecked()) {
                    extra= "Martes";
            }
            }
        });
        miercoles.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (miercoles.isChecked()) {
                    extra = "Miercoles";
                    precioMenu -= 1;
                }
                else{
                    precioMenu +=1;
                }
            }
        });
            jueves.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (jueves.isChecked()) {
                        extra = "Jueves";
                    }
                }
            });
            viernes.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (viernes.isChecked()) {
                        extra = "Viernes";
                    }
                }
            });
            sabado.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (sabado.isChecked()) {
                        extra = "Sabado";
                        precioMenu += 1;
                    }
                    else{
                        precioMenu -=1;
                    }
                }
            });
            domingo.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (domingo.isChecked()) {
                        extra = "Domingo";
                        precioMenu += 1;
                    }
                    else{
                        precioMenu -=1;
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
                Intent acerca = new Intent(this, AcercaDe.class);
                startActivity(acerca);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


} class ViewHolder{
    TextView nombre, genero, precio, horario;
    ImageView imagen;
}




