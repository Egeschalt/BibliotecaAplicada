package com.example.appbibliotecaeapis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import Model.Libro;
import VistaModelo.VMLibro;

public class ModificarLibro1_2_Activity extends AppCompatActivity {
    VMLibro vmLibro;
    Button btnmodificar;
    EditText ettitulo, etautor, eteditorial, etgenero, etidioma, etstock;
    ImageView ivlibro;
    byte[] imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro12);

        ettitulo = findViewById(R.id.et_tituloModificarP2);
        etautor = findViewById(R.id.et_et_autorModificarP2);
        eteditorial = findViewById(R.id.et_editorialModificarP2);
        etgenero = findViewById(R.id.et_generoModificarP2);
        etidioma = findViewById(R.id.et_idiomaModificarP2);
        etstock = findViewById(R.id.et_stockModificarP2);
        ivlibro = findViewById(R.id.img_mostrarImagenModificarP2);
        btnmodificar = findViewById(R.id.btn_modificarP2);
        btnmodificar.setOnClickListener(view -> modificarLibro());
        ivlibro.setOnClickListener(v -> {
            cargarImagen();
        });
        Bundle datos = getIntent().getExtras();
        if (datos != null) {
            if (datos.containsKey("libroSeleccionado")) {
                Libro oLibro = (Libro) datos.getSerializable("libroSeleccionado");
                if (oLibro != null) {
                    // Mostrar los datos del libro en los EditText y el ImageView
                    ettitulo.setText(oLibro.getTitulo());
                    etautor.setText(oLibro.getAutor());
                    eteditorial.setText(oLibro.getEditorial());
                    etgenero.setText(oLibro.getGenero());
                    etidioma.setText(oLibro.getIdioma());
                    etstock.setText(String.valueOf(oLibro.getStock())); // Convertir el stock a String
                    ivlibro.setImageBitmap(decodificarByteBitMap(oLibro.getImgLibro()));
                } else {
                    Log.d("ModificarLibro1_2", "El objeto Libro es nulo");
                }
            } else {
                Log.d("ModificarLibro1_2", "No se encontró la clave 'libroSeleccionado' en el Intent");
            }
        } else {
            Log.d("ModificarLibro1_2", "El bundle es nulo");
        }
    }


    private Bitmap decodificarByteBitMap(byte[] imagen) {
        return BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
    }

    private void modificarLibro() {
        Intent ointent = getIntent();
        String titulo = ettitulo.getText().toString();
        String autor = etautor.getText().toString();
        String editorial = eteditorial.getText().toString();
        String genero = etgenero.getText().toString();
        String idioma = etidioma.getText().toString();

        int idLibro = ointent.getIntExtra("idLibroSeleccionado", -1);

        // Verificar si el campo de stock es numérico antes de intentar convertirlo
        int stock = 0;
        String stockString = etstock.getText().toString();
        if (!TextUtils.isEmpty(stockString) && TextUtils.isDigitsOnly(stockString)) {
            stock = Integer.parseInt(stockString);
        } else {
            // Mostrar un mensaje de error si el campo no es numérico
            Toast.makeText(this, "Por favor, introduce un valor numérico para el stock", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el campo no es numérico
        }

        // Convertir la imagen del ImageView a un array de bytes si es necesario
        if (imagen == null) {
            ivlibro.buildDrawingCache();
            Bitmap imagenBitmap = ivlibro.getDrawingCache();
            ByteArrayOutputStream flujoSalida = new ByteArrayOutputStream();
            imagenBitmap.compress(Bitmap.CompressFormat.PNG, 0, flujoSalida);
            imagen = flujoSalida.toByteArray();
        }

        // Crear un nuevo objeto Libro con los datos modificados
        Libro oLibro = new Libro(titulo, autor, editorial, genero, idioma, "", imagen, 0, stock);

        // Inicializar la instancia de VMLibro si aún no se ha hecho
        if (vmLibro == null) {
            vmLibro = new VMLibro();
        }

        // Llamar al método para modificar el libro en la base de datos
        if (vmLibro.ModificarLibro(this, oLibro, idLibro)) {
            Toast.makeText(this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
            Intent oIntent = new Intent(this, ModificarLibro1_1_Activity.class);
            startActivity(oIntent);
        } else {
            Toast.makeText(this, "No se pudo modificar el libro", Toast.LENGTH_SHORT).show();
        }
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

    private void cargarImagen() {
        Intent oIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        oIntent.setType("image/*");
        startActivityIfNeeded(Intent.createChooser(oIntent,"Selecionar la Aplicacion"),1);
    }
}