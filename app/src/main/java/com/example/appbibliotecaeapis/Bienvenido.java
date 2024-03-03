package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }
    //PantallaUsuario
    public void PantallaUsuario(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, EditarUsuario.class);
        startActivity(intent);
    }

}