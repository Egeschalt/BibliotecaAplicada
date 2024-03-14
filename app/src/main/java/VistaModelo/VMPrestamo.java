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
    public boolean AgregarPrestamo(Prestamo prestamo) {

        boolean rpta = false;
        BDPrestamoOpenHelper bdBibliotecarioOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdBibliotecarioOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdPrestamo", prestamo.getIdPrestamo());
            oRegistro.put("IdLibro",prestamo.getIdLibro());
            oRegistro.put("IdEstudiante", prestamo.getIdEstudiante());
            oRegistro.put("IdBibliotecario", prestamo.getIdBibliotecario());
            oRegistro.put("FechaDePrestamo", prestamo.getFechaPrestamo());
            oRegistro.put("FechaDeDevolución", prestamo.getFechaDevolucion());
            oRegistro.put("FechaDeVencimiento", prestamo.getFechaVencimiento());

            long fila = oBD.insert("Prestamo", null, oRegistro);
            Log.d("valor de fila", String.valueOf(fila));
            if (fila > 1) {
                rpta = true;
                oBD.close();
            }
        }
        return rpta;
    }
}
