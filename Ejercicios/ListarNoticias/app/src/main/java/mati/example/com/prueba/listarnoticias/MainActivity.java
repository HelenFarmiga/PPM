package mati.example.com.prueba.listarnoticias;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.support.v7.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    TextView pag;
    Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pag = (TextView) findViewById(R.id.pag);
        go =(Button)findViewById(R.id.go);
        final String web = "http://www.elseptimoarte.net/rss.php";

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                new TareaHttpAsincrona().execute(web);
            }
        });

    }
    public  class TareaHttpAsincrona extends AsyncTask<String, String, String> {
        private void checkInternetConenction(){
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo[] ni = cm.getAllNetworkInfo();
                if (ni != null) {
                    for (int i = 0; i < ni.length; i++) {
                        if (ni[i].getState() == NetworkInfo.State.CONNECTED)
                            Toast.makeText(MainActivity.this, "La conexión se ha realizado con exito", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
                Toast.makeText(MainActivity.this, "Ha ocurrido un error en su conexión", Toast.LENGTH_SHORT).show();
        }
        protected void onPreExecute(){
            checkInternetConenction();
        }
        protected String doInBackground(String... params) {
            String aux = "";
            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(1000);
                httpURLConnection.setConnectTimeout(1500);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream =  httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    int i, j;
                    String linea = bufferedReader.readLine();

                    while (linea != null) {
                        if (linea.contains("<title>")) {
                            i = linea.indexOf("<title>") + 16;
                            j = linea.indexOf("</title>") - 3;
                            aux += linea.substring(i, j);
                            aux += "\n\n\n";
                        }
                        linea = bufferedReader.readLine();
                    }

                    bufferedReader.close();
                    inputStream.close();

                }
                publishProgress(aux);

            }
            catch(Exception e){
                aux= ( e.getMessage());
            }
            finally {
                httpURLConnection.disconnect();
            }
            return aux;
        }

        protected void onProgressUpdate(String... pro) {
            pag.append(pro[0]);
        }

    }

}

