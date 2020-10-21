package com.example.clientej2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientej2.model.Jugador;
import com.google.gson.Gson;

public class EscogePersonaje extends AppCompatActivity implements View.OnClickListener{

    private ImageView mugman, cuphead;

    String personaje;

    //TCP
    TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoge_personaje);

        mugman=findViewById(R.id.mugman);
        cuphead=findViewById(R.id.cuphead);

        tcp= TCPSingleton.getInstance();
        mugman.setOnClickListener(this);
        cuphead.setOnClickListener(this);
    }


    public void onClick(View view){

        Intent i = new Intent(this, Control.class);
        startActivity(i);

        switch (view.getId()) {
            case R.id.cuphead:
                personaje="cuphead";
                break;
            case R.id.mugman:
                personaje="mugman";
                break;
        }

        //creamos el gson
        Gson gson= new Gson();

        //Creamos al jugador y lo pasamos a JSON
        Jugador jugador= new Jugador(personaje);
        String json= gson.toJson(jugador);
       

        //Enviamos el json
        tcp.enviar(json);

        Log.e(">>",json);
    }

}