package com.example.clientej2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPSingleton extends Thread {

    private static TCPSingleton unicaInstancia;

    private TCPSingleton(){

    }

    //Instance
    public static TCPSingleton getInstance(){

        if(unicaInstancia==null){
            unicaInstancia= new TCPSingleton();
            unicaInstancia.start();
        }
        return unicaInstancia;
    }

    //writer y reader
    private BufferedWriter writer;
    private BufferedReader reader;


    //Observer

    OnMessageListener observer;

    public void setObserver(OnMessageListener observer) {
        this.observer=observer;
    }



    //Hilo conexion
    public void run(){

        try {
            Log.e(">>","Esperando conexion");
            Socket socket= new Socket("192.168.0.8",5000);
            Log.e(">>","Conectado");


            //Input Output
            OutputStream os= socket.getOutputStream();
            InputStream is=socket.getInputStream();


            //Writer reader
            writer=new BufferedWriter(new OutputStreamWriter(os));
            reader= new BufferedReader(new InputStreamReader(is));


            //Recepción mensaje
            while(true) {
                String line = reader.readLine();
                System.out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Metodo recepcion mensajes
    public void recibir() {

        new Thread (
                ()->{

                    try {
                        while(true) {

                            System.out.println("Esperando mensaje");

                            //Recibimos el mensaje
                            String line;
                            line = reader.readLine();

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();


    }



    //Metodo envio
    public void enviar(String mensaje){

        new Thread(
                ()->{
                    try {
                        writer.write(mensaje + "\n");
                        writer.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

        ).start();

    }



}
