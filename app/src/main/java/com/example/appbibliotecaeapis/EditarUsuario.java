package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
    }
    //PantallaBienvenido
    public void PantallaBienvenido(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, Bienvenido.class);
        startActivity(intent);
    }
}