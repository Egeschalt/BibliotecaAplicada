package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.Bibliotecario;
import Model.Estudiante;
import Model.Libro;
import Model.Prestamo;
import VistaModelo.VMEstudiante;
import VistaModelo.VMPrestamo;

public class ReservarLibro_2 extends AppCompatActivity {
    TextView tv_Titulo, tv_Autor, tv_Idioma, tv_Cantidad;
    ImageView iv_Libro;
    CalendarView cv_FechaEntrega;
    ImageButton btn_prestar;
    VMPrestamo vmPrestamo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_libro2);
        tv_Titulo = findViewById(R.id.tv_tituloPrestaP2);
        tv_Autor = findViewById(R.id.tv_AutorPrestaP2);
        tv_Idioma = findViewById(R.id.tv_idiomaPrestaP2);
        tv_Cantidad = findViewById(R.id.tv_cantDispPrestarP2);
        iv_Libro = findViewById(R.id.iv_imgLibroPrestar);
        cv_FechaEntrega = findViewById(R.id.calendarv_reservarLibro);

        btn_prestar = findViewById(R.id.btn_prestar);
        vmPrestamo = new VMPrestamo(this);
        Bundle datos = getIntent().getExtras();
        Libro olibro =  datos.getParcelable("libro");
        tv_Titulo.setText(olibro.getTitulo());
        tv_Autor.setText(olibro.getAutor());
        tv_Idioma.setText(olibro.getIdioma());
        tv_Cantidad.setText(olibro.getCantidadDisponible());
        iv_Libro.setImageBitmap(BitmapFactory.decodeByteArray(olibro.getImgLibro(), 0, olibro.getImgLibro().length));

        btn_prestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Calendar fechaActual = Calendar.getInstance();
                long fechaSeleccionadaMillis = cv_FechaEntrega.getDate();
                Calendar fechaSeleccionada = Calendar.getInstance();
                fechaSeleccionada.setTimeInMillis(fechaSeleccionadaMillis);

                Calendar fechaVencimiento = (Calendar) fechaSeleccionada.clone();
                fechaVencimiento.add(Calendar.WEEK_OF_YEAR, 1);

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); // Formato dd/MM/yyyy
                String fechaActualString = formatoFecha.format(fechaActual.getTime());
                String fechaSeleccionadaString = formatoFecha.format(fechaSeleccionada.getTime());
                String fechaVencimientoString = formatoFecha.format(fechaVencimiento.getTime());

                if (fechaSeleccionadaMillis > fechaActual.getTimeInMillis()) {
                    Estudiante estudiante = new Estudiante();
                    Libro libro = new Libro();
                    Bibliotecario bibliotecario = new Bibliotecario();
                    Prestamo prestamo = new Prestamo(
                            libro.getIdLibro(),
                            estudiante.getCodEstudiante(),
                            bibliotecario.getIdBibliotecario(),
                            fechaActualString,
                            fechaSeleccionadaString,
                            fechaVencimientoString
                    );
                    if (vmPrestamo.AgregarPrestamo(prestamo)) {
                        Toast.makeText(ReservarLibro_2.this, "Agregado Correctamente\"", Toast.LENGTH_SHORT).show();
                        PantallaReservaLibro();
                    } else {
                        Toast.makeText(ReservarLibro_2.this, "Seleccione una fecha posterior a la actual", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    public void PantallaReservaLibro(){
        Intent intent = new Intent(ReservarLibro_2.this, ReservaLibro.class);
        startActivity(intent);
        finish();
    }
}