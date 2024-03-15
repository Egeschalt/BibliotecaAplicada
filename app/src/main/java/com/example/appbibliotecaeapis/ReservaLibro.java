package com.example.appbibliotecaeapis;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import Model.Libro;

import VistaModelo.VMLibro;


public class ReservaLibro extends AppCompatActivity {
    public static VMLibro vmLibro = new VMLibro();

    RecyclerView rvLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_libro);

        rvLibros = findViewById(R.id.rv_librosParaReservar);

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 11);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvLibros.setLayoutManager(linearLayoutManager);

        if (vmLibro.CargarLibros(this)) {
            LibroAdapter libroAdapter = new LibroAdapter(this, vmLibro);
            rvLibros.setAdapter(libroAdapter);
            libroAdapter.setOnLibroClickListener(new LibroAdapter.OnLibroClickListener() {
                @Override
                public void onLibroClick(int position, Libro libro) {
                    Libro libroSeleccionado = vmLibro.obtenerLibro(position);
                    Intent oIntent = new Intent(ReservaLibro.this, ReservarLibro_2.class);
                    oIntent.putExtra("idLibroSeleccionado", libroSeleccionado.getIdLibro());
                    oIntent.putExtra("libro", libro);
                    startActivity(oIntent);
                }
            });

        }else {
            Toast.makeText(this, "No hay registro de libros", Toast.LENGTH_SHORT).show();
        }
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rvLibros.getContext(),
                linearLayoutManager.getOrientation());
        rvLibros.addItemDecoration(mDividerItemDecoration);
    }
}

