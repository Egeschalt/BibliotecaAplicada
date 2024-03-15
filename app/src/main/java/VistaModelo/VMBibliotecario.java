package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Model.Bibliotecario;

public class VMBibliotecario {
    Context context; // Variable para almacenar el contexto

    ArrayList<Bibliotecario> listaBibliotecarios;
    String nombreBD;
    Activity oActivity;
    int version;

    public VMBibliotecario(Context context) {
        this.context = context;
        listaBibliotecarios = new ArrayList<>();
        nombreBD = "BDBiblioteca";
        version = 1;
        InsertarBibliotecario();
    }

    private void InsertarBibliotecario(){

        Bibliotecario bibliotecario1=new Bibliotecario("Juan","Pérez","945123100","bibliotecario1@unc.edu.pe","bibliotecario");
        if (AgregarBibliotecario(bibliotecario1)) {
            listaBibliotecarios.add(bibliotecario1);
        }
    }

    private boolean AgregarBibliotecario(Bibliotecario bibliotecario) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdBibliotecarioOpenHelper = new BDPrestamoOpenHelper(context, nombreBD, null, version); // Corrección aquí
        SQLiteDatabase oBD = bdBibliotecarioOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("IdBibliotecario", bibliotecario.getIdBibliotecario());
            oRegistro.put("nombreBibliotecario", bibliotecario.getNombre());
            oRegistro.put("apellidosBibliotecario", bibliotecario.getApellidos());
            oRegistro.put("telefonoBibliotecario", bibliotecario.getTelefono());
            oRegistro.put("correoBibliotecario", bibliotecario.getCorreo());
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
    public int obtenerIdBibliotecario() {
        if (!listaBibliotecarios.isEmpty()) {
            return listaBibliotecarios.get(0).getIdBibliotecario();
        } else {
            return 0;
        }
    }
}
