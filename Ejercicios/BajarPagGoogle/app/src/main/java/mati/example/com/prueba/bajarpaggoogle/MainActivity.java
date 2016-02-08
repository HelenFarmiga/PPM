package mati.example.com.prueba.bajarpaggoogle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText pag;
    TextView aux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pag = (EditText)findViewById(R.id.editText1);
        aux = (TextView)findViewById(R.id.textView2);
    }
    public void go(View view){
        String url = "http://" + pag.getText().toString();
        Descargar down = new Descargar(this, aux);
        down.execute(url);
    }
}

class Descargar extends AsyncTask<String, String, String> {
    private Context context;
    private TextView tv;
    public Descargar(Context context, TextView tv) {
        this.context = context;
        this.tv = tv;
    }
    private void checkInternetConenction(){
        ConnectivityManager Connect = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Connect != null) {
            NetworkInfo[] ninf = Connect.getAllNetworkInfo();
            if (ninf != null) {
                for (int i = 0; i < ninf.length; i++) {
                    if (ninf[i].getState() == NetworkInfo.State.CONNECTED) {
                        Toast.makeText(context, "La conexión ha internet esta establecida", Toast.LENGTH_SHORT).show();
                            }
                    }
                }
         }
        else
            Toast.makeText(context, "Error con la conexión", Toast.LENGTH_SHORT).show();
    }
    protected void onPreExecute(){
        checkInternetConenction();
    }
    protected String doInBackground(String... arg0) {
        String pag = "";
        HttpURLConnection htc=null;
        try {
            String url = arg0[0];
            URL urlg = new URL(url);
            htc = (HttpURLConnection) urlg.openConnection();
            htc.setReadTimeout(10000);
            htc.setConnectTimeout(15000);
            htc.setRequestMethod("GET");
            htc.setDoInput(true);
            htc.connect();
            if (htc.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream in = htc.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String data = null;


                while ((data = buffer.readLine()) != null) {
                    pag += data + "\n";
                }
                in.close();
            }
            publishProgress(pag);

        } catch(Exception e){
            pag= new String("Exception: " + e.getMessage());
        }
        finally {
            htc.disconnect();
        }
        return pag;
    }
    protected void onProgressUpdate(String... pasos) {
        Toast.makeText(context, pasos[0], Toast.LENGTH_SHORT).show();
    }
    protected void onPostExecute(String result){
        this.tv.setText(result);
    }
}
