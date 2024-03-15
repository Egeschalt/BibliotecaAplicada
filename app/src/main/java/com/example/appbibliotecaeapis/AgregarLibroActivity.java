package com.example.appbibliotecaeapis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Model.Libro;
import VistaModelo.VMLibro;

public class AgregarLibroActivity extends AppCompatActivity {
    VMLibro vmLibro ;
    Button btnagregar;
    EditText ettitulo,etautor,eteditorial,etgenero,etidioma,etstock;

    ImageView ivlibro;

    CalendarView cvfecha;
    ImageButton ibbuscar;
    byte[] imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        ettitulo= findViewById(R.id.et_tituloAgregar);
        etautor= findViewById(R.id.et_autorAgregar);
        eteditorial= findViewById(R.id.et_editorialAgregar);
        etgenero= findViewById(R.id.et_generoAgregar);
        etidioma= findViewById(R.id.et_idiomaAgregar);
        ivlibro= findViewById(R.id.imv_libro);
        cvfecha= findViewById(R.id.cv_fecha);
        ibbuscar = findViewById(R.id.ib_buscar);
        btnagregar = findViewById(R.id.btn_agregar);
        etstock = findViewById(R.id.et_stock);

        ivlibro.setOnClickListener(v -> {
            cargarImagen();
        });
        btnagregar.setOnClickListener(v -> {
            agregarLibro();

        });

    }
    private void agregarLibro() {
        try {
        String titulo =ettitulo.getText().toString();
        String autor =etautor.getText().toString();
        String editorial =eteditorial.getText().toString();
        String genero =etgenero.getText().toString();
        String idioma =etidioma.getText().toString();
        int stock = Integer.valueOf(etstock.getText().toString());
        long fechaMillis = cvfecha.getDate();
        Date fechaPublicacion = new Date(fechaMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String fechaFormateada = sdf.format(fechaPublicacion);
        Libro olibro = new Libro(titulo,autor,editorial,genero,idioma, fechaFormateada,imagen,stock,stock);
        vmLibro = new VMLibro();
        if(vmLibro.AgregarLibro(this,olibro)){
            Toast.makeText(this,"Libro Agregado Correctamente",Toast.LENGTH_SHORT).show();
            Intent oIntent = new Intent(AgregarLibroActivity.this, BienvenidoBibliotecarioActivity.class);
            startActivity(oIntent);
        }
        else {
            Toast.makeText(this,"No Se agrego  el Libro",Toast.LENGTH_SHORT).show();
        }
        } catch (SQLiteConstraintException e) {
            // Maneja la excepción cuando se viola la restricción de unicidad
            Toast.makeText(this, "El título del libro ya existe en la base de datos", Toast.LENGTH_SHORT).show();
            // Aquí puedes realizar alguna acción adicional, como solicitar un nuevo título al usuario
        }
    }

    private void cargarImagen() {
        Intent oIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        oIntent.setType("image/*");
        startActivityIfNeeded(Intent.createChooser(oIntent,"Selecionar la Aplicacion"),1);
    }
    @ Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1 && data != null){

            ivlibro.setImageURI(data.getData());
            ivlibro.buildDrawingCache();
            // Convierte la imagen a Bitmap
            Bitmap imagenBitMap = ivlibro.getDrawingCache();
            ByteArrayOutputStream flujoSalida = new ByteArrayOutputStream();
            // Comprime el Bitmap a formato PNG
            imagenBitMap.compress(Bitmap.CompressFormat.PNG, 100, flujoSalida);
            // Convierte ByteArrayOutputStream a un arreglo de bytes
            imagen = flujoSalida.toByteArray();
        }
    }

    public Bitmap decodificarByteBitMap(byte[] imagen){
        return BitmapFactory.decodeByteArray(imagen,0,imagen.length);
    }
}