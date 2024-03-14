package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Model.Estudiante;
import VistaModelo.VMEstudiante;

public class Registrar extends AppCompatActivity {

        VMEstudiante vmEstudiante;
        EditText et_Usuario, et_Correo, et_Contrasena;
        ImageButton btn_Registro;
        TextView tv_IniciaSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        vmEstudiante = new VMEstudiante(this);
        et_Usuario = findViewById(R.id.et_usuario);
        et_Correo = findViewById(R.id.et_correoEstud);
        et_Contrasena = findViewById(R.id.et_contrasenaEstud);
        btn_Registro = findViewById(R.id.bt_iniciarSesion);
        tv_IniciaSesion = findViewById(R.id.tv_iniciaSesion);

        btn_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEstudiante();
            }
        });
    }

    private void agregarEstudiante() {
        String Usuario = et_Usuario.getText().toString();
        String Correo = et_Correo.getText().toString();
        String Contrasena= et_Contrasena.getText().toString();

        if(!Usuario.isEmpty() && !Correo.isEmpty() && !Contrasena.isEmpty()){
            if(Correo.endsWith("unc.edu.pe")){
                Estudiante estudiante = new Estudiante(Usuario,Correo,Contrasena);
                vmEstudiante=new VMEstudiante(this);
                if (vmEstudiante.AgregarEstudiante(this,estudiante)) {
                    Toast.makeText(this, "Agregado Correctamente", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(Registrar.this, "Por favor, utilice un correo válido", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Registrar.this, "Rellene los Campos faltantes", Toast.LENGTH_SHORT).show();
        }
    }
    public void PantallaSesion(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, IniciarSesion.class);
        startActivity(intent);
    }
}