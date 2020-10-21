package com.example.clientej2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientej2.model.Vida;
import com.google.gson.Gson;

public class PantallaIntro extends AppCompatActivity implements View.OnClickListener{

    private TextView bJugar;

    TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_intro);

        bJugar=findViewById(R.id.bJugar);

        tcp=TCPSingleton.getInstance();

        bJugar.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent i = new Intent(this,EscogePersonaje.class);
        startActivity(i);

        //creamos el gson
        Gson gson= new Gson();

        //Creo las vidas de los jugadores
        Vida vida= new Vida(50);
        String json=gson.toJson(vida);

        //Enviamos el json
        tcp.enviar(json);

        Log.e(">>",json);


    }
}