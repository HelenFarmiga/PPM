package mati.example.com.prueba.bajarpaggoogle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DescargarPaginaWeb extends AsyncTask<String, String, String> {
    private TextView dataField;
    private Context context;

    public DescargarPaginaWeb(Context context, TextView dataField) {
        this.context = context;
        this.dataField = dataField;
    }
    private void checkInternetConenction(){
        ConnectivityManager check = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (check != null) {
            NetworkInfo[] info = check.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Toast.makeText(context, "Internet está conectado", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
        else
            Toast.makeText(context, "No se puede conectar a Internet", Toast.LENGTH_SHORT).show();

    }

    protected void onPreExecute(){
        checkInternetConenction();
    }

    protected String doInBackground(String... arg0) {

        String paginaWeb = "";
        HttpURLConnection httpURLConnection=null;

        try {

            String enlace = arg0[0];
            URL url = new URL(enlace);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String data = null;


                while ((data = reader.readLine()) != null) {
                    paginaWeb += data + "\n";
                }
                is.close();
            }
            publishProgress(paginaWeb);

        }
        catch(Exception e){
            paginaWeb= new String("Exception: " + e.getMessage());
        }
        finally {
            httpURLConnection.disconnect();
        }
        return paginaWeb;
    }


    protected void onProgressUpdate(String... pasos) {
        Toast.makeText(context, pasos[0], Toast.LENGTH_SHORT).show();
    }

    protected void onPostExecute(String result){
        this.dataField.setText(result);
    }
}
