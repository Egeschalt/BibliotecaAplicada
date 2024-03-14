package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class IniciarSesion extends AppCompatActivity {
    EditText et_Usuario, et_Contrasena;
    ImageButton btn_IniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        et_Contrasena = findViewById(R.id.et_contrasena);
        et_Usuario = findViewById(R.id.et_usuario);
        btn_IniciarSesion = findViewById(R.id.bt_iniciarSesion);

        btn_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Usuario = et_Usuario.getText().toString();
                String Contrasena = et_Contrasena.getText().toString();
                if(!Usuario.isEmpty() && !Contrasena.isEmpty()){
                    if(Usuario.equals("Bibliotecario") && Contrasena.equals("BibliotecarioContraseña")){
                        //Inicio de sesión de Bibliotecario
                        Context context=v.getContext();
                        Intent intent = new Intent(context, BienvenidoBibliotecarioActivity.class);
                        startActivity(intent);
                    }else{
                        //Inicio de sesión de Estudiante
                        Context context=v.getContext();
                        Intent intent = new Intent(context, BienvenidoBibliotecarioActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(IniciarSesion.this, "Rellene los campos", Toast.LENGTH_SHORT).show();
                }
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