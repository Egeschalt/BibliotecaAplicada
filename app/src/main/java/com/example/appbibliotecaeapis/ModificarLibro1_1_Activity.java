package com.example.appbibliotecaeapis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;

import Model.Libro;
import VistaModelo.VMLibro;

public class ModificarLibro1_1_Activity extends AppCompatActivity {
    ListView lvlibros;
    SearchView buscarAutor;
    SearchView buscar;



    VMLibro vmLibro = new VMLibro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro11);
        buscar = findViewById(R.id.searchv_modificarLibroP1);
        buscarAutor = findViewById(R.id.searchv_autor);
        lvlibros = findViewById(R.id.lv_modificarLibroP1);
        vmLibro.CargarLibros(this);

        // Actualiza el adaptador del ListView con los nuevos datos
        ArrayList<Libro> listaLibros = vmLibro.listarLibro();
        ArrayAdapter<Libro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLibros);
        lvlibros.setAdapter(adapter);
        registerForContextMenu(lvlibros);
        buscarAutor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Libro libroBuscado = vmLibro.BuscarAutor(ModificarLibro1_1_Activity.this, query); // Busca por título en lugar de por ID
                if (libroBuscado != null) {
                    listaLibros.clear();
                    listaLibros.add(libroBuscado);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ModificarLibro1_1_Activity.this, "No se encontró el libro con el título proporcionado", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // No es necesario manejar los cambios de texto aquí
                return false;
            }
        });


        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Libro libroBuscado = vmLibro.BuscarLibroTitle(ModificarLibro1_1_Activity.this, query); // Busca por título en lugar de por ID
                if (libroBuscado != null) {
                    listaLibros.clear();
                    listaLibros.add(libroBuscado);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ModificarLibro1_1_Activity.this, "No se encontró el libro con el título proporcionado", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Libro libroSeleccionado = obtenerLibro(info.position);

        if (item.getItemId() == R.id.im_Eliminar) {
            eliminarLibro(libroSeleccionado);
            return true;
        } else if (item.getItemId() == R.id.im_modificar) { // Corregir el ID del elemento de menú "Modificar"
            if (libroSeleccionado != null) {
                Intent intent = new Intent(ModificarLibro1_1_Activity.this, ModificarLibro1_2_Activity.class);
                intent.putExtra("idLibroSeleccionado", libroSeleccionado.getIdLibro());
                intent.putExtra("stockLibro", libroSeleccionado.getStock()); // Pasar el valor del stock
                intent.putExtra("libroSeleccionado", libroSeleccionado);
                startActivity(intent);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }

    private void eliminarLibro(Libro libro) {
        if (libro != null) {
            boolean eliminado = vmLibro.eliminarLibro(this, libro.getIdLibro());
            if (eliminado) {
                ArrayList<Libro> listaLibros = vmLibro.listarLibro();
                ArrayAdapter<Libro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLibros);
                lvlibros.setAdapter(adapter);
                Toast.makeText(this, "Libro eliminado correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModificarLibro1_1_Activity.this, BienvenidoBibliotecarioActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No se puedo eliminar el libro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Libro obtenerLibro(int position) {
        ArrayList<Libro> lista = vmLibro.listarLibro();
        if (position >= 0 && position < lista.size()) {
            return lista.get(position);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
