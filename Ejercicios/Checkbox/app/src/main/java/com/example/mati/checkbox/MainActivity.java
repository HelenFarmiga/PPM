package com.example.mati.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxGOT;
    CheckBox chkBoxOITNB;
    CheckBox chkBoxTBBT;
    Button btnSerie;
    TextView txtHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialUISetup();
    }

    public void initialUISetup()
    {
        chkBoxOITNB = (CheckBox) findViewById(R.id.chkBoxOITNB);
        chkBoxGOT = (CheckBox) findViewById(R.id. chkBoxGOT);
        chkBoxTBBT = (CheckBox) findViewById(R.id.chkBoxTBBT);
        btnSerie = (Button)findViewById(R.id.btnSerie);
        txtHobby = (TextView)findViewById(R.id.txtSerie);

        btnSerie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSerieClick(v);
            }
        });
    }
    public void getSerieClick(View v)
    {  String strMessage = "";

        if(chkBoxOITNB.isChecked())
        {
            strMessage+="Orange is the New Black ";
            strMessage+=",";
            showTextNotification(strMessage);
        }
        if(chkBoxGOT.isChecked())
        {
            strMessage+="Game of Thrones ";
            strMessage+=", ";
            showTextNotification(strMessage);
        }
        if( chkBoxTBBT.isChecked())
        {
            strMessage+="The Big Bang Theory ";
            strMessage+=".";
            showTextNotification(strMessage);
        }
    }

    public void showTextNotification(String msgToDisplay)
    {
        txtHobby.setText(msgToDisplay);
        //Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();

    }

}
