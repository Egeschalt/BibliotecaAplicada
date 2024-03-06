package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Model.Bibliotecario;

public class VMBibliotecario {
    ArrayList<Bibliotecario> listaBibliotecarios;
    String nombreBD;
    Activity oActivity;
    int version;

    public VMBibliotecario(Activity oActivity) {
        listaBibliotecarios=new ArrayList<>();
        nombreBD="BDBiblioteca";
        version=1;
        this.oActivity=oActivity;
        InsertarBibliotecario();
    }

    private void InsertarBibliotecario(){

    }
    private boolean AgregarBibliotecario(Bibliotecario bibliotecario) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdBibliotecarioOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdBibliotecarioOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdBibliotecario", bibliotecario.getIdBibliotecario());
            oRegistro.put("nombreBibliotecario", bibliotecario.getNombre());
            oRegistro.put("apellidosBibliotecario", bibliotecario.getApellidos());
            oRegistro.put("telefonoBibliotecario", bibliotecario.getTelefono());
            oRegistro.put("correoBibliotecario", bibliotecario.getCorreo());
            oRegistro.put("usuarioBibliotecario", bibliotecario.getUsuario());
            oRegistro.put("contasenaBibliotecario", bibliotecario.getContrasena());
            oRegistro.put("fotoBibliotecario", bibliotecario.getFotoBibliotecario());

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
