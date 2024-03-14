package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageButton;
import android.widget.TextView;

import VistaModelo.BDPrestamoOpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView tv_Registrarse;
    ImageButton btn_iniciaSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Crear la base de datos al iniciar la aplicación
        BDPrestamoOpenHelper dbHelper = new BDPrestamoOpenHelper(this, "BibliotecaDB", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        tv_Registrarse = findViewById(R.id.tv_registrarse);
        btn_iniciaSesion = findViewById(R.id.bt_1);

        tv_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              PantallaRegistro(v);
            }
        });
        btn_iniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PantallaSesion(v);
            }
        });
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