package com.example.clientej2;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientej2.model.Disparo;
import com.example.clientej2.model.Posicion;
import com.google.gson.Gson;

public class Control extends AppCompatActivity implements OnMessageListener, View.OnTouchListener, View.OnClickListener {

    private Button bDerecha, bIzquierda, bDisparo;

    //TCP
    TCPSingleton tcp;

    //Booleano que controla el onTouch
    boolean buttonPressed;

    int x,y;
    int xd,yd,vel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        bDerecha=findViewById(R.id.rightButton);
        bIzquierda=findViewById(R.id.leftButton);
            bDisparo=findViewById(R.id.shot);
        //x y y del jugador
        x=400;
        y=400;
        //posiciones y vel del disparo
        xd=420;
        yd=400;
        vel=5;

        tcp= TCPSingleton.getInstance();

        bIzquierda.setOnTouchListener(this);
        bDerecha.setOnTouchListener(this);

        bDisparo.setOnClickListener(this);

    }


    //Controles movimiento
    public boolean onTouch(View view, MotionEvent event) {

        //controlamos cada evento de los botones con el boolean
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                buttonPressed=true;
                break;

            case MotionEvent.ACTION_UP:
                buttonPressed=false;
                break;
        }

        //Si la variable es true
        if( buttonPressed==true){

            new Thread(
                    ()->{
                        while (buttonPressed){

                            switch (view.getId()){
                                case R.id.rightButton:
                                    x= x+8;
                                    xd= xd+8;
                                    break;

                                case R.id.leftButton:

                                    x= x-8;
                                    xd=xd-8;
                                    break;
                            }

                            //Gson
                            Gson gson= new Gson();

                            //Lo pasamos a Gson
                            Posicion posicion= new Posicion(x,y);
                            String json=gson.toJson(posicion);

                            //Se envia la coordenada
                            tcp.enviar(json);

                            Log.e(">>>", json);

                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
            ).start();

        }
        return false;

    }

    //Controles disparo
    public void onClick(View view) {

        //Gson
        Gson gson= new Gson();

        //Lo pasamos a Gson
        Disparo disparo= new Disparo(xd,yd,vel);
        String json=gson.toJson(disparo);

        //Se envia la coordenada
        tcp.enviar(json);

        Log.e(">>>", json);

    }

}