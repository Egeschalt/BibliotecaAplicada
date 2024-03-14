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
    public VMLibro() {
        this.listaLibros =new ArrayList<Libro>();
        nombreBD="BDBiblioteca";
        version=1;

    }

    private void InsertarLibrosCargados() {

    }

    public boolean AgregarLibro(Activity oActivity, Libro libro) {
        boolean rpta = false;
        BDPrestamoOpenHelper openHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = null;

        try {
            oBD = openHelper.getWritableDatabase();

            if (oBD != null) {

                ContentValues oRegistro = new ContentValues();

                oRegistro.put("titulo", libro.getTitulo());
                oRegistro.put("autor", libro.getAutor());
                oRegistro.put("editorial", libro.getEditorial());
                oRegistro.put("genero", libro.getGenero());
                oRegistro.put("idioma", libro.getIdioma());
                oRegistro.put("FechaDePublicación", libro.getFechaPublicacion());
                oRegistro.put("fotoLibro", libro.getImgLibro());
                oRegistro.put("cantidadDisponible", libro.getCantidadDisponible());
                oRegistro.put("stock", libro.getStock());

                long fila = oBD.insert("Libro", null, oRegistro);
                Log.d("valor de fila", String.valueOf(fila));


                if (fila > 0) {
                    rpta = true;
                }
            }
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loggearla)
        } finally {
            if (oBD != null) {
                oBD.close();
            }
        }

        return rpta;
    }
    public ArrayList<Libro> listarLibro(){
        return listaLibros;
    }

    public int contarLibros(){
        return listaLibros.size();
    }

    public Libro obtenerLibro(int indice){
        return listaLibros.get(indice);
}

}
