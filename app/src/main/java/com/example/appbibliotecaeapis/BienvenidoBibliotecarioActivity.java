package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class BienvenidoBibliotecarioActivity extends AppCompatActivity {

    ImageButton ibgagregar,ibgmodificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido_bibliotecario);

        ibgagregar = findViewById(R.id.imButton_agregarLibro);
        ibgmodificar = findViewById(R.id.imButton_ModificarLibro);

        ibgagregar.setOnClickListener(v -> {
            llevarAgregar();
        });
        ibgmodificar.setOnClickListener(v -> {
            llevarModificar();
        });
    }

    private void llevarModificar() {
        Intent oIntent = new Intent(BienvenidoBibliotecarioActivity.this, ModificarLibro1_1_Activity.class);
        startActivity(oIntent);
    }

    private void llevarAgregar() {
        Intent oIntent = new Intent(BienvenidoBibliotecarioActivity.this, AgregarLibroActivity.class);
        startActivity(oIntent);
    }
}