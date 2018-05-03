package com.example.alumno.clase_5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    TextView tv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       this.tv = (TextView) findViewById(R.id.tv);

        Handler handler = new Handler(this);
        MiHilo hilo = new MiHilo(handler);
        hilo.start();

    }

    @Override
    public boolean handleMessage(Message msg) {

        if(msg.arg1 == 0){
            tv.setText(msg.obj.toString());
        }
        if(msg.arg1 == 1){
            byte[] arr = (byte[]) msg.obj;
            Bitmap bm = BitmapFactory.decodeByteArray(arr,0,arr.length);
            this.iv.setImageBitmap(bm);

        }

        return false;
    }
}
