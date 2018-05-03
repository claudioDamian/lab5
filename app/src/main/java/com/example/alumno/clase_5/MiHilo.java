package com.example.alumno.clase_5;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MiHilo extends Thread {
   private TextView tv;
   public ImageView iv;
   private Handler miHandler;
   private String mensaje;
    public MiHilo(Handler handlerParam){
        this.miHandler = handlerParam;
    }
    @Override
    public void run(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL miCon = null;
        try {
            miCon = new URL("http://www.lslutnfra.com/alumnos/practicas/listaPersonas.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection con =(HttpURLConnection) miCon.openConnection();

            con.setRequestMethod("GET");

            con.connect();

            int codigo = con.getResponseCode();

            if(codigo == 200){
                InputStream is = con.getInputStream();// me conecto con el input del servidor
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] arr = new byte[1024];
                int len;
                while((len = is.read(arr,0,1024))> -1){
                    baos.write(arr,0,1024);
                }
                is.close();
                this.mensaje = new String(baos.toByteArray(),"UTF-8");// para cuando retornams strings


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msj = new Message();

        msj.obj = this.mensaje;
        miHandler.sendMessage(msj);

    }
}
