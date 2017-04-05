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
        //permite acceder al servicio para verificar la conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //permite obtener la informacion de la conexion
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();

        if(network != null && network.isConnectedOrConnecting()) {
            return true;
        }else{
            return false;
        }
    }
    //***********************************************
    ///llamar al metodo que se encuentra arriba!!!
    //Evento boton cargar informacion
    public void onButton(View view) {
        if(inOnLine()){
            MyTask task =new MyTask();
            //EJECUTAR MI TAREA Y SE PARA COMO PARAMETRO LA URL DE LOS DATOS
            task.execute("http://186.116.10.48/zeusacad/img/usuarios.xml");
            //

        }else{
            Toast.makeText(this, "Sin Conexion", Toast.LENGTH_SHORT).show();
        }
    }
    //Evento cargar datoss
    public void cargarDatos(String dato){
        ///
        texto.setText(dato+"\n");
    }



    public class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            //habilita boton cargar y lo pone visike
            super.onPreExecute();
            cargador.setVisibility(View.VISIBLE);
            cargarDatos("Iniciar Datos");
        }

        @Override
        protected String doInBackground(String... params) {
            //se obtendra la url que esta llegando como parametro
            //se crea variable
            String contend = null;
            try {
                //lo que retorne la clase de conexion, se lo asigno como variable, el try catch maneja errore
                contend = HttpManager.getData(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //retorna lo que hay en conexxion, se va a metodo PostExecute
            return contend;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            cargarDatos (values[0]);


        }

        @Override
        protected void onPostExecute(String s) {
            //quita el cargador y manda a llamar a los datos
            super.onPostExecute(s);
            cargador.setVisibility(View.GONE);
            cargarDatos(s);
        }
    }
}
