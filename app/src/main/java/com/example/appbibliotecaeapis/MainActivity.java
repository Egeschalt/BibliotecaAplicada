package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;

import VistaModelo.BDPrestamoOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Crear la base de datos al iniciar la aplicación
        BDPrestamoOpenHelper dbHelper = new BDPrestamoOpenHelper(this, "BibliotecaDB", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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