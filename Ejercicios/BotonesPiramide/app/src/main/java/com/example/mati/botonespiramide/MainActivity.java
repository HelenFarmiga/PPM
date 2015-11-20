package com.example.mati.botonespiramide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = new Button(this);
        button.setText("Touch That!");

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onButtonClick(v);
            }
        });

    }

    public void onButtonClick(View view){
        button.getB
    }

}
