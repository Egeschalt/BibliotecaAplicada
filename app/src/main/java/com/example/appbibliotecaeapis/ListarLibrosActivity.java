package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Model.Libro;
import VistaModelo.VMLibro;

public class ListarLibrosActivity extends AppCompatActivity {

    ListView lvlibros;
    VMLibro vmLibro = new VMLibro();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);


        lvlibros= findViewById(R.id.lv_listaDeLibros);

        vmLibro.CargarLibros(this);

// Actualiza el adaptador del ListView con los nuevos datos
        ArrayList<Libro> listaLibros = vmLibro.listarLibro();
        ArrayAdapter<Libro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLibros);
        lvlibros.setAdapter(adapter);

    }
}