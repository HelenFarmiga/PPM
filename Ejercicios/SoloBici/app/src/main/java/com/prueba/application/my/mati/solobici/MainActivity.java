package com.prueba.application.my.mati.solobici;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button btGame;
    private Button btAcercaD;
    private Button btExit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btGame = (Button) findViewById(R.id.btGame);
        btGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Game();
            }
        });

        btAcercaD = (Button) findViewById(R.id.btAcerca);
        btAcercaD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AcercaD();
            }
        });

        btExit = (Button) findViewById(R.id.btExit);
        btExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Exit();
            }
        });
    }
        public void Game() {
            Intent i = new Intent(this, JuegoActivity.class);
            startActivity(i);
        }
        public void AcercaD() {
            Intent i = new Intent(this, AcercaDeActivity.class);
            startActivity(i);
        }
        public void Exit() {

            finish();
        }
}
