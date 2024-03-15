package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Model.Estudiante;

public class VMEstudiante {
    ArrayList<Estudiante> listaEstudiantes;
    String nombreBD;
    Activity oActivity;
    int version;

    public VMEstudiante(Activity activity) {
        listaEstudiantes = new ArrayList<>();
        nombreBD = "BDBiblioteca";
        version = 1;
        oActivity = activity;
        InsertarEstudiantes();
    }

    public void InsertarEstudiantes() {
        // Código para insertar estudiantes en la base de datos
    }

    public boolean AgregarEstudiante(Estudiante estudiante) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdEstudiantesOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdEstudiantesOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdEstudiante", estudiante.getCodEstudiante());
            oRegistro.put("Nombre", estudiante.getNombre());
            oRegistro.put("Apellidos", estudiante.getApellidos());
            oRegistro.put("Dni", estudiante.getDni());
            oRegistro.put("Carrera", estudiante.getCarrera());
            oRegistro.put("Correo", estudiante.getCorreo());
            oRegistro.put("Contraseña", estudiante.getContrasena());
            oRegistro.put("Telefono", estudiante.getTelefono());
            oRegistro.put("fotoEstudiante", estudiante.getFotoEstudiante());
            long fila = oBD.insert("Estudiante", null, oRegistro);
            Log.d("valor de fila", String.valueOf(fila));
            listaEstudiantes.add(estudiante);
            if (fila > 0) {
                listaEstudiantes.add(estudiante);
                rpta = true;
            }
            oBD.close();
        }
        return rpta;
    }

    public boolean verificarEstudiante(String correo, String contrasena) {
        BDPrestamoOpenHelper bdEstudiantesOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdEstudiantesOpenHelper.getReadableDatabase();
        boolean existeEstudiante = false;

        if (oBD != null) {
            String[] args = new String[]{correo, contrasena};
            Cursor c = oBD.rawQuery("SELECT * FROM Estudiante WHERE Correo=? AND Contraseña=?", args);

            if (c.moveToFirst()) {
                existeEstudiante = true;
            }
            c.close();
            oBD.close();
        }
        return existeEstudiante;
    }

    public String obtenerCodEstudiante(int indice) {
        if (indice >= 0 && indice < listaEstudiantes.size()) {
            Estudiante estudiante = listaEstudiantes.get(indice);
            return estudiante.getCodEstudiante();
        } else {
            return ""; // Retorna una cadena vacía si el índice está fuera de rango
        }
    }
}
