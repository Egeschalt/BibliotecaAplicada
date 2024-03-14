package com.example.appbibliotecaeapis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    EditText ettitulo,etautor,eteditorial,etgenero,etidioma;

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

        ivlibro.setOnClickListener(v -> {
            cargarImagen();
        });
        btnagregar.setOnClickListener(v -> {
            agregarLibro();


        });






    }
    private void agregarLibro() {


        String titulo =ettitulo.getText().toString();
        String autor =etautor.getText().toString();
        String editorial =eteditorial.getText().toString();
        String genero =etgenero.getText().toString();
        String idioma =etidioma.getText().toString();


        long fechaMillis = cvfecha.getDate();
        Date fechaPublicacion = new Date(fechaMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String fechaFormateada = sdf.format(fechaPublicacion);

        boolean disponible = true;

        Libro olibro = new Libro(titulo,autor,editorial,genero,idioma, fechaFormateada,disponible,imagen);
        vmLibro = new VMLibro();
        if(vmLibro.AgregarLibro(this,olibro)){
            Toast.makeText(this,"Libro Agregado Correctamente",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"No Se agrego  el Libro",Toast.LENGTH_SHORT).show();
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
        if(resultCode==RESULT_OK && requestCode == 1){

            ivlibro.setImageURI(data.getData());
            ivlibro.buildDrawingCache();
            // convierte la imagen a Bitmap
            Bitmap imagenBitMap=ivlibro.getDrawingCache();
            ByteArrayOutputStream flujoSalida=new ByteArrayOutputStream();
            imagenBitMap.compress(Bitmap.CompressFormat.PNG,0,flujoSalida);
            imagen=flujoSalida.toByteArray();
//
        }
    }
    public Bitmap decodificarByteBitMap(byte[] imagen){
        return BitmapFactory.decodeByteArray(imagen,0,imagen.length);
    }
}