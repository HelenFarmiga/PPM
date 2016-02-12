package com.example.mati.randomutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchDrawShapes1(View clickedButton) {
        Intent activityIntent =
                new Intent(this, DrawShapes1.class);
        startActivity(activityIntent);
    }
    public void launchDrawShapes2(View clickedButton) {
        Intent activityIntent =
                new Intent(this, DrawShapes2.class);
        startActivity(activityIntent);
    }
    public void launchDrawShapes3(View clickedButton) {
        Intent activityIntent =
                new Intent(this, DrawShapes3.class);
        startActivity(activityIntent);
    }
}
