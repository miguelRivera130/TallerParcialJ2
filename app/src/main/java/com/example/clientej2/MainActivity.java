package com.example.clientej2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private TextView bContinuar;

    TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bContinuar=findViewById(R.id.continuar);

        tcp=TCPSingleton.getInstance();

        bContinuar.setOnClickListener(this);

    }

    public void onClick(View view){
        Intent i = new Intent(this,PantallaIntro.class);
        startActivity(i);

    }
}