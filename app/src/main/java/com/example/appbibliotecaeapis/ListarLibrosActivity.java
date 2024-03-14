package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Libro;
import VistaModelo.VMLibro;

public class ListarLibrosActivity extends AppCompatActivity {

    ListView lvlibros;
    VMLibro vmLibro = new VMLibro();
    SearchView buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);


        lvlibros= findViewById(R.id.lv_listaDeLibros);
        buscar = findViewById(R.id.searchv_listaDeLibros);

        vmLibro.CargarLibros(this);

// Actualiza el adaptador del ListView con los nuevos datos
        ArrayList<Libro> listaLibros = vmLibro.listarLibro();
        ArrayAdapter<Libro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLibros);
        lvlibros.setAdapter(adapter);


        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    int idLibro = Integer.parseInt(query);
                    Libro libroBuscado = vmLibro.Buscar(ListarLibrosActivity.this, idLibro);
                    if (libroBuscado != null) {

                        listaLibros.clear();
                        listaLibros.add(libroBuscado);
                        adapter.notifyDataSetChanged();
                    } else {

                        Toast.makeText(ListarLibrosActivity.this, "No se encontró el libro con el ID proporcionado", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {

                    Toast.makeText(ListarLibrosActivity.this, "Ingrese un ID de libro válido", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // No es necesario manejar los cambios de texto aquí
                return false;
            }
        });
    }

}