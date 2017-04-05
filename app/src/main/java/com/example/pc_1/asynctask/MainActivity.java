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

import java.io.IOException;

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

    public void onButton(View view) {
        if(inOnLine()){
            MyTask task =new MyTask();
            task.execute("http://186.116.10.48/zeusacad/img/usuarios.xml");
            //

        }else{
            Toast.makeText(this, "Sin Conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarDatos(String dato){
        texto.setText(dato+"\n");
    }



    public class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargador.setVisibility(View.VISIBLE);
            cargarDatos("Iniciar Datos");
        }

        @Override
        protected String doInBackground(String... params) {
            //se obtendra la url que esta llegando como parametro
            String contend = null;
            try {
                contend = HttpManager.getData(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contend;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            cargarDatos (values[0]);


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargador.setVisibility(View.GONE);
            cargarDatos(s);
        }
    }
}
