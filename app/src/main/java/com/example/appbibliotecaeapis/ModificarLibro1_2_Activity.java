package com.example.appbibliotecaeapis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.BaseMenuPresenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

public class ModificarLibro1_2_Activity extends AppCompatActivity {
    VMLibro vmLibro ;
    Button btnmodificar;
    EditText ettitulo,etautor,eteditorial,etgenero,etidioma,etstock;

    ImageView ivlibro;

    CalendarView cvfecha;
    ImageButton ibbuscar;
    byte[] imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro12);


        ettitulo= findViewById(R.id.et_tituloModificarP2);
        etautor= findViewById(R.id.et_autorModificarP2);
        eteditorial= findViewById(R.id.et_editorialModificarP2);
        etgenero= findViewById(R.id.et_generoModificarP2);
        etidioma= findViewById(R.id.et_idiomaModificarP2);
        ivlibro= findViewById(R.id.img_mostrarImagenModificarP2);
        cvfecha= findViewById(R.id.calendarv_fechaPublicacionModificarP2);
        btnmodificar = findViewById(R.id.btn_modificarP2);




        Bundle datos = getIntent().getExtras();
        if (datos != null) {
            Libro oLibro = (Libro) datos.getSerializable("libroSeleccionado");
            if (oLibro != null) {
                // Mostrar los datos del libro en los EditText
                ettitulo.setText(oLibro.getTitulo());
                etautor.setText(oLibro.getAutor());
                eteditorial.setText(oLibro.getEditorial());
                etgenero.setText(oLibro.getGenero());
                etidioma.setText(oLibro.getIdioma());
                etstock.setText(String.valueOf(oLibro.getStock()));

                // Decodificar la imagen del libro y establecerla en el ImageView
                ivlibro.setImageBitmap(decodificarByteBitMap(oLibro.getImgLibro()));
            } else {
                // Mostrar un mensaje de error o manejar el caso de libro nulo
                Toast.makeText(this, "No se encontró el libro seleccionado", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mostrar un mensaje de error o manejar el caso de datos nulos
            Toast.makeText(this, "No se recibieron datos del libro seleccionado", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap decodificarByteBitMap(byte[] imagen){
        return BitmapFactory.decodeByteArray(imagen,0,imagen.length);
    }

    private void modificarLibro() {
        String titulo = ettitulo.getText().toString();
        String autor = etautor.getText().toString();
        String editorial = eteditorial.getText().toString();
        String genero = etgenero.getText().toString();
        String idioma = etidioma.getText().toString();
        int stock = Integer.valueOf(etstock.getText().toString());



    }
}