package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class BienvenidoBibliotecarioActivity extends AppCompatActivity {

    ImageButton ibgagregar,ibgmodificar,ibgeliminar,ibglistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido_bibliotecario);

        ibgagregar = findViewById(R.id.imButton_agregarLibro);
        ibgmodificar = findViewById(R.id.imButton_ModificarLibro);
        ibgeliminar = findViewById(R.id.imButton_eliminarLibro);
        ibglistar = findViewById(R.id.imButton_listarLibros);


        ibgagregar.setOnClickListener(v -> {
            llevarAgregar();
        });
        ibgmodificar.setOnClickListener(v -> {
            llevarModificar();
        });
        ibglistar.setOnClickListener(v -> {
            llevarListar();
        });
        ibgeliminar.setOnClickListener(v -> {
            llevaEliminar();
        });

    }

    private void llevaEliminar() {
    }

    private void llevarListar() {
    }

    private void llevarModificar() {
    }

    private void llevarAgregar() {
        Intent oIntent = new Intent(BienvenidoBibliotecarioActivity.this, AgregarLibroActivity.class);
        startActivity(oIntent);
    }
}