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

    public VMEstudiante(Activity oActivity) {
        listaEstudiantes=new ArrayList<>();
        nombreBD="BDBiblioteca";
        version=1;
        this.oActivity=oActivity;
        InsertarEstudiantes();
    }

    public void InsertarEstudiantes(){
      //  Estudiante est1=new Estudiante("01","Maycol","Varas","72346482","Ingeniería de Sistemas","jvaras","a",);
       // Estudiante est2=new Estudiante("02","Diego");
//        Estudiante est3=new Estudiante("03","Jhosmer");
//        Estudiante est4=new Estudiante("04","Hans");
//        Estudiante est5=new Estudiante("05","Ever");
//        AgregarEstudiante(est1);


    }
    public boolean AgregarEstudiante(Activity oActivity,Estudiante estudiante) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdEstudiantesOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdEstudiantesOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdEstudiante", estudiante.getCodEstudiante());
            oRegistro.put("nombreEstudiante", estudiante.getNombre());
            oRegistro.put("apellidosEstudiante", estudiante.getApellidos());
            oRegistro.put("carreraEstudiante", estudiante.getCarrera());
            oRegistro.put("correoEstudiante", estudiante.getCorreo());
            oRegistro.put("contasenaEstudiante", estudiante.getContrasena());
            oRegistro.put("telefonoEstudiante", estudiante.getTelefono());
            long fila = oBD.insert("Estudiante", null, oRegistro);
            Log.d("valor de fila", String.valueOf(fila));
            if (fila > 1) {
                rpta = true;
                oBD.close();
            }
        }
        return rpta;
    }
    public boolean verificarEstudiante(String correo, String contrasena) {
        BDPrestamoOpenHelper bdEstudiantesOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdEstudiantesOpenHelper.getReadableDatabase();
        boolean existeEstudiante = false;

        if (oBD != null) {
            String[] args = new String[]{correo, contrasena};
            Cursor c = oBD.rawQuery("SELECT * FROM Estudiante WHERE correoEstudiante=? AND contrasenaEstudiante=?", args);

            if (c.moveToFirst()) {
                existeEstudiante = true;
            }
            c.close();
            oBD.close();
        }
        return existeEstudiante;
    }
}