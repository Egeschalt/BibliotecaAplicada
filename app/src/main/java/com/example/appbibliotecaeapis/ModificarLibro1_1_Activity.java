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

import java.util.ArrayList;

import Model.Libro;
import VistaModelo.VMLibro;

public class ModificarLibro1_1_Activity extends AppCompatActivity {
    ListView lvlibros;
    VMLibro vmLibro = new VMLibro();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro11);

        lvlibros= findViewById(R.id.lv_modificarLibroP1);

        vmLibro.cargarLibros(this);

// Actualiza el adaptador del ListView con los nuevos datos
        ArrayList<Libro> listaLibros = vmLibro.listarLibros();
        ArrayAdapter<Libro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLibros);
        lvlibros.setAdapter(adapter);
        registerForContextMenu(lvlibros);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int id = v.getId();
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.im_modificar) {
            // Obtener el libro seleccionado


            // Crear un Intent para iniciar la nueva actividad
            Intent intent = new Intent(ModificarLibro1_1_Activity.this, ModificarLibro1_2_Activity.class);



            // Iniciar la nueva actividad
            startActivity(intent);

            return true;
        }
        return super.onContextItemSelected(item);
    }

    private Libro obtenerLibro(int position) {


            ArrayList<Libro> lista = vmLibro.listarLibros();
            if (position >= 0 && position < lista.size()) {
                return lista.get(position);
            }
            return null; // En caso de que la posición sea inválida
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}