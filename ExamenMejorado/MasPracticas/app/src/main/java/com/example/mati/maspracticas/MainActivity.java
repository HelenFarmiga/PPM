package com.example.mati.maspracticas;



    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.LayoutInflater;
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
        CheckBox extragrande, extraIngrediente, extraQueso;
        EditText Cantidad;
        Spinner miSpinner;
        RadioGroup radioGroup;
        Button calcular;


        Boolean envio;
        String extra;
        String des;
        int imagenPizza;
        String nom;



        int precioTipo = 0;
        int precioTotal = 0;
        int precioEx= 0;

        int sum=0;
        int unidades=0;


        private Pizza[] objetosSpinner = new Pizza[]{
                new Pizza("MARGARITA", "Jamon/tomate", "12", R.drawable.pizza1),
                new Pizza("TRES QUESOS", "queso1/queso2/queso3", "15", R.drawable.pizza2),
                new Pizza("BARBACOA", "Carne/tomate", "20", R.drawable.pizza3)
        };

        public void mostrarToast(String text) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }


        class MiAdaptador extends ArrayAdapter<Pizza> {

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

                TextView nombre = (TextView)convertView.findViewById(R.id.TvPizza);
                nombre.setText(objetosSpinner[position].getNombre());

                TextView descripcion =(TextView)convertView.findViewById(R.id.tvCompuesto);
                descripcion.setText(objetosSpinner[position].getDescripcion());

                TextView precio=(TextView)convertView.findViewById(R.id.tvPrecio);
                precio.setText(objetosSpinner[position].getPrecio());

                ImageView imagen = (ImageView)convertView.findViewById(R.id.ivImagen);
                imagen.setImageResource(objetosSpinner[position].getImagen());

                nom = objetosSpinner[position].getNombre();
                des = objetosSpinner[position].getDescripcion();
                precioTipo = Integer.parseInt(objetosSpinner[position].getPrecio());
                imagenPizza = objetosSpinner[position].getImagen();
                return convertView;
            }
        }
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            extragrande = (CheckBox)findViewById(R.id.cbGrande);
            extraIngrediente = (CheckBox)findViewById(R.id.cbIng);
            extraQueso = (CheckBox)findViewById(R.id.cbQueso);
            Cantidad = (EditText)findViewById(R.id.cant);

            MiAdaptador adaptador = new MiAdaptador(this);
            miSpinner = (Spinner)findViewById(R.id.spin);
            miSpinner.setAdapter(adaptador);


            radioGroup = (RadioGroup) findViewById(R.id.grupoRadio);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (R.id.rb1 == radioGroup.getCheckedRadioButtonId())
                        envio = false;
                    else
                        envio = true;

                }
            });
            extraQueso.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (extraQueso.isChecked()) {
                        extra = "Con extra de queso";
                        precioEx += 1;
                    } else {
                        precioEx -= 1;
                    }
                }
            });
            extragrande.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (extragrande.isChecked()) {
                        extra = "Tamaño más grande";
                        precioEx += 1;
                    }
                    else{
                        precioEx -= 1;
                    }
                }
            });

            extraIngrediente.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(extraIngrediente.isChecked()){
                        extra = "Con más ingredientes";
                        precioEx += 1;
                    }
                    else{
                        precioEx -= 1;
                    }
                }
            });
            calcular = (Button)findViewById(R.id.btCal);
            calcular.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {
                    int unidades = Integer.parseInt(Cantidad.getText().toString());
                    sum = precioTipo+ precioEx;
                    precioTotal = sum * unidades;
                    if(envio == true){
                        precioTotal*=1.10;
                    }

                    Intent intent = new Intent(MainActivity.this, SegundaPantalla.class);
                    Bundle bundle = new Bundle();

                    // añadimos las variables al bundle creado
                    bundle.putInt("Cantidad", unidades );
                    bundle.putInt("TipoPrecio",precioTipo);
                    bundle.putBoolean("ENVIO", envio);
                    bundle.putString("Nombre", nom);
                    bundle.putInt("PRECIO", precioTotal);
                    bundle.putInt("IMAGEN", imagenPizza);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }

    }