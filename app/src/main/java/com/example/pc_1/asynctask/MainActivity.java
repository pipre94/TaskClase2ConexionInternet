package com.example.pc_1.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
