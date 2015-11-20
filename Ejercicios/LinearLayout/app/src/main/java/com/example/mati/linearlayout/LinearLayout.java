package com.example.mati.linearlayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LinearLayout extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView textview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        textview = (TextView) findViewById(R.id.textview);

        /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                switch (checkedId) {
                    case R.id.rbutton1:
                        textview.setBackgroundColor(Color.parseColor("FFFF9E9B"));
                        break;
                    case R.id.rbutton2:
                        textview.setBackgroundColor(Color.parseColor("FFBFFFB6"));
                        break;
                    case R.id.rbutton3:
                        textview.setBackgroundColor(Color.parseColor("FFB8E9FF"));
                        break;
                }
            }

            // }
        });
    }
}



