package com.example.mati.layout;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // eventos de onClick de botones

    public void irAlinear (View view) {
        Intent intent = new Intent(this, LinearActivity.class);
        startActivity(intent);
    }

    public void irAtable (View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    public void irArelative (View view) {
        Intent intent = new Intent(this, RelativeActivity.class);
        startActivity(intent);
    }

    public void irAgrid (View view) {
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }


}
