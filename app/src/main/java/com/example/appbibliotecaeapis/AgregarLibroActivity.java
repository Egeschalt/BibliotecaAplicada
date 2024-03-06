package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.Date;

public class AgregarLibroActivity extends AppCompatActivity {
EditText ettitulo,etautor,eteditorial;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);
    }
}