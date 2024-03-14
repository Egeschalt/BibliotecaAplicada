package com.example.appbibliotecaeapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Model.Estudiante;
import VistaModelo.VMEstudiante;

public class Registrar extends AppCompatActivity {

        VMEstudiante vmEstudiante;
        EditText et_Correo, et_Contrasena, et_Celular, et_Nombre, et_Apellido, et_Dni, et_Codigo;
        ImageButton btn_Registro;
        TextView tv_IniciaSesion;
        Spinner sp_Carrera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        vmEstudiante = new VMEstudiante(this);
        et_Correo = findViewById(R.id.et_correoEstud);
        et_Contrasena = findViewById(R.id.et_contrasenaEstud);
        btn_Registro = findViewById(R.id.bt_iniciarSesion);
        tv_IniciaSesion = findViewById(R.id.tv_iniciaSesion);
        et_Celular = findViewById(R.id.et_celular);
        et_Nombre = findViewById(R.id.et_nombres);
        et_Apellido = findViewById(R.id.et_apelidosEstud);
        et_Dni = findViewById(R.id.et_dniEst);
        et_Codigo = findViewById(R.id.et_codEstudiante);
        sp_Carrera = findViewById(R.id.sp_carreras);

        tv_IniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                PantallaSesion();
            }
        });
        btn_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEstudiante();
            }
        });
    }
    private void agregarEstudiante() {
        String Codigo = et_Codigo.getText().toString();
        String Nombre = et_Nombre.getText().toString();
        String Apellido = et_Apellido.getText().toString();
        String Dni = et_Dni.getText().toString();
        String Carrera = sp_Carrera.getSelectedItem().toString();
        String Celular = et_Celular.getText().toString();
        String Correo = et_Correo.getText().toString();
        String Contrasena= et_Contrasena.getText().toString();

        if(!Codigo.isEmpty() && !Nombre.isEmpty() && !Apellido.isEmpty() && !Dni.isEmpty() &&
                !Carrera.isEmpty() && !Correo.isEmpty() && !Contrasena.isEmpty() && !Celular.isEmpty()){
            if(Correo.endsWith("unc.edu.pe")){
                if (Dni.matches("[0-9]{8}")) {
                    if (Codigo.matches("[0-9]{10}")) {
                        Estudiante estudiante = new Estudiante(Codigo,Nombre,Apellido,Dni,Carrera,Correo,Contrasena,Celular);
                        vmEstudiante=new VMEstudiante(this);
                        if (vmEstudiante.AgregarEstudiante(this,estudiante)) {
                            Toast.makeText(this, "Agregado Correctamente", Toast.LENGTH_SHORT).show();
                            PantallaSesion();
                        }
                    } else {
                        Toast.makeText(this, "Código Inválido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "DNI Inválido", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(Registrar.this, "Por favor, utilice un correo válido", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Registrar.this, "Rellene los Campos faltantes", Toast.LENGTH_SHORT).show();
        }
    }
    public void PantallaSesion(){
        Intent intent = new Intent(Registrar.this, IniciarSesion.class);
        startActivity(intent);
        finish();
    }
}