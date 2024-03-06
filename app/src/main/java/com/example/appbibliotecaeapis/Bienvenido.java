package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Bienvenido extends AppCompatActivity {

    ImageView idReservaLibro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        idReservaLibro = findViewById(R.id.reservaBienvenido);
        idReservaLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReservarLibro(v);
            }
        });
    }

    public void PantallaUsuario(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, EditarUsuario.class);
        startActivity(intent);
    }

    public void ReservarLibro(View view){
        Intent oIntent = new Intent(this, ReservaLibro.class);
        startActivity(oIntent);
    }
}