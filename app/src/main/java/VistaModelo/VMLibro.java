package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Model.Libro;

public class VMLibro {
    ArrayList<Libro> listaLibros;
    String nombreBD;
    Activity oActivity;
    int version;
    public VMLibro(Activity oActivity) {
        listaLibros =new ArrayList<>();
        nombreBD="BDPlato";
        version=1;
        this.oActivity=oActivity;
        InsertarLibrosCargados();
    }

    private void InsertarLibrosCargados() {

    }

    private boolean AgregarLinbro(Libro libro) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdLibroOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdLibroOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oRegistro = new ContentValues();
            oRegistro.put("idLibro", libro.getIdLibro());
            oRegistro.put("titulo", libro.getTitulo());
            oRegistro.put("autor", libro.getAutor());
            oRegistro.put("editorial", libro.getEditorial());
            oRegistro.put("genero", libro.getGenero());
            oRegistro.put("idioma", libro.getIdioma());
            oRegistro.put("fechaPublicacion", libro.getFechaPublicacion().getTime());
            oRegistro.put("disponibilidad", libro.isDisponibilidad());
            oRegistro.put("imglibro", libro.getImgLibro());

            long fila = oBD.insert("Libro", null, oRegistro);
            Log.d("valor de fila", String.valueOf(fila));
            if (fila > 1) {
                rpta = true;
                oBD.close();
            }
        }
        return rpta;
    }

}
