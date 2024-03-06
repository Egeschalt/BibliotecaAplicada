package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
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

    private void InsertarEstudiantes(){
      //  Estudiante est1=new Estudiante("01","Maycol","Varas","72346482","Ingeniería de Sistemas","jvaras","a",);
       // Estudiante est2=new Estudiante("02","Diego");
//        Estudiante est3=new Estudiante("03","Jhosmer");
//        Estudiante est4=new Estudiante("04","Hans");
//        Estudiante est5=new Estudiante("05","Ever");
//        AgregarEstudiante(est1);


    }
    private boolean AgregarEstudiante(Estudiante estudiante) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdEstudiantesOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdEstudiantesOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdEstudiante", estudiante.getCodEstudiante());
            oRegistro.put("nombreEstudiante", estudiante.getNombre());
            oRegistro.put("nombreEstudiante", estudiante.getApellidos());
            oRegistro.put("nombreEstudiante", estudiante.getCarrera());
            oRegistro.put("nombreEstudiante", estudiante.getCorreo());
            oRegistro.put("nombreEstudiante", estudiante.getUsuario());
            oRegistro.put("nombreEstudiante", estudiante.getContrasena());
            oRegistro.put("nombreEstudiante", estudiante.getTelefono());

            long fila = oBD.insert("Estudiante", null, oRegistro);
            Log.d("valor de fila", String.valueOf(fila));
            if (fila > 1) {
                rpta = true;
                oBD.close();
            }
        }
        return rpta;
    }
}