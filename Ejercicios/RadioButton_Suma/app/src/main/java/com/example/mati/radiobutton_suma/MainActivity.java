package com.example.mati.suma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mati.radiobutton_suma.R;


public class MainActivity extends AppCompatActivity {

    EditText nume1, nume2;
    TextView resulta;
    RadioGroup radioG;
    Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nume1 = (EditText)findViewById(R.id.num1);
        nume2 = (EditText)findViewById(R.id.num2);
        radioG = (RadioGroup)findViewById(R.id.radioGroup);
        resulta = (TextView) findViewById(R.id.Result);
        calcular = (Button) findViewById(R.id.btoperar);

        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dato1 = nume1.getText().toString();
                String dato2 = nume2.getText().toString();
                int numer1 = Integer.parseInt(dato1);
                int numer2 = Integer.parseInt(dato2);
                if (R.id.rbuttonS == radioG.getCheckedRadioButtonId()){
                    int suma=numer1+numer2;
                    String resu=String.valueOf(suma);
                    resulta.setText(resu);
                }
                if (R.id.rbuttonR == radioG.getCheckedRadioButtonId()){
                    int resta = numer2-numer1;
                    String resu = String.valueOf(resta);
                    resulta.setText(resu);
                }

                if(R.id.rbuttonM == radioG.getCheckedRadioButtonId()){
                    int multiplica = numer1*numer2;
                    String resu= String.valueOf(multiplica);
                    resulta.setText(resu);
                }
                if(R.id.rbuttonD == radioG.getCheckedRadioButtonId()){
                    int divide = numer1/numer2;
                    String resu= String.valueOf(divide);
                    resulta.setText(resu);
                }
            }
        });



    }
}