package com.example.pc_1.asynctask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar cargador;
    TextView texto;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargador = (ProgressBar) findViewById(R.id.cargador);
        texto = (TextView) findViewById(R.id.texto);
        boton = (Button) findViewById(R.id.boton);
    }

    ///METODO PARA VALIDAR LA CONEXION A INTERNET
    //****************************************
    public Boolean inOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();

        if(network != null && network.isConnectedOrConnecting()) {
            return true;
        }else{
            return false;
        }
    }
    //***********************************************
    ///llamar al metodo que se encuentra arriba!!!

    public void onButto(View view) {
        if(inOnLine()){
            //
            Toast.makeText(this, "my tarea",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sin Conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void onButtonStart(View view){
        new MyTask().execute(60);
    }

    public class MyTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargador.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... params) {

            int max = params[0];

            for(int i=0; i<=max; i++){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            return "Fin";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int contador = values[0];
            String cadena = "Contador: "+ contador;
            texto.setText(cadena);
            texto.setTextSize(contador);
            for(int j=0; j<=30; j++){
                texto.setTextColor(getColor(R.color.color1));
            }
                for(int H=31; H<=contador; H++){
                    texto.setTextColor(getColor(R.color.color2));
                }


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            texto.append("\n"+s);
            cargador.setVisibility(View.INVISIBLE);
        }
    }
}
