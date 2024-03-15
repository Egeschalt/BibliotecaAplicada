package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Model.Libro;
import Model.Prestamo;
import VistaModelo.VMBibliotecario;
import VistaModelo.VMEstudiante;
import VistaModelo.VMPrestamo;

public class ReservarLibro_2 extends AppCompatActivity {
    TextView tv_Titulo, tv_Autor, tv_Idioma, tv_Cantidad;
    ImageView iv_Libro;
    CalendarView cv_FechaEntrega;
    ImageButton btn_prestar;
    VMPrestamo vmPrestamo;
    VMEstudiante vmEstudiante;
    VMBibliotecario vmBibliotecario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_libro2);
        vmBibliotecario = new VMBibliotecario(this); // Corrección aquí

        tv_Titulo = findViewById(R.id.tv_tituloPrestaP2);
        tv_Autor = findViewById(R.id.tv_AutorPrestaP2);
        tv_Idioma = findViewById(R.id.tv_idiomaPrestaP2);
        tv_Cantidad = findViewById(R.id.tv_cantDispPrestarP2);
        iv_Libro = findViewById(R.id.iv_imgLibroPrestar);
        cv_FechaEntrega = findViewById(R.id.calendarv_reservarLibro);
        btn_prestar = findViewById(R.id.btn_prestar);

        vmPrestamo = new VMPrestamo(this);
        vmEstudiante = new VMEstudiante(this); // Pasando el contexto al constructor

        Bundle datos = getIntent().getExtras();
        Libro olibro = (Libro) datos.getSerializable("libro");

        tv_Titulo.setText(olibro.getTitulo());
        tv_Autor.setText(olibro.getAutor());
        tv_Idioma.setText(olibro.getIdioma());
        tv_Cantidad.setText(String.valueOf(olibro.getCantidadDisponible()));
        iv_Libro.setImageBitmap(decodificarByteBitMap(olibro.getImgLibro()));

        VMBibliotecario finalVmBibliotecario = vmBibliotecario;
        btn_prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Obtener la fecha actual
                    Calendar fechaActual = Calendar.getInstance();

                    // Obtener la fecha seleccionada
                    long fechaSeleccionadaMillis = cv_FechaEntrega.getDate();
                    Calendar fechaSeleccionada = Calendar.getInstance();
                    fechaSeleccionada.setTimeInMillis(fechaSeleccionadaMillis);

                    // Verificar si la fecha seleccionada es posterior a la fecha actual
                    if (fechaActual.after(fechaSeleccionada)) {
                        // Crear la fecha de vencimiento sumando una semana a la fecha seleccionada
                        Calendar fechaVencimiento = (Calendar) fechaSeleccionada.clone();
                        fechaVencimiento.add(Calendar.WEEK_OF_YEAR, 1);

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); // Formato dd/MM/yyyy
                        String fechaActualString = formatoFecha.format(fechaActual.getTime());
                        String fechaSeleccionadaString = formatoFecha.format(fechaSeleccionada.getTime());
                        String fechaVencimientoString = formatoFecha.format(fechaVencimiento.getTime());

                        // La fecha seleccionada es posterior a la fecha actual
                        Intent ointent = getIntent();
                        int idLibro = ointent.getIntExtra("idLibroSeleccionado", -1);
                        String codEstudiante = vmEstudiante.obtenerCodEstudiante(0);
                        int codBibliotecario = finalVmBibliotecario.obtenerIdBibliotecario();

                        Prestamo prestamo = new Prestamo(
                                idLibro,
                                codEstudiante,
                                codBibliotecario,
                                fechaActualString,
                                fechaSeleccionadaString,
                                fechaVencimientoString
                        );

                        if (vmPrestamo.AgregarPrestamo(prestamo)) {
                            Toast.makeText(ReservarLibro_2.this, "Agregado Correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ReservarLibro_2.this, Bienvenido.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ReservarLibro_2.this, "Ocurrió un error al agregar el préstamo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // La fecha seleccionada es igual o anterior a la fecha actual
                        Toast.makeText(ReservarLibro_2.this, "Seleccione una fecha posterior a la actual", Toast.LENGTH_SHORT).show();
                        Log.d("DEBUG", "Fecha actual: " + fechaActual.getTime().toString());
                        Log.d("DEBUG", "Fecha seleccionada: " + fechaSeleccionada.getTime().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ReservarLibro_2.this, "Error al obtener la fecha seleccionada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Bitmap decodificarByteBitMap(byte[] imagen) {
        return BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
    }
}
