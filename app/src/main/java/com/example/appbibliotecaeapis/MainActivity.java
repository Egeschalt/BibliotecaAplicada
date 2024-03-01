package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void PantallaSesion(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, IniciarSesion.class);
        startActivity(intent);
    }
    public void PantallaRegistro(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, Registrar.class);
        startActivity(intent);
    }
}