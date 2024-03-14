package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import VistaModelo.BDPrestamoOpenHelper;
import VistaModelo.VMEstudiante;

public class IniciarSesion extends AppCompatActivity {
    EditText et_Correo, et_Contrasena;
    ImageButton btn_IniciarSesion;

    VMEstudiante vmEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        et_Contrasena = findViewById(R.id.et_contrasenaEstud);
        et_Correo = findViewById(R.id.et_usuario);
        btn_IniciarSesion = findViewById(R.id.bt_iniciarSesion);
        vmEstudiante = new VMEstudiante(this);
        btn_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Correo = et_Correo.getText().toString();
                String Contrasena = et_Contrasena.getText().toString();
                if(!Correo.isEmpty() && !Contrasena.isEmpty()) {
                    if (Correo.equals("bibliotecario1@unc.edu.pe") && Contrasena.equals("bibliotecario")) {
                        //Inicio de sesión de Bibliotecario
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BienvenidoBibliotecarioActivity.class);
                        startActivity(intent);
                    } else {
                        //Inicio de sesión de Estudiante
                        boolean esEstudiante = verificarEstudianteEnBD(Correo, Contrasena);
                        if (esEstudiante) {
                            // Inicio de sesión exitoso para estudiante
                            Context context = v.getContext();
                            Intent intent = new Intent(context, Bienvenido.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(IniciarSesion.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(IniciarSesion.this, "Rellene los campos", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean verificarEstudianteEnBD(String correo, String contrasena) {
                return vmEstudiante.verificarEstudiante(correo, contrasena);
            }
        });
    }
    public void PantallaRegistro(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, Registrar.class);
        startActivity(intent);
    }


    public void PantallaBienvenido(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, Bienvenido.class);
        startActivity(intent);
    }
}


