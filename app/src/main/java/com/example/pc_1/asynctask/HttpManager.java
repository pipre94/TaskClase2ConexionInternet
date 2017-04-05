package com.example.pc_1.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pc-16 on 5/04/17.
 */

public class HttpManager {
    public static String getData(String uri) throws IOException {
        // Clase para manejar archivos
        BufferedReader reader = null;
        //Clase URL de java (manejar rutas)
        //URL permite parsear las urls
        URL url = new URL(uri);
        //ESTA CLASE CONECTA A INTERNET
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //Se declara objeto de tipo StrinBuilder, para manejar el tipo de archivo
        StringBuilder stringBuilder= new StringBuilder();
        //Permite conectar y leer de internet, new InputStreamReader(connection.getInputStream())), este archivo permite recibir un parametro
        //y recibe archivo de la clase de internet
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        //verificar si el archivo llego o no
        while((line = reader.readLine())!= null){
            stringBuilder.append(line+"\n");

        }
        //tostring para hacer cadena
        return  stringBuilder.toString();

    }
}
