package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Model.Bibliotecario;
import Model.Prestamo;

public class VMPrestamo {
    ArrayList<Prestamo> listaPrestamos;
    String nombreBD;
    Activity oActivity;
    int version;

    public VMPrestamo(Activity oActivity) {
        listaPrestamos=new ArrayList<>();
        nombreBD="BDBiblioteca";
        version=1;
        this.oActivity=oActivity;
        InsertarPrestamo();
    }

    private void InsertarPrestamo(){

    }
    private boolean AgregarPrestamo(Prestamo prestamo) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdBibliotecarioOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdBibliotecarioOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdPrestamo", prestamo.getIdPrestamo());
            oRegistro.put("idLibro", prestamo.getIdLibro().getIdLibro());
            oRegistro.put("IdEstudiante", prestamo.getIdEstudiante().getCodEstudiante());
            oRegistro.put("IdBibliotecario", prestamo.getIdBibliotecario().getIdBibliotecario());
            oRegistro.put("FechaPrestamo", prestamo.getFechaPrestamo().getTime());
            oRegistro.put("FechaDevolucion", prestamo.getFechaDevolucion().getTime());
            oRegistro.put("FechaVencimiento", prestamo.getFechaVencimiento().getTime());

            long fila = oBD.insert("Bibliotecario", null, oRegistro);
            Log.d("valor de fila", String.valueOf(fila));
            if (fila > 1) {
                rpta = true;
                oBD.close();
            }
        }
        return rpta;
    }
}
